# java-demo

This is a very small java application for testing java features and native compilation with BellSoft Liberica.
Local development is done with BellSoft Liberica on Ubuntu 25.04. And Github actions are used for CI/CD building for Windows, Linux and MacOS. It must be able to run on all three platforms and it must be able to use all javafx features.
Build tool is gradle.

## BellSoft Liberica Setup
Download Liberica from https://bell-sw.com/pages/downloads/

Setup environment variables for jdk 21:

```{script}
export JAVA_HOME=/opt/bellsoft/liberica-vm-full-23.1.9-openjdk21
export PATH=/opt/bellsoft/liberica-vm-full-23.1.9-openjdk21/bin:$PATH
```

or this for jdk 25:

```{script}
export JAVA_HOME=/opt/bellsoft/liberica-vm-full-25.0.2-openjdk25
export PATH=/opt/bellsoft/liberica-vm-full-25.0.2-openjdk25/bin:$PATH
```

In order to compile native you must install the following packages:
```{script}
sudo apt install zlib1g-dev
```

## Graalvm Gradle plugin for Native Client
see https://graalvm.github.io/native-build-tools/latest/gradle-plugin.html

Run this to collect information about classes accessed via reflections and resources needed.  

Do this for creating the necessary metadata files:

```{script}
./gradlew -Pagent=standard run
./gradlew metadataCopy
```

Run this for doing the actual native compilation:

```{script}
./gradlew nativeCompile
```

or commit an the github actions will do it for you.

## Problems with Windows build in Github actions 

See https://github.com/graalvm/native-build-tools/issues/754
Temporarily fixed by adding this to gradle.properties:

org.gradle.jvmargs=-Djava.io.tmpdir=D:/Temp

## Using mime types for starting the application

You can start the application and select a car by using a mime type. For example:

```{script}
./gradlew run --args="fs-java-demo://view?brand=Ford&name=Mustang"
```

If you install src/main/os/debian/java-demo.desktop on your linux os then it is possible to activate the application from a html page like src/main/os/debian/test.html.

You can also test the mime registration inside the desktop file by running:

sudo update-desktop-database /usr/share/applications/

```{script}
xdg-open fs-java-demo://view?brand=Ford&name=Mustang
```

## Contact

* Feel free to contact me.

## Links
* Github actions: https://bell-sw.com/blog/how-to-create-javafx-native-images/
* https://github.com/actions/runner-images?tab=readme-ov-file

