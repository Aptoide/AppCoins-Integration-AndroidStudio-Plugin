package actions;

import com.intellij.openapi.command.WriteCommandAction;
import com.intellij.openapi.editor.Document;
import com.intellij.openapi.fileEditor.FileDocumentManager;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.vfs.VirtualFile;
import snipets.Snippets;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.util.Map;

public class ImplementAndroidManifestQueriesChanges extends AbstractAction {
    private Project project;
    private Map<Integer, VirtualFile> files;
    private Snippets snippets;
    private String oldContent;
    public ImplementAndroidManifestQueriesChanges(Project project, Map<Integer, VirtualFile> files, Snippets snippets){
        super("Static");
        this.project=project;
        this.files=files;
        this.snippets=snippets;
    }
    @Override
    public void actionPerformed(ActionEvent actionEvent) {
        Document androidManifestDocument = FileDocumentManager.getInstance().getDocument(files.get(3));
        oldContent = androidManifestDocument.getText();
        StringBuffer newContent = new StringBuffer();
        newContent.append(oldContent);

        if(!oldContent.contains("android:name=\"com.appcoins.wallet\"")){
            if(!oldContent.contains("<queries>")){
                int insertIndex= newContent.indexOf("<application")-1;
                newContent.insert(insertIndex,"\t<queries>\n\t" +
                        "<package android:name=\"com.appcoins.wallet\" />\n" +
                        "\t</queries>\n");
            }
            else{
                int insertIndex= newContent.indexOf("<queries>")+ "<queries>".length();
                newContent.insert(insertIndex,
                        "\n\t\t<package android:name=\"com.appcoins.wallet\" />\n");
            }
        }
        String finalAndroidManifestContent = newContent.toString();
        Runnable r2 = () -> {
            androidManifestDocument.setReadOnly(false);
            androidManifestDocument.setText(finalAndroidManifestContent);
        };
        WriteCommandAction.runWriteCommandAction(project, r2);
    }
}
