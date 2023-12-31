package window_factory;

import com.intellij.openapi.application.ApplicationManager;
import com.intellij.openapi.editor.Document;
import com.intellij.openapi.fileEditor.FileDocumentManager;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.roots.ContentIterator;
import com.intellij.openapi.roots.ProjectFileIndex;
import com.intellij.openapi.vfs.VirtualFile;
import com.intellij.openapi.wm.ToolWindow;
import com.intellij.openapi.wm.ToolWindowFactory;
import com.intellij.ui.content.Content;
import com.intellij.ui.content.ContentFactory;
import dialogs.DialogCreator;
import org.jetbrains.annotations.NotNull;
import snipets.JavaSnippets;
import snipets.KotlinSnippets;
import snipets.Snippets;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class BillingToolWindowFactory implements ToolWindowFactory {
    Map<Integer, VirtualFile> files = new HashMap<>();
    ArrayList<VirtualFile> buildGradleFiles = new ArrayList<>();
    ArrayList<String> buildGradleLocations = new ArrayList<>();
    Project targetProject;
    public static String projectLanguage;
    public static Snippets snippets;

    /**
     * Create the tool window content.
     *
     * @param project    current project
     * @param toolWindow current tool window
     */
    public void createToolWindowContent(@NotNull Project project, @NotNull ToolWindow toolWindow) {
        targetProject = project;

        ContentIterator iterator = fileOrDir -> {
            try{
                if (fileOrDir.isValid() && !fileOrDir.isDirectory()) {
                    String content = getFileContent(fileOrDir);
                    grabGradleFile(fileOrDir);
                    grabAndroidManifestFile(fileOrDir);
                    grabPurchaseFlowFunctionFile(content, fileOrDir);
                    grabGradlePropertiesFile(fileOrDir);
                    grabAllPurchaseFlowFunctionFile(content,fileOrDir);
                    handleMultipleGradleFiles();
                    getProjectLanguage(fileOrDir);
                }
                return true;
            } catch (Exception exp) {
                return true;
            }
        };

        locateNecessaryFilesInProject(iterator);

        String mainActivityName = grabActivityName();
        ContentIterator iterator2 = fileOrDir -> {
            try {
                if (fileOrDir.isValid() && !fileOrDir.isDirectory()) {
                    String content = getFileContent(fileOrDir);
                    if (fileOrDir.getExtension().contains("java") || fileOrDir.getExtension().contains("kt")) {
                        if (containsClass(content,mainActivityName)) {
                            files.put(1, fileOrDir);
                            if(containsOnCreate(content)){
                                files.put(4,fileOrDir);
                            }
                        }
                    }
                }
                return true;
            } catch (Exception exp) {
                return true;
            }
        };
        ProjectFileIndex.getInstance(project).iterateContent(iterator2);
        createSnipetsObject();
        ContentFactory contentFactory = ApplicationManager.getApplication().getService(ContentFactory.class);
        Content content = contentFactory.createContent(DialogCreator.cardLayout(project,files,toolWindow), "", false);
        toolWindow.getContentManager().addContent(content);
    }

    private void getProjectLanguage(VirtualFile file) {
        if (file.getExtension().contains("java") && projectLanguage != "Kotlin") {
            projectLanguage = "Java";
        } else if (file.getExtension().contains("kt")){
            projectLanguage = "Kotlin";
        }
    }

    public static boolean projectIsKotlin(){
        if (projectLanguage == "Kotlin"){
            return true;
        }
        return false;
    }

    private void createSnipetsObject(){
        if (projectLanguage == "Java"){
            snippets = new JavaSnippets();
        } else {
            snippets = new KotlinSnippets();
        }
    }

    private Document convertFileToDocument(VirtualFile file){
        FileDocumentManager fileInstance = FileDocumentManager.getInstance();
        Document fileDocument = fileInstance.getDocument(file);
        return fileDocument;
    }

    private void grabAllPurchaseFlowFunctionFile(String content, VirtualFile fileOrDir) {
        if (containsLaunchPurchaseFlowDeclaration(content)){
            files.put(7,fileOrDir);
        }
    }

    private String getFileContent(VirtualFile file){
        Document fileDocument = convertFileToDocument(file);
        String content = fileDocument.getText();
        return content;
    }

    private void grabGradleFile(VirtualFile file) {
        if (file.getName().contains("build.gradle")) {
            buildGradleFiles.add(file);
            buildGradleLocations.add(file.getCanonicalPath());
        }
    }

    private void grabAndroidManifestFile(VirtualFile file) {
        if (file.getName().contains("AndroidManifest")) {
            files.put(3, file);
        }
    }

    private void grabPurchaseFlowFunctionFile(String content, VirtualFile file) {
        if (files.get(5)==null && content.contains(".launchPurchaseFlow")){
            files.put(5,file);
        }
    }

    private void grabGradlePropertiesFile(VirtualFile file) {
        if (file.getName().contains("gradle.properties")){
            files.put(6,file);
        }
    }

    private void handleMultipleGradleFiles() {
        if (buildGradleLocations.size() == 2) {
            VirtualFile buildGradleFile;
            if (buildGradleLocations.get(0).length() < buildGradleLocations.get(1).length()) {
                buildGradleFile = buildGradleFiles.get(1);
            } else {
                buildGradleFile = buildGradleFiles.get(0);
            }
            files.put(2, buildGradleFile);
        }
    }

    private void locateNecessaryFilesInProject(ContentIterator iterator) {
        ProjectFileIndex.getInstance(targetProject).iterateContent(iterator);
    }

    private String grabActivityName(){
        VirtualFile androidManifest = files.get(3);
        String content = getFileContent(androidManifest);

        int activityIndex =  content.indexOf("<activity");
        int activityNameIndex = content.indexOf("android:name",activityIndex);

        char separator = getNameSeparatorChar(content, activityNameIndex);

        int nameStartIndex = content.indexOf(separator,activityNameIndex);
        int nameEndIndex = content.indexOf(separator,nameStartIndex+1);

        String rawName = content.substring(nameStartIndex,nameEndIndex);
        String fullName = cleanActivityName(rawName);

        return fullName;
    }

    private String cleanActivityName(String rawName) {
        String [] splittedName = rawName.split("\\.");
        return splittedName[splittedName.length-1];
    }

    private char getNameSeparatorChar(String content, int activityNameIndex){
        int apostropheIndex = content.indexOf("'", activityNameIndex);
        int quoteIdex = content.indexOf('"', activityNameIndex);
        if (apostropheAndQuoteExist(apostropheIndex, quoteIdex)){
            int firstToOccur = Math.min(apostropheIndex,quoteIdex);
            return content.charAt(firstToOccur);
        }
        else {
            int separatorIndex = Math.max(apostropheIndex,quoteIdex);
            return content.charAt(separatorIndex);
        }
    }

    private boolean apostropheAndQuoteExist(int apostropheIndex, int quoteIdex) {
        if (apostropheIndex != -1 && quoteIdex != -1){
            return true;
        }
        return false;
    }

    private boolean containsClass(String fileContent, String className){
        int classIndex = fileContent.indexOf("class ");
        while(classIndex!=-1){
            int lastIndex = fileContent.indexOf("{",classIndex);
            if(fileContent.substring(classIndex,lastIndex).contains(className)){
                return true;
            }
            classIndex = fileContent.indexOf("class ",classIndex+1);
        }
        return false;
    }
    private boolean containsOnCreate(String content){
        int firstIndex= 0;
        int lastIndex=0;
        while(firstIndex!=-1){
            firstIndex = content.indexOf("@Override",lastIndex);
            lastIndex= content.indexOf("{", firstIndex);
            String method = content.substring(firstIndex,lastIndex);
            if ((method.contains(" public ") ||
                method.contains(" protected ") ||
                method.contains(" private ")) &&
                method.contains(" void ") && method.contains(" onCreate")){
                return true;
            }
        }
        return false;
    }

    private boolean containsLaunchPurchaseFlowDeclaration(String content){
        int index = content.indexOf("launchPurchaseFlow");
        while(index!=-1){
            if(!isCommented(index,content)){
                if(content.indexOf("{",index)!=-1){
                    if(content.indexOf(";",index)==-1 || content.indexOf("{",index)<content.indexOf(";",index)){
                        return true;
                    }
                    else{
                        index= content.indexOf(";",index);
                    }
                }
                else{
                    return false;
                }
            }
            index = content.indexOf("launchPurchaseFlow",index+1);
        }
        return false;
    }
    private boolean isCommented(int index, String content){
        String beforeMethod = content.substring(0,index);
        if(beforeMethod.lastIndexOf("//")!=-1 && beforeMethod.lastIndexOf("\n")<beforeMethod.lastIndexOf("//")){
            return true;
        }
        String afterMethod = content.substring(index);
        if(!afterMethod.contains("/*")){
            if(afterMethod.contains("*/")){
                return true;
            }
            else{
                return false;
            }
        }
        else{
            if(afterMethod.indexOf("*/")!=-1 && afterMethod.indexOf("*/")<afterMethod.indexOf("/*")){
                return true;
            }
        }
        return false;
    }
}
