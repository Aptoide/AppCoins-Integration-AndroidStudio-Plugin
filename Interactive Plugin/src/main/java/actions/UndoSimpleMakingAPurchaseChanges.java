package actions;

import com.intellij.openapi.application.ApplicationManager;
import com.intellij.openapi.command.WriteCommandAction;
import com.intellij.openapi.editor.Document;
import com.intellij.openapi.fileEditor.FileDocumentManager;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.vfs.VirtualFile;
import com.intellij.openapi.wm.ToolWindow;
import com.intellij.ui.content.Content;
import com.intellij.ui.content.ContentFactory;
import dialogs.DialogCreator;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.util.Map;

public class UndoSimpleMakingAPurchaseChanges extends AbstractAction {
    private Project project;
    private Map<Integer, VirtualFile> files;
    private ToolWindow toolWindow;
    private String oldContent;
    public UndoSimpleMakingAPurchaseChanges(Project project, Map<Integer, VirtualFile> files, ToolWindow toolWindow, String oldContent){
        super("No, I want to implement manually");
        this.project=project;
        this.files=files;
        this.toolWindow=toolWindow;
        this.oldContent=oldContent;
    }
    @Override
    public void actionPerformed(ActionEvent actionEvent) {
        Document document = FileDocumentManager.getInstance().getDocument(files.get(5));
        Runnable r = () -> {
            document.setReadOnly(false);
            document.setText(oldContent);
        };
        WriteCommandAction.runWriteCommandAction(project, r);
        toolWindow.getContentManager().removeAllContents(true);
        ContentFactory contentFactory = ApplicationManager.getApplication().getService(ContentFactory.class);
        Content content = contentFactory.createContent(DialogCreator.makingAPurchaseManual(project,files,toolWindow), "", false);
        toolWindow.getContentManager().addContent(content);
    }
}
