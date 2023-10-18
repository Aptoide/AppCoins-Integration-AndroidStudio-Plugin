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

public class ConfirmChanges extends AbstractAction {
    private Project project;
    private Map<Integer, VirtualFile> files;
    private ToolWindow toolWindow;
    private int step;
    public ConfirmChanges(Project project, Map<Integer, VirtualFile> files, ToolWindow toolWindow, int step){
        super("Yes");
        this.project=project;
        this.files = files;
        this.toolWindow = toolWindow;
        this.step=step;
    }
    @Override
    public void actionPerformed(ActionEvent actionEvent) {
        toolWindow.getContentManager().removeAllContents(true);
        ContentFactory contentFactory = ApplicationManager.getApplication().getService(ContentFactory.class);
        Content content;
        switch (step){
            case 1:
                content = contentFactory.createContent(DialogCreator.goToAndroidManifest(project,files,toolWindow), "", false);
                break;
            case 2:
                if(files.get(4)!=null){
                    content = contentFactory.createContent(DialogCreator.goToStartingServiceConnection(project,files,toolWindow), "", false);
                }
                else{
                    content = contentFactory.createContent(DialogCreator.noOnCreate(project,files,toolWindow), "", false);
                }
                break;
            case 3:
                if(files.get(5)!=null){
                    content = contentFactory.createContent(DialogCreator.goToMakingAPurchase(project,files,toolWindow), "", false);
                }
                else{
                    content = contentFactory.createContent(DialogCreator.noMakingAPurchase(project,files,toolWindow), "", false);
                }
                break;
            case 4:
                content = contentFactory.createContent(DialogCreator.goToOnActivityResult(project,files,toolWindow), "", false);
                break;
            case 5:
                content = contentFactory.createContent(DialogCreator.queryPurchasesDialog(project,files,toolWindow), "", false);
                break;
            default:
                content = contentFactory.createContent(DialogCreator.conclusion(project,files,toolWindow), "", false);
        }
        toolWindow.getContentManager().addContent(content);
    }

}
