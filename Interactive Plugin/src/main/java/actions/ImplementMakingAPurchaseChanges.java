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

public class ImplementMakingAPurchaseChanges extends AbstractAction {
    private Project project;
    private Map<Integer, VirtualFile> files;
    private ToolWindow toolWindow;
    public ImplementMakingAPurchaseChanges(Project project, Map<Integer, VirtualFile> files, ToolWindow toolWindow){
        super("Add code to detected purchases");
        this.project=project;
        this.files=files;
        this.toolWindow=toolWindow;
    }

    @Override
    public void actionPerformed(ActionEvent actionEvent) {
        VirtualFile referenceFile = files.get(5);
        VirtualFile declarationFile = files.get(7);
        if(referenceFile.getName()!=declarationFile.getName()){
            Document referenceDocument = FileDocumentManager.getInstance().getDocument(referenceFile);
            Document declarationDocument = FileDocumentManager.getInstance().getDocument(declarationFile);
            String oldReferenceContent = referenceDocument.getText();
            StringBuffer newReferenceContent = new StringBuffer();
            newReferenceContent.append(oldReferenceContent);
            String oldDeclarationContent = declarationDocument.getText();
            StringBuffer newDeclarationContent = new StringBuffer();
            newDeclarationContent.append(oldDeclarationContent);
            int index=0;
            int googleIndex= oldReferenceContent.indexOf(".launchPurchaseFlow");
            FileEditorManager.getInstance(project).openTextEditor(
                    new OpenFileDescriptor(project,referenceFile,googleIndex),
                    true // request focus to editor
            );
            if(!oldDeclarationContent.contains("com.appcoins.sdk.billing.BillingFlowParams")){
                int importIndex = newDeclarationContent.indexOf("\n",newDeclarationContent.lastIndexOf("import "));
                newDeclarationContent.insert(importIndex,"\nimport com.appcoins.sdk.billing.BillingFlowParams;\n");
            }
            if(!oldDeclarationContent.contains("startPurchase")){
                int classIndex;
                if(oldDeclarationContent.contains("cab")&&!isCommented(oldDeclarationContent.indexOf("cab"),oldDeclarationContent)){
                    if (BillingToolWindowFactory.projectIsKotlin()) {
                        classIndex=newDeclarationContent.indexOf("AppcoinsBillingClient",newDeclarationContent.indexOf("cab"));
                        classIndex += "AppcoinsBillingClient".length();
                    } else {
                        classIndex = newDeclarationContent.indexOf(";", newDeclarationContent.indexOf("cab"));
                    }
                }
                else{
                    classIndex = newDeclarationContent.indexOf("{");
                    while(true){
                        if(!isCommented(classIndex,newDeclarationContent.toString())){
                            break;
                        }
                        classIndex = newDeclarationContent.indexOf("{",classIndex+1);
                    }
                }
                String method = BillingToolWindowFactory.snippets.startPurchase();
                newDeclarationContent.insert(classIndex+1,method);
                index = newDeclarationContent.indexOf("launchPurchaseFlow");
                while(index!=-1){
                    if(!isCommented(index,newDeclarationContent.toString())){
                        if(newDeclarationContent.indexOf("{",index)!=-1){
                            if(newDeclarationContent.indexOf(";",index)==-1 || newDeclarationContent.indexOf("{",index)<newDeclarationContent.indexOf(";",index)){
                                String beforeMethod = newDeclarationContent.substring(0,index);
                                int firstIndex= beforeMethod.lastIndexOf('\n');
                                int lastIndex = newDeclarationContent.indexOf("{",firstIndex)+1;
                                int count=0;
                                while (true){
                                    if(newDeclarationContent.indexOf("{",lastIndex)<newDeclarationContent.indexOf("}",lastIndex)){
                                        lastIndex= newDeclarationContent.indexOf("{",lastIndex)+1;
                                        count++;
                                    }
                                    else if(count==0){
                                        lastIndex= newDeclarationContent.indexOf("}",lastIndex)+1;
                                        break;
                                    }
                                    else{
                                        lastIndex= newDeclarationContent.indexOf("}",lastIndex)+1;
                                        count--;
                                    }
                                }
                                newDeclarationContent.insert(lastIndex,"*/");
                                newDeclarationContent.insert(firstIndex,"/*");
                            }
                            else{
                                index= newDeclarationContent.indexOf(";",index);
                            }
                        }
                        else{
                            break;
                        }
                    }
                    index = newDeclarationContent.indexOf("launchPurchaseFlow",index+1);
                }
                index=newReferenceContent.indexOf(".launchPurchaseFlow");
                while(index!=-1){
                    if(!isCommented(index,newReferenceContent.toString())){
                        String beforePurchase = newReferenceContent.substring(0,index);
                        int firstIndex = beforePurchase.lastIndexOf('\n');
                        String billingClass = newReferenceContent.substring(firstIndex+1,index);
                        int finalIndex= newReferenceContent.indexOf(";",index);
                        int commentIndex = newReferenceContent.indexOf("*/",index);
                        if(commentIndex==finalIndex+1){
                            newReferenceContent.insert(commentIndex+2,"\n\t\t"+billingClass+".startPurchase(sku,developerPayload);\n");
                        }
                        else{
                            newReferenceContent.insert(finalIndex+1,"*/\n\t\t"+billingClass+".startPurchase(sku,developerPayload);\n");
                            newReferenceContent.insert(firstIndex+1,"/*");
                        }
                    }
                    index = newReferenceContent.indexOf(".launchPurchaseFlow",index+1);
                }
            }
            String finalReferenceContent = newReferenceContent.toString();
            String finalDeclarationContent = newDeclarationContent.toString();
            Runnable r = () -> {
                referenceDocument.setReadOnly(false);
                referenceDocument.setText(finalReferenceContent);
            };
            Runnable r2 = () -> {
                declarationDocument.setReadOnly(false);
                declarationDocument.setText(finalDeclarationContent);
            };
            if(index!=0){
                FileEditorManager.getInstance(project).openTextEditor(
                        new OpenFileDescriptor(project,referenceFile,index),
                        true // request focus to editor
                );
            }
            WriteCommandAction.runWriteCommandAction(project, r);
            WriteCommandAction.runWriteCommandAction(project, r2);
        }
        else{
            Document document = FileDocumentManager.getInstance().getDocument(referenceFile);
            String oldContent = document.getText();
            StringBuffer newContent = new StringBuffer();
            newContent.append(oldContent);
            int index=0;
            int googleIndex= oldContent.indexOf(".launchPurchaseFlow");
            FileEditorManager.getInstance(project).openTextEditor(
                    new OpenFileDescriptor(project,referenceFile,googleIndex),
                    true // request focus to editor
            );
            if(!oldContent.contains("com.appcoins.sdk.billing.BillingFlowParams")){
                int importIndex = newContent.indexOf("\n",newContent.lastIndexOf("import "));
                newContent.insert(importIndex,"\nimport com.appcoins.sdk.billing.BillingFlowParams;\n");
            }
            if(!oldContent.contains("startPurchase")){
                int classIndex;
                if(oldContent.contains("cab")&&!isCommented(oldContent.indexOf("cab"),oldContent)){
                    if (BillingToolWindowFactory.projectIsKotlin()) {
                        classIndex=newContent.indexOf("AppcoinsBillingClient",newContent.indexOf("cab"));
                        classIndex += "AppcoinsBillingClient".length();
                    } else {
                        classIndex = newContent.indexOf(";", newContent.indexOf("cab"));
                    }
                }
                else{
                    classIndex = newContent.indexOf("{");
                    while(true){
                        if(!isCommented(classIndex,newContent.toString())){
                            break;
                        }
                        classIndex = newContent.indexOf("{",classIndex+1);
                    }
                }
                String method = BillingToolWindowFactory.snippets.startPurchase();
                newContent.insert(classIndex+1,method);
                index = newContent.indexOf("launchPurchaseFlow");
                while(index!=-1){
                    if(!isCommented(index,newContent.toString())){
                        if(newContent.indexOf("{",index)!=-1){
                            if(newContent.indexOf(";",index)==-1 || newContent.indexOf("{",index)<newContent.indexOf(";",index)){
                                String beforeMethod = newContent.substring(0,index);
                                int firstIndex= beforeMethod.lastIndexOf('\n');
                                int lastIndex = newContent.indexOf("{",firstIndex)+1;
                                int count=0;
                                while (true){
                                    if(newContent.indexOf("{",lastIndex)<newContent.indexOf("}",lastIndex)){
                                        lastIndex= newContent.indexOf("{",lastIndex)+1;
                                        count++;
                                    }
                                    else if(count==0){
                                        lastIndex= newContent.indexOf("}",lastIndex)+1;
                                        break;
                                    }
                                    else{
                                        lastIndex= newContent.indexOf("}",lastIndex)+1;
                                        count--;
                                    }
                                }
                                newContent.insert(lastIndex,"*/");
                                newContent.insert(firstIndex,"/*");
                            }
                            else{
                                index= newContent.indexOf(";",index);
                            }
                        }
                        else{
                            break;
                        }
                    }
                    index = newContent.indexOf("launchPurchaseFlow",index+1);
                }
                index=newContent.indexOf(".launchPurchaseFlow");
                while(index!=-1){
                    if(!isCommented(index,newContent.toString())){
                        String beforePurchase = newContent.substring(0,index);
                        int firstIndex = beforePurchase.lastIndexOf('\n');
                        String billingClass = newContent.substring(firstIndex+1,index);
                        int finalIndex= newContent.indexOf(";",index);
                        int commentIndex = newContent.indexOf("*/",index);
                        if(commentIndex==finalIndex+1){
                            newContent.insert(commentIndex+2,"\n\t\t"+billingClass+".startPurchase(sku,developerPayload);\n");
                        }
                        else{
                            newContent.insert(finalIndex+1,"*/\n\t\t"+billingClass+".startPurchase(sku,developerPayload);\n");
                            newContent.insert(firstIndex+1,"/*");
                        }
                    }
                    index = newContent.indexOf(".launchPurchaseFlow",index+1);
                }
            }
            String finalContent = newContent.toString();
            Runnable r = () -> {
                document.setReadOnly(false);
                document.setText(finalContent);
            };
            if(index!=0){
                FileEditorManager.getInstance(project).openTextEditor(
                        new OpenFileDescriptor(project,referenceFile,index),
                        true // request focus to editor
                );
            }
            WriteCommandAction.runWriteCommandAction(project, r);
        }
    }

    private boolean isCommented(int index, String content){
        String beforeMethod = content.substring(0,index);
        if(beforeMethod.lastIndexOf("//")!=-1 && beforeMethod.lastIndexOf("\n")<beforeMethod.lastIndexOf("//")){
            return true;
        }
        String afterMethod = content.substring(index);
        if(!afterMethod.contains("/*")){
            if(afterMethod.contains("*/")){
                return true;
            }
            else{
                return false;
            }
        }
        else{
            if(afterMethod.indexOf("*/")!=-1 && afterMethod.indexOf("*/")<afterMethod.indexOf("/*")){
                return true;
            }
        }
        return false;
    }
}
