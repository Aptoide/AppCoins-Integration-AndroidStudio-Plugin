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

public class UndoMakingAPurchaseChanges extends AbstractAction {
    private Project project;
    private Map<Integer, VirtualFile> files;
    private ToolWindow toolWindow;
    private String oldReferenceContent;
    private String oldDeclarationContent;
    public UndoMakingAPurchaseChanges(Project project, Map<Integer, VirtualFile> files, ToolWindow toolWindow, String oldReferenceContent, String oldDeclarationContent){
        super("No, I want to implement manually");
        this.project=project;
        this.files=files;
        this.toolWindow=toolWindow;
        this.oldReferenceContent=oldReferenceContent;
        this.oldDeclarationContent=oldDeclarationContent;
    }
    @Override
    public void actionPerformed(ActionEvent actionEvent) {
        Document referenceDocument = FileDocumentManager.getInstance().getDocument(files.get(5));
        Document declarationDocument = FileDocumentManager.getInstance().getDocument(files.get(7));
        Runnable r = () -> {
            referenceDocument.setReadOnly(false);
            referenceDocument.setText(oldReferenceContent);
            declarationDocument.setReadOnly(false);
            declarationDocument.setText(oldDeclarationContent);
        };
        WriteCommandAction.runWriteCommandAction(project, r);
        toolWindow.getContentManager().removeAllContents(true);
        ContentFactory contentFactory = ContentFactory.SERVICE.getInstance();
        Content content = contentFactory.createContent(DialogCreator.makingAPurchaseManual(project,files,toolWindow), "", false);
        toolWindow.getContentManager().addContent(content);
    }
}
