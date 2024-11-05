package actions;

import com.intellij.openapi.command.WriteCommandAction;
import com.intellij.openapi.editor.Document;
import com.intellij.openapi.fileEditor.FileDocumentManager;
import com.intellij.openapi.fileEditor.FileEditorManager;
import com.intellij.openapi.fileEditor.OpenFileDescriptor;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.vfs.VirtualFile;
import com.intellij.openapi.wm.ToolWindow;
import window_factory.BillingToolWindowFactory;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.util.Map;

public class ImplementPurchaseFinishedListenerChanges extends AbstractAction {
    private Project project;
    private Map<Integer, VirtualFile> files;
    private ToolWindow toolWindow;
    private JTextField key;
    public ImplementPurchaseFinishedListenerChanges(Project project, Map<Integer, VirtualFile> files, ToolWindow toolWindow){
        super("Static");
        this.project=project;
        this.files=files;
        this.toolWindow=toolWindow;
    }
    @Override
    public void actionPerformed(ActionEvent actionEvent) {
        Document onCreateDocument = FileDocumentManager.getInstance().getDocument(files.get(4));
        String oldContent = onCreateDocument.getText();
        StringBuffer newOnCreateContent = new StringBuffer();
        newOnCreateContent.append(onCreateDocument.getText());
        String listeners = "";
        String toImport = "";
        String toOnCreate = "";
        String variables = "";
        int classIndex = newOnCreateContent.indexOf("{");
        if(oldContent.contains("cab")){
            if (BillingToolWindowFactory.projectIsKotlin()) {
                classIndex=newOnCreateContent.indexOf("AppcoinsBillingClient",newOnCreateContent.indexOf("cab"));
                classIndex += "AppcoinsBillingClient".length();
            } else {
                classIndex = newOnCreateContent.indexOf(";", newOnCreateContent.indexOf("cab"));
            }
        }
        FileEditorManager.getInstance(project).openTextEditor(
                new OpenFileDescriptor(project,files.get(4),classIndex),
                true // request focus to editor
        );
        int importIndex = newOnCreateContent.indexOf("\n",newOnCreateContent.lastIndexOf("import "));
        int firstIndex= 0;
        int lastIndex=0;
        while(firstIndex!=-1){
            firstIndex = newOnCreateContent.indexOf("@Override",lastIndex);
            lastIndex= newOnCreateContent.indexOf("{", firstIndex);
            String method = newOnCreateContent.substring(firstIndex,lastIndex);
            if ((method.contains(" public ") ||
                    method.contains(" protected ") ||
                    method.contains(" private ")) &&
                    method.contains(" void ") && method.contains(" onCreate")){
                break;
            }
        }
        lastIndex++;
        int superIndex=lastIndex;
        int counter=0;
        while (lastIndex< newOnCreateContent.length()){
            if(newOnCreateContent.charAt(lastIndex)=='}'){
                if(counter==0){
                    break;
                }
                else{
                    counter--;
                }
            }
            else if(newOnCreateContent.charAt(lastIndex)=='{'){
                counter++;
            }
            lastIndex++;
        }

        if (!oldContent.contains("PurchasesUpdatedListener")){
            listeners = listeners.concat(BillingToolWindowFactory.snippets.onPurchasesUpdated());
            toImport=toImport.concat("\nimport com.appcoins.sdk.billing.PurchasesUpdatedListener;"
                    + "\nimport com.appcoins.sdk.billing.Purchase;");
            if(!oldContent.contains("android.app.AlertDialog")){
                toImport=toImport.concat("\nimport android.app.AlertDialog;");
            }
        }

        newOnCreateContent.insert(classIndex+1,listeners);
        newOnCreateContent.insert(importIndex,toImport);

        // Writing build.gradle file changes

        String finalOnCreateContent = String.valueOf(newOnCreateContent);
        Runnable r2 = () -> {
            onCreateDocument.setReadOnly(false);
            onCreateDocument.setText(finalOnCreateContent);
        };
        WriteCommandAction.runWriteCommandAction(project, r2);
    }
}
