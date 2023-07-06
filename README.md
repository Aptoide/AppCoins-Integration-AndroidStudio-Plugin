# sdk_integration_plugin
SDK Integration Plugin for Android Studio 
This code was created in Intellij IDEA, so it is recomended to use this code editor to work in it.
The plugin was created for Android Studio and the only way to verify its functionality, at the moment, is to run it, 
therefore it is also recomended that you install Android Studio if you want to work on the code.

# Builing the plugin and testing it
To build the plugin, you have to open the "Gradle" tool window on the right side of the window and head to: Interactive Plugin -> Tasks -> build -> build.

To install the project in Android Studio, you must go to settings (you can do that by opening the file tab on the top left and then settings), select the
"Plugins" tab, click the gear icon and select "Install Plugin from Disk..." (assuming that you have the project built locally).
The plugin file that you must add is located in "Interactive Plugin/build/libs", and you should select the version you have been working on (you can check that in the build.gradle file of the plugin).

# Code structure
The plugin source code is 100% java and is divided into 4 packages: Actions, AuxiliaryClasses, Dialogs and MainClasses.

The Actions package contains all the button actions in the plugin. All these classes extend AbstractAction and are applied, at least, to one button.
The actions can perform multiple tasks, but the primary task that any actions performs is to change the dialog in the tool window created by the plugin.
Actions that change the source code of the app always look at the files as strings, which is simpler to understand and work on but can create some bugs.

The AuxiliaryClasses package contains classes that were created to help with the display of content in the tool window. Currently, the JImageComponent is not
being used, but the code it has could be usefull for editing images. The JKeyComponent is used in one of the tool windows to display the form where the user
inserts their Catappult public key.

The Dialogs package contains a single class: DialogCreator. This class is responsible for creating every tool window's content. All the public methods in it are
static, so there is no need to ever create a DialogCreator object. The private methods are used only to format the displayed text.

The MainClasses package contains a single class: BillingToolWindowFactory. This class is called when Android Studio starts and is responsible for creating the
tool window and searching for all the files relevant to the plugin.

# To do in the future
One of the limitations of the plugin is that it reads all files as strings. This is a problem especially because of commented code that can trick the plugin.
To solve this issue, there already is a method called "isCommented", that recognizes if the specified index on a file's string is commented or not, but
this method was created very recently and so it wasn't applied to most cases where this can be an issue.

Another limitation of the plugin is that it only works on java code. For the plugin to also work on kotlin, the corresponding code snippets have to be added
and there needs to be created some logic to choose, for each stage, which tutorial should be shown, java or kotlin.

It would also be important to track how many developers have migrated their apps thanks to the plugin, so an endpoint should be created to count how many
developers have reached the end of the plugin tutorial.

The plugin was only tested in a simple app, so it is still recomended to verify its functionality on multiple and possibly more complex apps.

The user interface is still very raw, as it can be greatly improved.
