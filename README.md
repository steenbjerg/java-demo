# java-demo

This is a very small java application for testing java features and native compilation with BellSoft Liberica.
Local development is done with BellSoft Liberica on Ubuntu 25.04. And Github actions are used for CI/CD building for Windows, Linux and MacOS. It must be able to run on all three platforms and it must be able to use all javafx features.
Build tool is gradle.

## BellSoft Liberica Setup
Download Liberica from https://bell-sw.com/pages/downloads/

Setup environment variables for jdk 21:

```{script}
export JAVA_HOME=/opt/bellsoft/liberica-vm-full-23.1.8-openjdk21
export PATH=/opt/bellsoft/liberica-vm-full-23.1.8-openjdk21/bin:$PATH
```
or this for jdk 24:

```{script}
export JAVA_HOME=/opt/bellsoft/liberica-vm-full-24.2.2-openjdk24
export PATH=/opt/bellsoft/liberica-vm-full-24.2.2-openjdk24/bin:$PATH   
```

or this for jdk 25:

```{script}
export JAVA_HOME=/opt/bellsoft/liberica-vm-full-25.0.1-openjdk25
export PATH=/opt/bellsoft/liberica-vm-full-25.0.1-openjdk25/bin:$PATH
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

## Problems with this branch

This branch uses jdk25 but the native compilation fails with this jdk. See actions output for details.

The short summary is that it can setup-graalvm can not find the jdk25 to install in Ubuntu. It seems like the installation asset is not named correctly. It should find this https://github.com/bell-sw/LibericaNIK/releases/download/jdk-25.0.0%2B37/bellsoft-liberica-vm-full-openjdk25+37-25.0.0+1-linux-amd64.tar.gz in tag jdk-25.0.0+37 (see https://github.com/bell-sw/LibericaNIK/releases?q=jdk-25&expanded=true). The tag is named 25.0.0+37 but the version number in the installation asset is 25-37-25.0.0+1 which will not be found.

## Contact

* Feel free to contact me.

## Links
* Github actions: https://bell-sw.com/blog/how-to-create-javafx-native-images/
* https://github.com/actions/runner-images?tab=readme-ov-file

