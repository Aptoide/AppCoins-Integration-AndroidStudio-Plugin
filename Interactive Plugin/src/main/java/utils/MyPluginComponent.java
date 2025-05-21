package utils;

import com.intellij.ide.util.PropertiesComponent;

public class MyPluginComponent {
    private static final String MY_KEY = "myKey";

    public void saveValue(boolean value) {
        PropertiesComponent.getInstance().setValue(MY_KEY, value);
    }

    public boolean getValue() {
        return Boolean.parseBoolean(PropertiesComponent.getInstance().getValue(MY_KEY, "false"));
    }
}