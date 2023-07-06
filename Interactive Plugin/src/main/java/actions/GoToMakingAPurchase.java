package actions;

import dialogs.DialogCreator;
import com.intellij.openapi.editor.Document;
import com.intellij.openapi.fileEditor.FileDocumentManager;
import com.intellij.openapi.fileEditor.FileEditorManager;
import com.intellij.openapi.fileEditor.OpenFileDescriptor;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.vfs.VirtualFile;
import com.intellij.openapi.wm.ToolWindow;
import com.intellij.ui.content.Content;
import com.intellij.ui.content.ContentFactory;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.util.Map;

public class GoToMakingAPurchase extends AbstractAction {
    private Project project;
    private Map<Integer, VirtualFile> files;
    private ToolWindow toolWindow;
    public GoToMakingAPurchase(Project project, Map<Integer, VirtualFile> files, ToolWindow toolWindow){
        super("Continue implementation");
        this.project=project;
        this.files = files;
        this.toolWindow = toolWindow;
    }
    @Override
    public void actionPerformed(ActionEvent actionEvent) {
        VirtualFile file = files.get(5);
        Document document = FileDocumentManager.getInstance().getDocument(file);
        String text = document.getText();
        int index= text.indexOf("launchPurchaseFlow");
        FileEditorManager.getInstance(project).openTextEditor(
                new OpenFileDescriptor(project,file,index),
                true // request focus to editor
        );
        toolWindow.getContentManager().removeAllContents(true);
        ContentFactory contentFactory = ContentFactory.SERVICE.getInstance();
        Content content = contentFactory.createContent(DialogCreator.makingAPurchaseDialog(project,files,toolWindow), "", false);
        toolWindow.getContentManager().addContent(content);
    }
}
