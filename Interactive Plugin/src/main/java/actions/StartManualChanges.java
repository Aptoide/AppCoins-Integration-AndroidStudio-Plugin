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

public class StartManualChanges extends AbstractAction {
    private Project project;
    private Map<Integer, VirtualFile> files;
    private ToolWindow toolWindow;
    private int step;
    public StartManualChanges(Project project, Map<Integer, VirtualFile> files, ToolWindow toolWindow, int step){
        super("Implement changes manually");
        this.project=project;
        this.files=files;
        this.toolWindow=toolWindow;
        this.step=step;
    }
    @Override
    public void actionPerformed(ActionEvent actionEvent) {
        toolWindow.getContentManager().removeAllContents(true);
        ContentFactory contentFactory = ContentFactory.SERVICE.getInstance();
        Content content;
        switch (step){
            case 1:
                content = contentFactory.createContent(DialogCreator.buildGradleManual(project,files,toolWindow), "", false);
                break;
            case 2:
                content = contentFactory.createContent(DialogCreator.androidManifestManual(project,files,toolWindow), "", false);
                break;
            case 3:
                content = contentFactory.createContent(DialogCreator.startingServiceConnectionManual(project,files,toolWindow), "", false);
                break;
            case 4:
                content = contentFactory.createContent(DialogCreator.makingAPurchaseManual(project,files,toolWindow), "", false);
                break;
            case 5:
                content = contentFactory.createContent(DialogCreator.consumeAPurchaseManual(project,files,toolWindow), "", false);
                break;
            default:
                content= contentFactory.createContent(DialogCreator.queryPurchasesManual(project,files,toolWindow), "", false);
        }
        toolWindow.getContentManager().addContent(content);
    }
}
