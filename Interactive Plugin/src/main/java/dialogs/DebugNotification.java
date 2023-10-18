package dialogs;

import com.intellij.notification.NotificationGroupManager;
import com.intellij.notification.NotificationType;
import com.intellij.openapi.project.Project;
import org.jetbrains.annotations.Nullable;

public class DebugNotification {
    public static void notifyError(@Nullable Project project,
                                   String content) {
        NotificationGroupManager.getInstance()
                .getNotificationGroup("Error Group")
                .createNotification(content, NotificationType.INFORMATION)
                .notify(project);
    }
}