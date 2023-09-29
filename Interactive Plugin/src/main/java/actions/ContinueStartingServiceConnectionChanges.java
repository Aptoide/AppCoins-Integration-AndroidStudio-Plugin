package actions;

import com.intellij.openapi.command.WriteCommandAction;
import com.intellij.openapi.editor.Document;
import com.intellij.openapi.fileEditor.FileDocumentManager;
import com.intellij.openapi.fileEditor.FileEditorManager;
import com.intellij.openapi.fileEditor.OpenFileDescriptor;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.vfs.VirtualFile;
import com.intellij.openapi.wm.ToolWindow;
import snipets.Snippets;
import window_factory.BillingToolWindowFactory;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.util.Map;

public class ContinueStartingServiceConnectionChanges extends AbstractAction {
    private Project project;
    private Map<Integer, VirtualFile> files;
    private ToolWindow toolWindow;
    private JTextField key;
    public ContinueStartingServiceConnectionChanges(Project project, Map<Integer, VirtualFile> files, ToolWindow toolWindow, JTextField key){
        super("Implement");
        this.project=project;
        this.files=files;
        this.toolWindow=toolWindow;
        this.key=key;
    }
    @Override
    public void actionPerformed(ActionEvent actionEvent) {
        Snippets snippets = BillingToolWindowFactory.snippets;
        String keyString=key.getText();
        Document onCreateDocument = FileDocumentManager.getInstance().getDocument(files.get(4));
        String oldContent = onCreateDocument.getText();
        StringBuffer newOnCreateContent = new StringBuffer();
        newOnCreateContent.append(onCreateDocument.getText());
        String listeners = "";
        String toImport = "";
        String toOnCreate = "";
        String variables = "";
        int classIndex = newOnCreateContent.indexOf("{");
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
        if (!oldContent.contains("AppCoinsBillingStateListener")){
            listeners= listeners.concat(snippets.appCoinsBillingStateListener());
            toImport=toImport.concat(
                    "\nimport com.appcoins.sdk.billing.listeners.AppCoinsBillingStateListener;\n" +
                    "import com.appcoins.sdk.billing.Purchase;\n" +
                    "import com.appcoins.sdk.billing.PurchasesResult;\n" +
                    "import com.appcoins.sdk.billing.ResponseCode;\n" +
                    "import com.appcoins.sdk.billing.types.SkuType;");
            if(!oldContent.contains("android.util.Log")){
                toImport=toImport.concat("\nimport android.util.Log;");
            }
        }

        if(!oldContent.contains("String base64EncodedPublicKey")){
            variables = variables.concat("\n\tString base64EncodedPublicKey = \"" + keyString + "\";");
        }

        if(!oldContent.contains("AppcoinsBillingClient cab")){
            variables = variables.concat("\n\tAppcoinsBillingClient cab;");
        }

        if(!oldContent.contains("String TAG")){
            variables = variables.concat("\n\tprivate static final String TAG = \"\";");
        }

        if (!oldContent.contains("CatapultBillingAppCoinsFactory.BuildAppcoinsBilling")){
            toOnCreate = toOnCreate.concat("\tcab = CatapultBillingAppCoinsFactory.BuildAppcoinsBilling(this, "+"base64EncodedPublicKey"+", purchasesUpdatedListener);\n"+
                    "\t\tcab.startConnection(appCoinsBillingStateListener);\n\t");
            toImport=toImport.concat("\nimport com.appcoins.sdk.billing.helpers.CatapultBillingAppCoinsFactory;");
        }
        newOnCreateContent.insert(lastIndex,toOnCreate);
        if(!oldContent.contains("super.onCreate")){
            newOnCreateContent.insert(superIndex,"\n\tsuper.onCreate(savedInstanceState);\n");
        }
        newOnCreateContent.insert(classIndex+1,listeners);
        if (!oldContent.contains("AppcoinsBillingClient")){
            variables = variables.concat("\n\tprivate AppcoinsBillingClient cab;");
            toImport=toImport.concat("\nimport com.appcoins.sdk.billing.AppcoinsBillingClient;");
        }
        newOnCreateContent.insert(classIndex+1,variables);
        if(oldContent.contains("String base64EncodedPublicKey")){
            int keyIndex = newOnCreateContent.indexOf("MIIBI");
            if(keyIndex!=-1){
                newOnCreateContent.replace(keyIndex,newOnCreateContent.indexOf(String.valueOf(newOnCreateContent.charAt(keyIndex-1)),keyIndex),keyString);
            }
        }
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
