# Integration Android Studio Plugin

- [Integration Android Studio Plugin](#integration-android-studio-plugin)
  - [💻 About](#-about)
  - [📝 TODO](#-todo)
  - [⚙️ Design/Features](#️-designfeatures)
  - [🚀 How to run it](#-how-to-run-it)
  - [🛠 Techs](#-techs)
  - [NOTES](#notes)


## 💻 About

`integration-android-studio-plugin` is an Android Studio plugin to help developers Integrate AppCoins into their apps.

## 📝 TODO
* Update SDK snippets
* Update OSP snippets
* Make snippets have different colors for different code elements (functions, variables, string, etc)

## ⚙️ Design/Features

The 4 main capabilities are 
- Choosing between OSP or SDK flow
- Follow the integrations step by step
- Apply most of the snippets directly into the user code
- Send usage metrics to the team

The main design components are
- The design is built on top of a JavaSwing CardLayout with multiple pages with different layout styles (such as GridBagLayout and BorderLayout).
- Pages call different actions, these actions are the ones that sniff the code and implement the snippets in the correct place. 
- Java and Kotlin snippets are in separate files found in the `src/main/java/snipets` folder

The version and metadata of the plugin can be changed in the `src/main/resources/META-INF/plugin.xml` file

This is what the flow of the steps look like
![AndroidStudioBillingPlugin](https://github.com/Aptoide/AppCoins-Integration-AndroidStudio-Plugin/assets/109087647/56def2bc-c59c-4b88-a380-ea5ab7cdff0f)

## 🚀 How to run it
It's recommended to develop the plugin in IntellIJ. There you will find an option to run gradle tasks. The one used to compile the plugin is `
buildDependentes`.

<img width="316" alt="image" src="https://github.com/Aptoide/AppCoins-Integration-AndroidStudio-Plugin/assets/109087647/f65f38a0-7120-4063-9602-299dd9edd664">


This will generate a `.zip` in `Interactive Plugin/build/libs` which can then be imported to Android Studio and IntellIJ Marketplace.
In Android Studio the plugin can be imported via Preferences either by using the local zip or by downloading it from the Marketplace.

<img width="977" alt="image" src="https://github.com/Aptoide/AppCoins-Integration-AndroidStudio-Plugin/assets/109087647/03f0d310-83d5-4b6b-94d4-306a0ced5f16">


## How to-do
- Change the metrics API URL -> When compile the production plugin do not forget to
change the to the production metrics API url at:
```Interactive Plugin/src/main/java/utils/MetricsClient.java```

- Release a new version -> change Plugin version and notes at:
```Interactive Plugin/src/main/resources/META-INF/plugin.xml```

## 🛠 Techs

* Java 11.0.16
* JSwing
* IntellIJ Gradle Plugin

## NOTES
* The design features may differ if the plugin is minimazed and then maximized
