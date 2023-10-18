package actions;

import com.intellij.openapi.application.ApplicationManager;
import com.intellij.openapi.editor.Document;
import com.intellij.openapi.fileEditor.FileDocumentManager;
import com.intellij.openapi.fileEditor.FileEditorManager;
import com.intellij.openapi.fileEditor.OpenFileDescriptor;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.vfs.VirtualFile;
import com.intellij.openapi.wm.ToolWindow;
import com.intellij.ui.content.Content;
import com.intellij.ui.content.ContentFactory;
import dialogs.DialogCreator;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.util.Map;

public class GoToOnActivityResult extends AbstractAction {
    private Project project;
    private Map<Integer, VirtualFile> files;
    private ToolWindow toolWindow;
    public GoToOnActivityResult(Project project, Map<Integer, VirtualFile> files, ToolWindow toolWindow){
        super("Implement manually");
        this.project=project;
        this.files = files;
        this.toolWindow = toolWindow;
    }
    @Override
    public void actionPerformed(ActionEvent actionEvent) {
        VirtualFile file = files.get(4);
        if (file!=null){
            Document document = FileDocumentManager.getInstance().getDocument(file);
            String text = document.getText();
            int index= text.indexOf("onActivityResult");
            if(index!=-1){
                FileEditorManager.getInstance(project).openTextEditor(
                        new OpenFileDescriptor(project,file,index),
                        true // request focus to editor
                );
                toolWindow.getContentManager().removeAllContents(true);
                ContentFactory contentFactory = ApplicationManager.getApplication().getService(ContentFactory.class);
                Content content = contentFactory.createContent(DialogCreator.onActivityResultManual(project,files,toolWindow), "", false);
                toolWindow.getContentManager().addContent(content);
            }
            else{
                FileEditorManager.getInstance(project).openTextEditor(
                        new OpenFileDescriptor(project,file),
                        true // request focus to editor
                );
                toolWindow.getContentManager().removeAllContents(true);
                ContentFactory contentFactory = ApplicationManager.getApplication().getService(ContentFactory.class);
                Content content = contentFactory.createContent(DialogCreator.noOnActivityResult(project,files,toolWindow), "", false);
                toolWindow.getContentManager().addContent(content);
            }
        }
        else{
            toolWindow.getContentManager().removeAllContents(true);
            ContentFactory contentFactory = ApplicationManager.getApplication().getService(ContentFactory.class);
            Content content = contentFactory.createContent(DialogCreator.noOnActivityResult(project,files,toolWindow), "", false);
            toolWindow.getContentManager().addContent(content);
        }
    }
}
