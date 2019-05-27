# kotlindsl

This project demonstrates various [Kotlin DSL](https://kotlinlang.org/docs/reference/type-safe-builders.html) usecases in different 
modules, gradle kotlin dsl script is used

### How to import
After cloning, see [link](https://www.jetbrains.com/help/idea/gradle.html) to see how to import this project into IntelliJ IDE,
check the **Import a project from a Gradle model** section

If you encounter this error after importing: 
`SDK location not found. Define location with an ANDROID_SDK_ROOT environment variable 
or by setting the sdk.dir path in your project's local properties file.`
Create a local.properties file and put it into the root project folder, in the file, define this line: `sdk.dir={pathToYourSDK}/Android/sdk.`