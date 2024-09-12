package utils;

import com.intellij.ide.util.PropertiesComponent;

public class MyPluginComponent {
    private static final String MY_KEY = "myKey";

    public void saveValue(String value) {
        PropertiesComponent.getInstance().setValue(MY_KEY, value);
    }

    public String getValue() {
        return PropertiesComponent.getInstance().getValue(MY_KEY, "defaultValue");
    }
}