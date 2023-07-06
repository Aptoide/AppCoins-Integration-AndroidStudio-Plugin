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

public class ImplementCreateOSPIntent extends AbstractAction {
    private final int MAIN_ACTIVITY_FILE = 1;
    private Project project;
    private Map<Integer, VirtualFile> files;
    private ToolWindow toolWindow;

    public ImplementCreateOSPIntent(Project project, Map<Integer, VirtualFile> files, ToolWindow toolWindow){
        super("Implement automatically");
        this.project=project;
        this.files=files;
        this.toolWindow=toolWindow;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        Document document = FileDocumentManager.getInstance().getDocument(files.get(MAIN_ACTIVITY_FILE));
        StringBuffer newContent = new StringBuffer();
        newContent.append(document.getText());
        String listenerAndMethod = "";
        int classIndex = newContent.indexOf("{");
        FileEditorManager.getInstance(project).openTextEditor(
                new OpenFileDescriptor(project,files.get(1),classIndex),
                true // request focus to editor
        );

        listenerAndMethod = listenerAndMethod.concat(BillingToolWindowFactory.snippets.ospIntent());
        StringBuffer newContentListenerAndMethod = newContent.insert(classIndex+1,listenerAndMethod);

        String finalContentListenerAndMethod = String.valueOf(newContentListenerAndMethod);

        Runnable r2 = () -> {
            document.setReadOnly(false);
            document.setText(finalContentListenerAndMethod);
        };
        WriteCommandAction.runWriteCommandAction(project, r2);
    }
}
