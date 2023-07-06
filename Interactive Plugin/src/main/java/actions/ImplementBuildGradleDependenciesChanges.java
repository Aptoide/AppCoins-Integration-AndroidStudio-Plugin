package actions;

import com.intellij.openapi.command.WriteCommandAction;
import com.intellij.openapi.editor.Document;
import com.intellij.openapi.fileEditor.FileDocumentManager;
import com.intellij.openapi.fileEditor.FileEditorManager;
import com.intellij.openapi.fileEditor.OpenFileDescriptor;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.vfs.VirtualFile;
import snipets.Snippets;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.util.Map;

public class ImplementBuildGradleDependenciesChanges extends AbstractAction {
    private Project project;
    private Map<Integer, VirtualFile> files;

    private Snippets snippets;
    private String oldContent;
    public ImplementBuildGradleDependenciesChanges(Project project, Map<Integer, VirtualFile> files, Snippets snippets){
        super("Implement automatically");
        this.project=project;
        this.files=files;
        this.snippets=snippets;
    }
    @Override
    public void actionPerformed(ActionEvent actionEvent) {
        Document buildGradleDocument = FileDocumentManager.getInstance().getDocument(files.get(2));
        String oldBuildGradleContent = buildGradleDocument.getText();
        this.oldContent=oldBuildGradleContent;
        String newBuildGradleContent = oldBuildGradleContent;
        if (!oldBuildGradleContent.contains(snippets.appCoinsBillingDependency())) {
            int dependenciesFirstIndex = oldBuildGradleContent.indexOf("dependencies");
            dependenciesFirstIndex = oldBuildGradleContent.indexOf("{", dependenciesFirstIndex);
            int dependenciesLastIndex = oldBuildGradleContent.indexOf("}", dependenciesFirstIndex);
            String oldDependencies = oldBuildGradleContent.substring(dependenciesFirstIndex + 1, dependenciesLastIndex - 1);
            String newDependencies = oldDependencies.concat(snippets.appCoinsBillingDependency());
            newBuildGradleContent = newBuildGradleContent.replace(oldDependencies, newDependencies);
            FileEditorManager.getInstance(project).openTextEditor(
                    new OpenFileDescriptor(project,files.get(2),dependenciesLastIndex),
                    true // request focus to editor
            );
        }


        // Writing build.gradle file changes

        String finalBuildGradleContent = newBuildGradleContent;
        Runnable r2 = () -> {
            buildGradleDocument.setReadOnly(false);
            buildGradleDocument.setText(finalBuildGradleContent);
        };
        WriteCommandAction.runWriteCommandAction(project, r2);
    }
}
