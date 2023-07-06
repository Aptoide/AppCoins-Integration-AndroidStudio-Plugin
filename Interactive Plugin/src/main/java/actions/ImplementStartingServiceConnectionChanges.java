package actions;

import dialogs.DialogCreator;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.vfs.VirtualFile;
import com.intellij.openapi.wm.ToolWindow;
import com.intellij.ui.content.Content;
import com.intellij.ui.content.ContentFactory;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.util.Map;

public class ImplementStartingServiceConnectionChanges extends AbstractAction {
    private Project project;
    private Map<Integer, VirtualFile> files;
    private ToolWindow toolWindow;
    public ImplementStartingServiceConnectionChanges(Project project, Map<Integer, VirtualFile> files, ToolWindow toolWindow){
        super("Partially implement changes automatically");
        this.project=project;
        this.files=files;
        this.toolWindow=toolWindow;
    }

    @Override
    public void actionPerformed(ActionEvent actionEvent) {
        toolWindow.getContentManager().removeAllContents(true);
        ContentFactory contentFactory = ContentFactory.SERVICE.getInstance();
        Content content = contentFactory.createContent(DialogCreator.getKey(project,files,toolWindow), "", false);
        toolWindow.getContentManager().addContent(content);
    }
}
