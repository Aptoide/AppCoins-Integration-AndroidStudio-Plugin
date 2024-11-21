package actions;

import com.intellij.openapi.command.WriteCommandAction;
import com.intellij.openapi.editor.Document;
import com.intellij.openapi.fileEditor.FileDocumentManager;
import com.intellij.openapi.fileEditor.FileEditorManager;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.vfs.VirtualFile;
import snipets.Snippets;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.util.Map;

public class ImplementBuildGradleAllProjectChanges extends AbstractAction {
    private Project project;
    private Map<Integer, VirtualFile> files;

    private Snippets snippets;
    private String oldContent;
    public ImplementBuildGradleAllProjectChanges(Project project, Map<Integer, VirtualFile> files, Snippets snippets){
        super("Static");
        this.project=project;
        this.files=files;
        this.snippets=snippets;
    }
    @Override
    public void actionPerformed(ActionEvent actionEvent) {

        VirtualFile selectedFile = FileEditorManager.getInstance(project).getSelectedFiles()[0];
        Document document = FileDocumentManager.getInstance().getDocument(selectedFile);

        if (document != null) {
            String oldContent = document.getText();
            String newContent = oldContent + snippets.buildGradleCodeAllprojects();

            Runnable writeRunnable = () -> {
                document.setReadOnly(false);
                document.setText(newContent);
            };
            WriteCommandAction.runWriteCommandAction(project, writeRunnable);
        }


            /**Document buildGradleDocument = FileDocumentManager.getInstance().getDocument(files.get(2));
            String oldBuildGradleContent = buildGradleDocument.getText();
            this.oldContent=oldBuildGradleContent;
            String newBuildGradleContent = oldBuildGradleContent;
            if (!oldBuildGradleContent.contains("google()")||!oldBuildGradleContent.contains("mavenCentral()")){
                newBuildGradleContent = newBuildGradleContent.concat(
                        snippets.buildGradleCodeAllprojects()
                );
            }


            // Writing build.gradle file changes

            String finalBuildGradleContent = newBuildGradleContent;
            Runnable r2 = () -> {
                buildGradleDocument.setReadOnly(false);
                buildGradleDocument.setText(finalBuildGradleContent);
            };
            WriteCommandAction.runWriteCommandAction(project, r2);
             **/
    }
}
