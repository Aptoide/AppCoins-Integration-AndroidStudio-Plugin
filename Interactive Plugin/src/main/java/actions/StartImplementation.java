package actions;

import com.intellij.openapi.application.ApplicationManager;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.vfs.VirtualFile;
import com.intellij.openapi.wm.ToolWindow;
import com.intellij.ui.content.Content;
import com.intellij.ui.content.ContentFactory;
import dialogs.DialogCreator;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.util.Map;

public class StartImplementation extends AbstractAction {
    private Project project;
    private Map<Integer, VirtualFile> files;
    private ToolWindow toolWindow;
    public StartImplementation(Project project, Map<Integer, VirtualFile> files, ToolWindow toolWindow){
        super("Start Implementation");
        this.project=project;
        this.files=files;
        this.toolWindow=toolWindow;
    }
    @Override
    public void actionPerformed(ActionEvent actionEvent) {
        toolWindow.getContentManager().removeAllContents(true);
        ContentFactory contentFactory = ApplicationManager.getApplication().getService(ContentFactory.class);
        Content content = contentFactory.createContent(DialogCreator.goToBuildGradle(project,files,toolWindow), "", false);
        toolWindow.getContentManager().addContent(content);
    }
}
