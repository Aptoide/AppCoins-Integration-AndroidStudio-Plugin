package utils;

import java.util.prefs.Preferences;

public class AICopilotState {
    private static final Preferences prefs = Preferences.userNodeForPackage(AICopilotState.class);
    private static final String KEY_AI_COPILOT_ON = "aiCopilotOn";

    public static boolean isAICopilotOn() {
        return prefs.getBoolean(KEY_AI_COPILOT_ON, false);
    }

    public static void setAICopilotOn(boolean isOn) {
        prefs.putBoolean(KEY_AI_COPILOT_ON, isOn);
    }
}