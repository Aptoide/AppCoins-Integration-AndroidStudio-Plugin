package actions;

import com.intellij.openapi.command.WriteCommandAction;
import com.intellij.openapi.editor.Document;
import com.intellij.openapi.fileEditor.FileDocumentManager;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.vfs.VirtualFile;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.util.Map;

public class ImplementAndroidManifestAppCoinsChanges extends AbstractAction {
    private Project project;
    private Map<Integer, VirtualFile> files;
    private String oldContent;
    public ImplementAndroidManifestAppCoinsChanges(Project project, Map<Integer, VirtualFile> files){
        super("Static");
        this.project=project;
        this.files=files;
    }
    @Override
    public void actionPerformed(ActionEvent actionEvent) {
        Document androidManifestDocument = FileDocumentManager.getInstance().getDocument(files.get(3));
        oldContent = androidManifestDocument.getText();
        StringBuffer newContent = new StringBuffer();
        newContent.append(oldContent);

        if(oldContent.contains("com.android.vending.BILLING")){
            int dependencyIndex= oldContent.indexOf("com.android.vending.BILLING");
            String auxString = oldContent.substring(0,dependencyIndex);
            int firstIndex= auxString.lastIndexOf("<");
            int lastIndex = oldContent.indexOf(">",dependencyIndex);
            newContent.delete(firstIndex,lastIndex+1);
        }
        if(!oldContent.contains("com.appcoins.BILLING")){
            int insertIndex= newContent.indexOf("<application")-1;
            newContent.insert(insertIndex,"\n\t<uses-permission android:name=\"com.appcoins.BILLING\" />\n");
        }
        if(!oldContent.contains("android.permission.INTERNET")){
            int insertIndex= newContent.indexOf("<application")-1;
            newContent.insert(insertIndex,"\n\t<uses-permission android:name=\"android.permission.INTERNET\" />\n");
        }

        String finalAndroidManifestContent = newContent.toString();
        Runnable r2 = () -> {
            androidManifestDocument.setReadOnly(false);
            androidManifestDocument.setText(finalAndroidManifestContent);
        };
        WriteCommandAction.runWriteCommandAction(project, r2);
    }
}
