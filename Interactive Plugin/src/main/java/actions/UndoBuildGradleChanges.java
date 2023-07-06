package actions;

import dialogs.DialogCreator;
import com.intellij.openapi.command.WriteCommandAction;
import com.intellij.openapi.editor.Document;
import com.intellij.openapi.fileEditor.FileDocumentManager;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.vfs.VirtualFile;
import com.intellij.openapi.wm.ToolWindow;
import com.intellij.ui.content.Content;
import com.intellij.ui.content.ContentFactory;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.util.Map;

public class UndoBuildGradleChanges extends AbstractAction {
    private Project project;
    private Map<Integer, VirtualFile> files;
    private ToolWindow toolWindow;
    private String oldContent;
    public UndoBuildGradleChanges(Project project, Map<Integer, VirtualFile> files, ToolWindow toolWindow, String oldContent){
        super("No, I want to implement manually");
        this.project=project;
        this.files=files;
        this.toolWindow=toolWindow;
        this.oldContent=oldContent;
    }
    @Override
    public void actionPerformed(ActionEvent actionEvent) {
        Document buildGradleDocument = FileDocumentManager.getInstance().getDocument(files.get(2));
        Runnable r = () -> {
            buildGradleDocument.setReadOnly(false);
            buildGradleDocument.setText(oldContent);
        };
        WriteCommandAction.runWriteCommandAction(project, r);
        toolWindow.getContentManager().removeAllContents(true);
        ContentFactory contentFactory = ContentFactory.SERVICE.getInstance();
        Content content = contentFactory.createContent(DialogCreator.buildGradleManual(project,files,toolWindow), "", false);
        toolWindow.getContentManager().addContent(content);
    }
}
