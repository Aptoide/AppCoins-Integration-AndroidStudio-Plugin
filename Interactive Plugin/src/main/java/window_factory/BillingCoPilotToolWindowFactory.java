package window_factory;

import actions.Action1;
import com.intellij.openapi.actionSystem.ActionManager;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.wm.ToolWindow;
import com.intellij.openapi.wm.ToolWindowAnchor;
import com.intellij.openapi.wm.ToolWindowFactory;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import javax.swing.*;
import java.awt.*;

public class BillingCoPilotToolWindowFactory implements ToolWindowFactory {
    @Override
    public boolean isApplicable(@NotNull Project project) {
        return ToolWindowFactory.super.isApplicable(project);
    }

    @Override
    public void createToolWindowContent(@NotNull Project project, @NotNull ToolWindow toolWindow) {
        // Create a panel to hold the components
        JPanel panel = new JPanel();
        panel.setLayout(new BorderLayout());

        // Create an editor pane to display HTML content
        JEditorPane editorPane = new JEditorPane();
        editorPane.setContentType("text/html");
        editorPane.setEditable(false); // Make it read-only
        JScrollPane scrollPane = new JScrollPane(editorPane);

        // Create a text field for input
        JTextField textField = new JTextField();
        textField.addActionListener(e -> {
            // Get the text from the text field
            String text = textField.getText();

            // Get the current content of the editor pane
            String currentContent = editorPane.getText();

            // Append the new text with a paragraph space
            String newContent = currentContent.replace("</body></html>", "") + "<p>" + text + "</p></body></html>";

            // Set the new content to the editor pane
            editorPane.setText(newContent);

            // Clear the text field
            textField.setText("");
        });

        // Add the text field and scroll pane to the panel
        panel.add(textField, BorderLayout.NORTH);
        panel.add(scrollPane, BorderLayout.CENTER);

        // Set the content of the tool window
        toolWindow.getComponent().add(panel);
    }


    @Override
    public void init(@NotNull ToolWindow toolWindow) {
        ToolWindowFactory.super.init(toolWindow);
    }

    @Override
    public boolean shouldBeAvailable(@NotNull Project project) {
        return ToolWindowFactory.super.shouldBeAvailable(project);
    }

    @Override
    public boolean isDoNotActivateOnStart() {
        return ToolWindowFactory.super.isDoNotActivateOnStart();
    }

    @Override
    public @Nullable ToolWindowAnchor getAnchor() {
        return ToolWindowFactory.super.getAnchor();
    }

    @Override
    public @Nullable Icon getIcon() {
        return ToolWindowFactory.super.getIcon();
    }
}
