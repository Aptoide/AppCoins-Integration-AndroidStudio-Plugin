package actions;

import com.intellij.openapi.command.WriteCommandAction;
import com.intellij.openapi.editor.Document;
import com.intellij.openapi.fileEditor.FileDocumentManager;
import com.intellij.openapi.fileEditor.FileEditorManager;
import com.intellij.openapi.fileEditor.OpenFileDescriptor;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.vfs.VirtualFile;
import window_factory.BillingToolWindowFactory;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.util.Map;

public class ImplementConsumeAPurchaseChanges extends AbstractAction {
    private Project project;
    private Map<Integer, VirtualFile> files;
    public ImplementConsumeAPurchaseChanges(Project project, Map<Integer, VirtualFile> files){
        super("Implement automatically");
        this.project=project;
        this.files=files;
    }
    @Override
    public void actionPerformed(ActionEvent actionEvent) {
        Document document = FileDocumentManager.getInstance().getDocument(files.get(1));
        String oldContent = document.getText();
        StringBuffer newContent = new StringBuffer();
        newContent.append(document.getText());
        String listenerAndMethod = "";
        String toImport = "";
        int classIndex = newContent.indexOf("{");;
        if(oldContent.contains("cab")){
            if (BillingToolWindowFactory.projectIsKotlin()) {
                classIndex=newContent.indexOf("AppcoinsBillingClient",newContent.indexOf("cab"));
                classIndex += "AppcoinsBillingClient".length();
            } else {
                classIndex = newContent.indexOf(";", newContent.indexOf("cab"));
            }
        }

        FileEditorManager.getInstance(project).openTextEditor(
                new OpenFileDescriptor(project,files.get(1),classIndex),
                true // request focus to editor
        );
        int importIndex = newContent.indexOf("\n",newContent.lastIndexOf("import "));
        if (!oldContent.contains("ConsumeResponseListener")){
            listenerAndMethod= listenerAndMethod.concat(BillingToolWindowFactory.snippets.consumePurchase1());
            toImport=toImport.concat("\nimport com.appcoins.sdk.billing.listeners.ConsumeResponseListener;");
            if(!oldContent.contains("android.util.Log")){
                toImport=toImport.concat("\nimport android.util.Log;");
            }
        }
        if (!oldContent.contains("cab.consumeAsync")){
            listenerAndMethod = listenerAndMethod.concat(BillingToolWindowFactory.snippets.consumePurchase2());
            if(!oldContent.contains("appcoins.sdk.billing.PurchasesResult")){
                toImport=toImport.concat("\nimport com.appcoins.sdk.billing.PurchasesResult;");
            }
            if(!oldContent.contains("appcoins.sdk.billing.Purchase")){
                toImport=toImport.concat("\nimport com.appcoins.sdk.billing.Purchase;");
            }
        }
        newContent=newContent.insert(classIndex+1,listenerAndMethod);
        newContent=newContent.insert(importIndex,toImport);

        // Writing build.gradle file changes

        String finalContent = String.valueOf(newContent);
        Runnable r2 = () -> {
            document.setReadOnly(false);
            document.setText(finalContent);
        };
        WriteCommandAction.runWriteCommandAction(project, r2);
    }
}
