# java-demo

This is a very small java application for testing java features and native compilation with BellSoft Liberica.
Local development is done with BellSoft Liberica on Ubuntu 25.04. And Github actions are used for CI/CD building for Windows, Linux and MacOS. It must be able to run on all three platforms and it must be able to use all javafx features.
Build tool is gradle.

## BellSoft Liberica Setup
Download Liberica from https://bell-sw.com/pages/downloads/

Setup environment variables for jdk 21:

```{script}
export JAVA_HOME=/opt/bellsoft/liberica-vm-full-23.1.8-openjdk21
export PATH=/opt/bellsoft/liberica-vm-full-23.1.8-openjdk21/bin
```
or this for jdk 24:

```{script}
export JAVA_HOME=/opt/bellsoft/liberica-vm-full-24.2.2-openjdk24
export PATH=/opt/bellsoft/liberica-vm-full-24.2.2-openjdk24/bin:$PATH   
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

Run the buided application:

```{script}
build/native/nativeCompile/java-demo
```

### Bellsoft

As soon as I add javafx dependencies, I get this error:

```
steenbjerg@stonemountain:~/workspaces/bellsoft/java-demo$ ./gradlew clean nativeCompile

> Task :generateResourcesConfigFile
[native-image-plugin] Resources configuration written into /home/steenbjerg/workspaces/bellsoft/java-demo/build/native/generated/generateResourcesConfigFile/resource-config.json

> Task :nativeCompile
[native-image-plugin] Args are: [-cp, /home/steenbjerg/workspaces/bellsoft/java-demo/build/libs/java-demo.jar:/home/steenbjerg/.gradle/caches/modules-2/files-2.1/org.slf4j/jul-to-slf4j/2.0.13/a3bcd9d9dd50c71ce69f06b1fd05e40fdeff6ba5/jul-to-slf4j-2.0.13.jar:/home/steenbjerg/.gradle/caches/modules-2/files-2.1/ch.qos.logback/logback-classic/1.5.6/afc75d260d838a3bddfb8f207c2805ed7d1b34f9/logback-classic-1.5.6.jar:/home/steenbjerg/.gradle/caches/modules-2/files-2.1/org.slf4j/slf4j-api/2.0.13/80229737f704b121a318bba5d5deacbcf395bc77/slf4j-api-2.0.13.jar:/home/steenbjerg/.gradle/caches/modules-2/files-2.1/ch.qos.logback/logback-core/1.5.6/41cbe874701200c5624c19e0ab50d1b88dfcc77d/logback-core-1.5.6.jar:/home/steenbjerg/.gradle/caches/modules-2/files-2.1/org.eclipse/yasson/3.0.3/84b3af77c917d5807b1de2d25aca434998d35eb1/yasson-3.0.3.jar:/home/steenbjerg/.gradle/caches/modules-2/files-2.1/jakarta.json.bind/jakarta.json.bind-api/3.0.0/9dab3b7d91ed6b22f7c76cc3674805482247af32/jakarta.json.bind-api-3.0.0.jar:/home/steenbjerg/.gradle/caches/modules-2/files-2.1/org.eclipse.parsson/parsson/1.1.0/9de69f5b29040791c2c275837ce56bc658ff834b/parsson-1.1.0.jar:/home/steenbjerg/.gradle/caches/modules-2/files-2.1/jakarta.json/jakarta.json-api/2.1.0/1e713a9cd9ea9bd4dc6ec263d47e1b7f1cf18082/jakarta.json-api-2.1.0.jar, --no-fallback, --verbose, -o, /home/steenbjerg/workspaces/bellsoft/java-demo/build/native/nativeCompile/java-demo, -H:ConfigurationFileDirectories=/home/steenbjerg/workspaces/bellsoft/java-demo/build/native/generated/generateResourcesConfigFile,/home/steenbjerg/.gradle/native-build-tools/repositories/c14aa06582e580a8084b6c86c7f7033c517ec9cc/exploded/ch.qos.logback/logback-classic/1.4.9, -Djava.awt.headless=false, dk.stonemountain.demo.java.Main]
[native-image-plugin] GraalVM Toolchain detection is disabled
[native-image-plugin] GraalVM location read from environment variable: JAVA_HOME
[native-image-plugin] Native Image executable path: /opt/bellsoft/liberica-vm-full-24.2.2-openjdk24/lib/svm/bin/native-image
Apply jar:file:///home/steenbjerg/workspaces/bellsoft/java-demo/build/libs/java-demo.jar!/META-INF/native-image/reachability-metadata.json
Apply jar:file:///home/steenbjerg/.gradle/caches/modules-2/files-2.1/org.eclipse/yasson/3.0.3/84b3af77c917d5807b1de2d25aca434998d35eb1/yasson-3.0.3.jar!/META-INF/native-image/org.eclipse/yasson/native-image.properties
Warning: The option '-H:IncludeResourceBundles=yasson-messages' is experimental and must be enabled via '-H:+UnlockExperimentalVMOptions' in the future.
Warning: Please re-evaluate whether any experimental option is required, and either remove or unlock it. The build output lists all active experimental options, including where they come from and possible alternatives. If you think an experimental option should be considered as stable, please file an issue.
Apply jar:file:///opt/bellsoft/liberica-vm-full-24.2.2-openjdk24/lib/svm/library-support.jar!/META-INF/native-image/com.oracle.svm/thirdparty/native-image.properties
Apply jar:file:///opt/bellsoft/liberica-vm-full-24.2.2-openjdk24/lib/svm/library-support.jar!/META-INF/native-image/com.oracle.svm/polyglot/native-image.properties
Executing [
HOME=/home/steenbjerg \
LANG=en_US.UTF-8 \
PATH=/opt/bellsoft/liberica-vm-full-24.2.2-openjdk24/bin:/home/steenbjerg/.local/bin:/usr/local/sbin:/usr/local/bin:/usr/sbin:/usr/bin:/sbin:/bin:/usr/games:/usr/local/games:/snap/bin:/snap/bin:/home/steenbjerg/.jbang/bin:/home/steenbjerg:/opt/gradle/gradle-8.14.2/bin:/home/steenbjerg/.jbang/bin:/home/steenbjerg:/opt/gradle/gradle-8.14.2/bin \
PWD=/home/steenbjerg/workspaces/bellsoft/java-demo \
USE_NATIVE_IMAGE_JAVA_PLATFORM_MODULE_SYSTEM=true \
/opt/bellsoft/liberica-vm-full-24.2.2-openjdk24/bin/java \
-XX:+UnlockExperimentalVMOptions \
-XX:+EnableJVMCI \
-Dtruffle.TrustAllTruffleRuntimeProviders=true \
-Dtruffle.TruffleRuntime=com.oracle.truffle.api.impl.DefaultTruffleRuntime \
-Dgraalvm.ForcePolyglotInvalid=true \
-Dgraalvm.locatorDisabled=true \
-Dsubstratevm.HostLibC=glibc \
--add-exports=java.base/com.sun.crypto.provider=org.graalvm.nativeimage.builder \
--add-exports=java.base/jdk.internal.access=org.graalvm.nativeimage.builder \
--add-exports=java.base/jdk.internal.event=org.graalvm.nativeimage.builder \
--add-exports=java.base/jdk.internal.loader=org.graalvm.nativeimage.builder \
--add-exports=java.base/jdk.internal.logger=org.graalvm.nativeimage.builder \
--add-exports=java.base/jdk.internal.misc=jdk.graal.compiler,org.graalvm.nativeimage.builder,org.graalvm.nativeimage.objectfile,org.graalvm.nativeimage.pointsto \
--add-exports=java.base/jdk.internal.module=org.graalvm.nativeimage.base,org.graalvm.nativeimage.builder \
--add-exports=java.base/jdk.internal.org.objectweb.asm=org.graalvm.nativeimage.builder \
--add-exports=java.base/jdk.internal.perf=org.graalvm.nativeimage.builder \
--add-exports=java.base/jdk.internal.platform=org.graalvm.nativeimage.builder \
--add-exports=java.base/jdk.internal.ref=org.graalvm.nativeimage.builder,org.graalvm.nativeimage.objectfile \
--add-exports=java.base/jdk.internal.reflect=org.graalvm.nativeimage.builder \
--add-exports=java.base/jdk.internal.util=org.graalvm.nativeimage.builder \
--add-exports=java.base/jdk.internal.vm.annotation=org.graalvm.nativeimage.builder \
--add-exports=java.base/jdk.internal.vm=org.graalvm.nativeimage.builder \
--add-exports=java.base/jdk.internal=org.graalvm.nativeimage.builder \
--add-exports=java.base/sun.invoke.util=org.graalvm.nativeimage.builder \
--add-exports=java.base/sun.net.www=org.graalvm.nativeimage.builder \
--add-exports=java.base/sun.net=org.graalvm.nativeimage.builder \
--add-exports=java.base/sun.nio.ch=org.graalvm.nativeimage.builder,org.graalvm.nativeimage.objectfile \
--add-exports=java.base/sun.reflect.annotation=org.graalvm.nativeimage.builder \
--add-exports=java.base/sun.reflect.generics.factory=org.graalvm.nativeimage.builder \
--add-exports=java.base/sun.reflect.generics.reflectiveObjects=org.graalvm.nativeimage.builder \
--add-exports=java.base/sun.reflect.generics.repository=org.graalvm.nativeimage.builder \
--add-exports=java.base/sun.reflect.generics.scope=org.graalvm.nativeimage.builder \
--add-exports=java.base/sun.reflect.generics.tree=org.graalvm.nativeimage.builder \
--add-exports=java.base/sun.security.jca=org.graalvm.nativeimage.builder \
--add-exports=java.base/sun.security.provider=org.graalvm.nativeimage.builder \
--add-exports=java.base/sun.security.ssl=org.graalvm.nativeimage.builder \
--add-exports=java.base/sun.security.util=org.graalvm.nativeimage.builder \
--add-exports=java.base/sun.security.x509=org.graalvm.nativeimage.builder \
--add-exports=java.base/sun.text.spi=org.graalvm.nativeimage.builder \
--add-exports=java.base/sun.util.calendar=org.graalvm.nativeimage.builder \
--add-exports=java.base/sun.util.cldr=org.graalvm.nativeimage.builder \
--add-exports=java.base/sun.util.locale.provider=org.graalvm.nativeimage.builder \
--add-exports=java.base/sun.util.locale=org.graalvm.nativeimage.builder \
--add-exports=java.base/sun.util.resources=org.graalvm.nativeimage.builder \
--add-exports=java.base/sun.util=org.graalvm.nativeimage.builder \
--add-exports=java.desktop/sun.java2d.pipe=org.graalvm.nativeimage.builder \
--add-exports=java.desktop/sun.java2d=org.graalvm.nativeimage.builder \
--add-exports=java.management/com.sun.jmx.mbeanserver=org.graalvm.nativeimage.builder \
--add-exports=java.management/sun.management=org.graalvm.nativeimage.builder,org.graalvm.nativeimage.pointsto \
--add-exports=jdk.internal.vm.ci/jdk.vm.ci.aarch64=jdk.graal.compiler,org.graalvm.nativeimage.builder,org.graalvm.nativeimage.objectfile \
--add-exports=jdk.internal.vm.ci/jdk.vm.ci.amd64=jdk.graal.compiler,org.graalvm.nativeimage.builder,org.graalvm.nativeimage.objectfile \
--add-exports=jdk.internal.vm.ci/jdk.vm.ci.code.site=jdk.graal.compiler,org.graalvm.nativeimage.builder \
--add-exports=jdk.internal.vm.ci/jdk.vm.ci.code.stack=jdk.graal.compiler,org.graalvm.nativeimage.builder \
--add-exports=jdk.internal.vm.ci/jdk.vm.ci.code=jdk.graal.compiler,org.graalvm.nativeimage.builder,org.graalvm.nativeimage.objectfile,org.graalvm.nativeimage.pointsto,org.graalvm.truffle.compiler \
--add-exports=jdk.internal.vm.ci/jdk.vm.ci.common=jdk.graal.compiler,org.graalvm.nativeimage.builder,org.graalvm.nativeimage.pointsto \
--add-exports=jdk.internal.vm.ci/jdk.vm.ci.hotspot.aarch64=jdk.graal.compiler \
--add-exports=jdk.internal.vm.ci/jdk.vm.ci.hotspot.amd64=jdk.graal.compiler \
--add-exports=jdk.internal.vm.ci/jdk.vm.ci.hotspot.riscv64=jdk.graal.compiler \
--add-exports=jdk.internal.vm.ci/jdk.vm.ci.hotspot=jdk.graal.compiler,org.graalvm.nativeimage.builder \
--add-exports=jdk.internal.vm.ci/jdk.vm.ci.meta=jdk.graal.compiler,org.graalvm.nativeimage.base,org.graalvm.nativeimage.builder,org.graalvm.nativeimage.objectfile,org.graalvm.nativeimage.pointsto,org.graalvm.truffle.compiler \
--add-exports=jdk.internal.vm.ci/jdk.vm.ci.riscv64=jdk.graal.compiler,org.graalvm.nativeimage.builder \
--add-exports=jdk.internal.vm.ci/jdk.vm.ci.runtime=jdk.graal.compiler,org.graalvm.nativeimage.builder,org.graalvm.nativeimage.pointsto \
--add-exports=jdk.internal.vm.ci/jdk.vm.ci.services=jdk.graal.compiler,org.graalvm.nativeimage.builder \
--add-exports=jdk.jfr/jdk.jfr.events=org.graalvm.nativeimage.builder \
--add-exports=jdk.jfr/jdk.jfr.internal.event=org.graalvm.nativeimage.builder \
--add-exports=jdk.jfr/jdk.jfr.internal.jfc=org.graalvm.nativeimage.builder \
--add-exports=jdk.jfr/jdk.jfr.internal=org.graalvm.nativeimage.builder \
--add-exports=jdk.management/com.sun.management.internal=org.graalvm.nativeimage.builder \
-XX:+UseJVMCINativeLibrary \
-Xss10m \
-XX:+UseParallelGC \
-XX:MaxRAMPercentage=85.0 \
-XX:GCTimeRatio=9 \
-XX:+ExitOnOutOfMemoryError \
-Djava.awt.headless=true \
-Dorg.graalvm.vendor=BellSoft \
-Dorg.graalvm.vendorurl=https://bell-sw.com/ \
-Dorg.graalvm.vendorversion=Liberica-NIK-24.2.2-1 \
-Dorg.graalvm.version=24.2.2 \
-Dcom.oracle.graalvm.isaot=true \
-Djava.system.class.loader=com.oracle.svm.hosted.NativeImageSystemClassLoader \
-Xshare:off \
-Djdk.internal.lambda.disableEagerInitialization=true \
-Djdk.internal.lambda.eagerlyInitialize=false \
-Djava.lang.invoke.InnerClassLambdaMetafactory.initializeLambdas=false \
-Djava.lang.invoke.MethodHandle.DONT_INLINE_THRESHOLD=-1 \
-Djava.lang.invoke.MethodHandle.PROFILE_GWT=false \
-Djava.awt.headless=false \
--add-modules=ALL-DEFAULT \
--enable-native-access=org.graalvm.nativeimage.objectfile,org.graalvm.nativeimage.builder,org.graalvm.nativeimage.pointsto,org.graalvm.nativeimage.foreign,org.graalvm.nativeimage.base \
--module-path \
/opt/bellsoft/liberica-vm-full-24.2.2-openjdk24/lib/svm/builder/pointsto.jar:/opt/bellsoft/liberica-vm-full-24.2.2-openjdk24/lib/svm/builder/svm-foreign.jar:/opt/bellsoft/liberica-vm-full-24.2.2-openjdk24/lib/svm/builder/svm.jar:/opt/bellsoft/liberica-vm-full-24.2.2-openjdk24/lib/svm/builder/objectfile.jar:/opt/bellsoft/liberica-vm-full-24.2.2-openjdk24/lib/svm/builder/native-image-base.jar \
-Dorg.graalvm.nativeimage.module.addmods=java.rmi,jdk.jdi,jfx.incubator.input,java.xml,jdk.xml.dom,java.datatransfer,org.graalvm.truffle.compiler,javafx.base,java.desktop,java.security.sasl,org.graalvm.nativeimage,jdk.zipfs,java.base,jdk.management.agent,org.graalvm.word,java.sql.rowset,jdk.jsobject,jdk.unsupported,jdk.jlink,java.security.jgss,javafx.graphics,java.compiler,jdk.graal.compiler,javafx.fxml,jdk.unsupported.desktop,javafx.media,java.sql,org.graalvm.collections,java.logging,java.xml.crypto,java.transaction.xa,jdk.jfr,jdk.internal.vm.ci,jdk.internal.md,java.naming,javafx.controls,jdk.internal.ed,java.prefs,java.net.http,jdk.compiler,jdk.internal.opt,jdk.attach,jdk.internal.le,java.management,jdk.jdwp.agent,jdk.internal.jvmstat,java.instrument,jdk.management,java.scripting,jdk.jdeps,java.management.rmi \
--add-modules=java.rmi,jdk.jdi,jfx.incubator.input,java.xml,jdk.xml.dom,java.datatransfer,org.graalvm.truffle.compiler,javafx.base,java.desktop,java.security.sasl,org.graalvm.nativeimage,jdk.zipfs,java.base,jdk.management.agent,org.graalvm.word,java.sql.rowset,jdk.jsobject,jdk.unsupported,jdk.jlink,java.security.jgss,javafx.graphics,java.compiler,jdk.graal.compiler,javafx.fxml,jdk.unsupported.desktop,javafx.media,java.sql,org.graalvm.collections,java.logging,java.xml.crypto,java.transaction.xa,jdk.jfr,jdk.internal.vm.ci,jdk.internal.md,java.naming,javafx.controls,jdk.internal.ed,java.prefs,java.net.http,jdk.compiler,jdk.internal.opt,jdk.attach,jdk.internal.le,java.management,jdk.jdwp.agent,jdk.internal.jvmstat,java.instrument,jdk.management,java.scripting,jdk.jdeps,java.management.rmi \
--module \
org.graalvm.nativeimage.builder/com.oracle.svm.hosted.NativeImageGeneratorRunner \
-keepalive \
/proc/325263/comm \
-imagecp \
/home/steenbjerg/workspaces/bellsoft/java-demo/build/libs/java-demo.jar:/home/steenbjerg/.gradle/caches/modules-2/files-2.1/org.slf4j/jul-to-slf4j/2.0.13/a3bcd9d9dd50c71ce69f06b1fd05e40fdeff6ba5/jul-to-slf4j-2.0.13.jar:/home/steenbjerg/.gradle/caches/modules-2/files-2.1/ch.qos.logback/logback-classic/1.5.6/afc75d260d838a3bddfb8f207c2805ed7d1b34f9/logback-classic-1.5.6.jar:/home/steenbjerg/.gradle/caches/modules-2/files-2.1/org.slf4j/slf4j-api/2.0.13/80229737f704b121a318bba5d5deacbcf395bc77/slf4j-api-2.0.13.jar:/home/steenbjerg/.gradle/caches/modules-2/files-2.1/ch.qos.logback/logback-core/1.5.6/41cbe874701200c5624c19e0ab50d1b88dfcc77d/logback-core-1.5.6.jar:/home/steenbjerg/.gradle/caches/modules-2/files-2.1/org.eclipse/yasson/3.0.3/84b3af77c917d5807b1de2d25aca434998d35eb1/yasson-3.0.3.jar:/home/steenbjerg/.gradle/caches/modules-2/files-2.1/jakarta.json.bind/jakarta.json.bind-api/3.0.0/9dab3b7d91ed6b22f7c76cc3674805482247af32/jakarta.json.bind-api-3.0.0.jar:/home/steenbjerg/.gradle/caches/modules-2/files-2.1/org.eclipse.parsson/parsson/1.1.0/9de69f5b29040791c2c275837ce56bc658ff834b/parsson-1.1.0.jar:/home/steenbjerg/.gradle/caches/modules-2/files-2.1/jakarta.json/jakarta.json-api/2.1.0/1e713a9cd9ea9bd4dc6ec263d47e1b7f1cf18082/jakarta.json-api-2.1.0.jar \
-imagemp \
/opt/bellsoft/liberica-vm-full-24.2.2-openjdk24/lib/svm/library-support.jar \
-H:CLibraryPath=/opt/bellsoft/liberica-vm-full-24.2.2-openjdk24/lib/svm/clibraries/linux-amd64/glibc,/opt/bellsoft/liberica-vm-full-24.2.2-openjdk24/lib/svm/clibraries/linux-amd64,/opt/bellsoft/liberica-vm-full-24.2.2-openjdk24/lib/svm/clibraries \
-H:Path@driver=/home/steenbjerg/workspaces/bellsoft/java-demo/build/native/nativeCompile \
'-H:ReachabilityMetadataResources@jar:file:///home/steenbjerg/workspaces/bellsoft/java-demo/build/libs/java-demo.jar!/META-INF/native-image/reachability-metadata.json+api=META-INF/native-image/reachability-metadata.json' \
'-H:IncludeResourceBundles@jar:file:///home/steenbjerg/.gradle/caches/modules-2/files-2.1/org.eclipse/yasson/3.0.3/84b3af77c917d5807b1de2d25aca434998d35eb1/yasson-3.0.3.jar!/META-INF/native-image/org.eclipse/yasson/native-image.properties=yasson-messages' \
-H:FallbackThreshold@user+api=0 \
-H:Name@user+api=java-demo \
-H:ConfigurationFileDirectories@user=/home/steenbjerg/workspaces/bellsoft/java-demo/build/native/generated/generateResourcesConfigFile,/home/steenbjerg/.gradle/native-build-tools/repositories/c14aa06582e580a8084b6c86c7f7033c517ec9cc/exploded/ch.qos.logback/logback-classic/1.4.9 \
'-H:Class@explicit main-class=dk.stonemountain.demo.java.Main' \
-H:ImageBuildID@driver=83be2fdc-12eb-f1e8-43f2-1debb4d1742d \
'-H:Features@jar:file:///opt/bellsoft/liberica-vm-full-24.2.2-openjdk24/lib/svm/library-support.jar!/META-INF/native-image/com.oracle.svm/thirdparty/native-image.properties+api=com.oracle.svm.thirdparty.gson.GsonFeature' \
'-H:Features@jar:file:///opt/bellsoft/liberica-vm-full-24.2.2-openjdk24/lib/svm/library-support.jar!/META-INF/native-image/com.oracle.svm/polyglot/native-image.properties+api=com.oracle.svm.polyglot.groovy.GroovyIndyInterfaceFeature,com.oracle.svm.polyglot.scala.ScalaFeature'
]
========================================================================================================================
GraalVM Native Image: Generating 'java-demo' (executable)...
========================================================================================================================
For detailed information and explanations on the build output, visit:
https://github.com/oracle/graal/blob/master/docs/reference-manual/native-image/BuildOutput.md
------------------------------------------------------------------------------------------------------------------------
[1/8] Initializing...                                                                                    (2.6s @ 0.17GB)
 Java version: 24.0.2+13, vendor version: Liberica-NIK-24.2.2-1
 Graal compiler: optimization level: 2, target machine: x86-64-v3
 C compiler: gcc (linux, x86_64, 14.2.0)
 Garbage collector: Serial GC (max heap size: 80% of RAM)
 1 user-specific feature(s):
 - com.oracle.svm.thirdparty.gson.GsonFeature
------------------------------------------------------------------------------------------------------------------------
 1 experimental option(s) unlocked:
 - '-H:IncludeResourceBundles' (origin(s): 'META-INF/native-image/org.eclipse/yasson/native-image.properties' in 'file:///home/steenbjerg/.gradle/caches/modules-2/files-2.1/org.eclipse/yasson/3.0.3/84b3af77c917d5807b1de2d25aca434998d35eb1/yasson-3.0.3.jar')
------------------------------------------------------------------------------------------------------------------------
Build resources:
 - 22.64GB of memory (75.6% of 29.96GB system memory, determined at start)
 - 24 thread(s) (100.0% of 24 available processor(s), determined at start)
[2/8] Performing analysis...  []                                                                         (2.7s @ 0.27GB)
    2,384 reachable types   (56.2% of    4,239 total)
    1,516 reachable fields  (24.6% of    6,156 total)
    5,271 reachable methods (20.9% of   25,178 total)
      976 types,    55 fields, and   143 methods registered for reflection
       21 native libraries: X11, Xtst, atk-1.0, cairo, cairo-gobject, fontconfig, freetype, gdk-3, gdk_pixbuf-2.0, gio-2.0, glib-2.0, gmodule-2.0, gobject-2.0, gthread-2.0, gtk-3, harfbuzz, m, pango-1.0, pangocairo-1.0, pangoft2-1.0, stdc++

Error: java.util.concurrent.ExecutionException: com.oracle.svm.util.ReflectionUtil$ReflectionUtilError: java.lang.NoSuchMethodException: com.sun.glass.ui.gtk.GtkView.notifyInputMethodDraw(java.lang.String,int,int,int,[B)
Caused by: java.util.concurrent.ExecutionException: com.oracle.svm.util.ReflectionUtil$ReflectionUtilError: java.lang.NoSuchMethodException: com.sun.glass.ui.gtk.GtkView.notifyInputMethodDraw(java.lang.String,int,int,int,[B)
        at java.base/java.util.concurrent.FutureTask.report(FutureTask.java:124)
        at java.base/java.util.concurrent.FutureTask.get(FutureTask.java:193)
        at org.graalvm.nativeimage.pointsto/com.oracle.graal.pointsto.util.AnalysisFuture.ensureDone(AnalysisFuture.java:70)
        at org.graalvm.nativeimage.pointsto/com.oracle.graal.pointsto.meta.AnalysisElement.lambda$execute$1(AnalysisElement.java:225)
        at org.graalvm.nativeimage.pointsto/com.oracle.graal.pointsto.util.CompletionExecutor.executeCommand(CompletionExecutor.java:166)
        at org.graalvm.nativeimage.pointsto/com.oracle.graal.pointsto.util.CompletionExecutor.lambda$executeService$0(CompletionExecutor.java:152)
        at java.base/java.util.concurrent.ForkJoinTask$RunnableExecuteAction.compute(ForkJoinTask.java:1735)
        at java.base/java.util.concurrent.ForkJoinTask$RunnableExecuteAction.compute(ForkJoinTask.java:1726)
        at java.base/java.util.concurrent.ForkJoinTask$InterruptibleTask.exec(ForkJoinTask.java:1650)
        at java.base/java.util.concurrent.ForkJoinTask.doExec(ForkJoinTask.java:507)
        at java.base/java.util.concurrent.ForkJoinPool$WorkQueue.topLevelExec(ForkJoinPool.java:1394)
        at java.base/java.util.concurrent.ForkJoinPool.runWorker(ForkJoinPool.java:1970)
        at java.base/java.util.concurrent.ForkJoinWorkerThread.run(ForkJoinWorkerThread.java:187)
Caused by: com.oracle.svm.util.ReflectionUtil$ReflectionUtilError: java.lang.NoSuchMethodException: com.sun.glass.ui.gtk.GtkView.notifyInputMethodDraw(java.lang.String,int,int,int,[B)
        at org.graalvm.nativeimage.base/com.oracle.svm.util.ReflectionUtil.lookupMethod(ReflectionUtil.java:93)
        at org.graalvm.nativeimage.base/com.oracle.svm.util.ReflectionUtil.lookupMethod(ReflectionUtil.java:80)
        at org.graalvm.nativeimage.builder/com.oracle.svm.core.jdk.JNIRegistrationUtil.method(JNIRegistrationUtil.java:92)
        at org.graalvm.nativeimage.builder/com.oracle.svm.hosted.javafx.JavaFXJNI.registerGlassGTK3(JavaFXJNI.java:140)
        at org.graalvm.nativeimage.builder/com.oracle.svm.hosted.javafx.JavaFXFeature.enableGlassGTK3(JavaFXFeature.java:188)
        at org.graalvm.nativeimage.builder/com.oracle.svm.hosted.javafx.JavaFXFeature.enableJavaFX(JavaFXFeature.java:157)
        at org.graalvm.nativeimage.pointsto/com.oracle.graal.pointsto.meta.AnalysisElement$ElementNotification.lambda$notifyCallback$0(AnalysisElement.java:152)
        at java.base/java.util.concurrent.FutureTask.run(FutureTask.java:328)
        at org.graalvm.nativeimage.pointsto/com.oracle.graal.pointsto.util.AnalysisFuture.ensureDone(AnalysisFuture.java:69)
        at org.graalvm.nativeimage.pointsto/com.oracle.graal.pointsto.meta.AnalysisField.lambda$beforeFieldValueAccess$0(AnalysisField.java:489)
        at org.graalvm.nativeimage.pointsto/com.oracle.graal.pointsto.meta.AnalysisType.forAllSuperTypes(AnalysisType.java:753)
        at org.graalvm.nativeimage.pointsto/com.oracle.graal.pointsto.meta.AnalysisType.forAllSuperTypes(AnalysisType.java:736)
        at org.graalvm.nativeimage.pointsto/com.oracle.graal.pointsto.meta.AnalysisType.forAllSuperTypes(AnalysisType.java:732)
        at org.graalvm.nativeimage.pointsto/com.oracle.graal.pointsto.meta.AnalysisField.beforeFieldValueAccess(AnalysisField.java:483)
        at org.graalvm.nativeimage.builder/com.oracle.svm.hosted.ameta.FieldValueInterceptionSupport.computeAndCacheFieldValueInterceptor(FieldValueInterceptionSupport.java:158)
        at org.graalvm.nativeimage.builder/com.oracle.svm.hosted.ameta.FieldValueInterceptionSupport.lookupFieldValueInterceptor(FieldValueInterceptionSupport.java:147)
        at org.graalvm.nativeimage.builder/com.oracle.svm.hosted.ameta.FieldValueInterceptionSupport.hasFieldValueTransformer(FieldValueInterceptionSupport.java:230)
        at org.graalvm.nativeimage.builder/com.oracle.svm.hosted.ameta.CustomTypeFieldHandler.handleField(CustomTypeFieldHandler.java:61)
        at org.graalvm.nativeimage.builder/com.oracle.svm.hosted.analysis.NativeImagePointsToAnalysis.onFieldAccessed(NativeImagePointsToAnalysis.java:130)
        at org.graalvm.nativeimage.pointsto/com.oracle.graal.pointsto.meta.AnalysisUniverse.onFieldAccessed(AnalysisUniverse.java:743)
        at org.graalvm.nativeimage.pointsto/com.oracle.graal.pointsto.meta.AnalysisField.lambda$registerAsWritten$0(AnalysisField.java:273)
        at org.graalvm.nativeimage.pointsto/com.oracle.graal.pointsto.util.AtomicUtils.atomicSetAndRun(AtomicUtils.java:49)
        at org.graalvm.nativeimage.pointsto/com.oracle.graal.pointsto.meta.AnalysisField.registerAsWritten(AnalysisField.java:270)
        at org.graalvm.nativeimage.pointsto/com.oracle.graal.pointsto.flow.MethodTypeFlowBuilder.registerUsedElements(MethodTypeFlowBuilder.java:350)
        at org.graalvm.nativeimage.pointsto/com.oracle.graal.pointsto.flow.MethodTypeFlowBuilder.parse(MethodTypeFlowBuilder.java:271)
        at org.graalvm.nativeimage.pointsto/com.oracle.graal.pointsto.flow.MethodTypeFlowBuilder.apply(MethodTypeFlowBuilder.java:715)
        at org.graalvm.nativeimage.pointsto/com.oracle.graal.pointsto.flow.MethodTypeFlow.createFlowsGraph(MethodTypeFlow.java:167)
        at org.graalvm.nativeimage.pointsto/com.oracle.graal.pointsto.flow.MethodTypeFlow.ensureFlowsGraphCreated(MethodTypeFlow.java:152)
        at org.graalvm.nativeimage.pointsto/com.oracle.graal.pointsto.flow.MethodTypeFlow.getOrCreateMethodFlowsGraphInfo(MethodTypeFlow.java:110)
        at org.graalvm.nativeimage.pointsto/com.oracle.graal.pointsto.typestate.DefaultAnalysisPolicy.staticRootMethodGraph(DefaultAnalysisPolicy.java:216)
        at org.graalvm.nativeimage.pointsto/com.oracle.graal.pointsto.PointsToAnalysis.lambda$addRootMethod$1(PointsToAnalysis.java:375)
        at org.graalvm.nativeimage.pointsto/com.oracle.graal.pointsto.AbstractAnalysisEngine$1.run(AbstractAnalysisEngine.java:341)
        ... 9 more
Caused by: java.lang.NoSuchMethodException: com.sun.glass.ui.gtk.GtkView.notifyInputMethodDraw(java.lang.String,int,int,int,[B)
        at java.base/java.lang.Class.getDeclaredMethod(Class.java:2424)
        at org.graalvm.nativeimage.base/com.oracle.svm.util.ReflectionUtil.lookupMethod(ReflectionUtil.java:85)
        ... 40 more
Caused by: com.oracle.svm.util.ReflectionUtil$ReflectionUtilError: java.lang.NoSuchMethodException: com.sun.glass.ui.gtk.GtkView.notifyInputMethodDraw(java.lang.String,int,int,int,[B)
        at org.graalvm.nativeimage.base/com.oracle.svm.util.ReflectionUtil.lookupMethod(ReflectionUtil.java:93)
        at org.graalvm.nativeimage.base/com.oracle.svm.util.ReflectionUtil.lookupMethod(ReflectionUtil.java:80)
        at org.graalvm.nativeimage.builder/com.oracle.svm.core.jdk.JNIRegistrationUtil.method(JNIRegistrationUtil.java:92)
        at org.graalvm.nativeimage.builder/com.oracle.svm.hosted.javafx.JavaFXJNI.registerGlassGTK3(JavaFXJNI.java:140)
        at org.graalvm.nativeimage.builder/com.oracle.svm.hosted.javafx.JavaFXFeature.enableGlassGTK3(JavaFXFeature.java:188)
        at org.graalvm.nativeimage.builder/com.oracle.svm.hosted.javafx.JavaFXFeature.enableJavaFX(JavaFXFeature.java:157)
        at org.graalvm.nativeimage.pointsto/com.oracle.graal.pointsto.meta.AnalysisElement$ElementNotification.lambda$notifyCallback$0(AnalysisElement.java:152)
        at java.base/java.util.concurrent.FutureTask.run(FutureTask.java:328)
        at org.graalvm.nativeimage.pointsto/com.oracle.graal.pointsto.util.AnalysisFuture.ensureDone(AnalysisFuture.java:69)
        at org.graalvm.nativeimage.pointsto/com.oracle.graal.pointsto.meta.AnalysisField.lambda$beforeFieldValueAccess$0(AnalysisField.java:489)
        at org.graalvm.nativeimage.pointsto/com.oracle.graal.pointsto.meta.AnalysisType.forAllSuperTypes(AnalysisType.java:753)
        at org.graalvm.nativeimage.pointsto/com.oracle.graal.pointsto.meta.AnalysisType.forAllSuperTypes(AnalysisType.java:736)
        at org.graalvm.nativeimage.pointsto/com.oracle.graal.pointsto.meta.AnalysisType.forAllSuperTypes(AnalysisType.java:732)
        at org.graalvm.nativeimage.pointsto/com.oracle.graal.pointsto.meta.AnalysisField.beforeFieldValueAccess(AnalysisField.java:483)
        at org.graalvm.nativeimage.builder/com.oracle.svm.hosted.ameta.FieldValueInterceptionSupport.computeAndCacheFieldValueInterceptor(FieldValueInterceptionSupport.java:158)
        at org.graalvm.nativeimage.builder/com.oracle.svm.hosted.ameta.FieldValueInterceptionSupport.lookupFieldValueInterceptor(FieldValueInterceptionSupport.java:147)
        at org.graalvm.nativeimage.builder/com.oracle.svm.hosted.ameta.FieldValueInterceptionSupport.hasFieldValueTransformer(FieldValueInterceptionSupport.java:230)
        at org.graalvm.nativeimage.builder/com.oracle.svm.hosted.ameta.CustomTypeFieldHandler.handleField(CustomTypeFieldHandler.java:61)
        at org.graalvm.nativeimage.builder/com.oracle.svm.hosted.analysis.NativeImagePointsToAnalysis.onFieldAccessed(NativeImagePointsToAnalysis.java:130)
        at org.graalvm.nativeimage.pointsto/com.oracle.graal.pointsto.meta.AnalysisUniverse.onFieldAccessed(AnalysisUniverse.java:743)
        at org.graalvm.nativeimage.pointsto/com.oracle.graal.pointsto.meta.AnalysisField.lambda$registerAsWritten$0(AnalysisField.java:273)
        at org.graalvm.nativeimage.pointsto/com.oracle.graal.pointsto.util.AtomicUtils.atomicSetAndRun(AtomicUtils.java:49)
        at org.graalvm.nativeimage.pointsto/com.oracle.graal.pointsto.meta.AnalysisField.registerAsWritten(AnalysisField.java:270)
        at org.graalvm.nativeimage.pointsto/com.oracle.graal.pointsto.flow.MethodTypeFlowBuilder.registerUsedElements(MethodTypeFlowBuilder.java:350)
        at org.graalvm.nativeimage.pointsto/com.oracle.graal.pointsto.flow.MethodTypeFlowBuilder.parse(MethodTypeFlowBuilder.java:271)
        at org.graalvm.nativeimage.pointsto/com.oracle.graal.pointsto.flow.MethodTypeFlowBuilder.apply(MethodTypeFlowBuilder.java:715)
        at org.graalvm.nativeimage.pointsto/com.oracle.graal.pointsto.flow.MethodTypeFlow.createFlowsGraph(MethodTypeFlow.java:167)
        at org.graalvm.nativeimage.pointsto/com.oracle.graal.pointsto.flow.MethodTypeFlow.ensureFlowsGraphCreated(MethodTypeFlow.java:152)
        at org.graalvm.nativeimage.pointsto/com.oracle.graal.pointsto.flow.MethodTypeFlow.getOrCreateMethodFlowsGraphInfo(MethodTypeFlow.java:110)
        at org.graalvm.nativeimage.pointsto/com.oracle.graal.pointsto.typestate.DefaultAnalysisPolicy.staticRootMethodGraph(DefaultAnalysisPolicy.java:216)
        at org.graalvm.nativeimage.pointsto/com.oracle.graal.pointsto.PointsToAnalysis.lambda$addRootMethod$1(PointsToAnalysis.java:375)
        at org.graalvm.nativeimage.pointsto/com.oracle.graal.pointsto.AbstractAnalysisEngine$1.run(AbstractAnalysisEngine.java:341)
        at org.graalvm.nativeimage.pointsto/com.oracle.graal.pointsto.util.CompletionExecutor.executeCommand(CompletionExecutor.java:166)
        at org.graalvm.nativeimage.pointsto/com.oracle.graal.pointsto.util.CompletionExecutor.lambda$executeService$0(CompletionExecutor.java:152)
        at java.base/java.util.concurrent.ForkJoinTask$RunnableExecuteAction.compute(ForkJoinTask.java:1735)
        at java.base/java.util.concurrent.ForkJoinTask$RunnableExecuteAction.compute(ForkJoinTask.java:1726)
        at java.base/java.util.concurrent.ForkJoinTask$InterruptibleTask.exec(ForkJoinTask.java:1650)
        at java.base/java.util.concurrent.ForkJoinTask.doExec(ForkJoinTask.java:507)
        at java.base/java.util.concurrent.ForkJoinPool$WorkQueue.topLevelExec(ForkJoinPool.java:1394)
        at java.base/java.util.concurrent.ForkJoinPool.runWorker(ForkJoinPool.java:1970)
        at java.base/java.util.concurrent.ForkJoinWorkerThread.run(ForkJoinWorkerThread.java:187)
Caused by: java.lang.NoSuchMethodException: com.sun.glass.ui.gtk.GtkView.notifyInputMethodDraw(java.lang.String,int,int,int,[B)
        at java.base/java.lang.Class.getDeclaredMethod(Class.java:2424)
        at org.graalvm.nativeimage.base/com.oracle.svm.util.ReflectionUtil.lookupMethod(ReflectionUtil.java:85)
        ... 40 more
Caused by: java.lang.NoSuchMethodException: com.sun.glass.ui.gtk.GtkView.notifyInputMethodDraw(java.lang.String,int,int,int,[B)
        at java.base/java.lang.Class.getDeclaredMethod(Class.java:2424)
        at org.graalvm.nativeimage.base/com.oracle.svm.util.ReflectionUtil.lookupMethod(ReflectionUtil.java:85)
        at org.graalvm.nativeimage.base/com.oracle.svm.util.ReflectionUtil.lookupMethod(ReflectionUtil.java:80)
        at org.graalvm.nativeimage.builder/com.oracle.svm.core.jdk.JNIRegistrationUtil.method(JNIRegistrationUtil.java:92)
        at org.graalvm.nativeimage.builder/com.oracle.svm.hosted.javafx.JavaFXJNI.registerGlassGTK3(JavaFXJNI.java:140)
        at org.graalvm.nativeimage.builder/com.oracle.svm.hosted.javafx.JavaFXFeature.enableGlassGTK3(JavaFXFeature.java:188)
        at org.graalvm.nativeimage.builder/com.oracle.svm.hosted.javafx.JavaFXFeature.enableJavaFX(JavaFXFeature.java:157)
        at org.graalvm.nativeimage.pointsto/com.oracle.graal.pointsto.meta.AnalysisElement$ElementNotification.lambda$notifyCallback$0(AnalysisElement.java:152)
        at java.base/java.util.concurrent.FutureTask.run(FutureTask.java:328)
        at org.graalvm.nativeimage.pointsto/com.oracle.graal.pointsto.util.AnalysisFuture.ensureDone(AnalysisFuture.java:69)
        at org.graalvm.nativeimage.pointsto/com.oracle.graal.pointsto.meta.AnalysisField.lambda$beforeFieldValueAccess$0(AnalysisField.java:489)
        at org.graalvm.nativeimage.pointsto/com.oracle.graal.pointsto.meta.AnalysisType.forAllSuperTypes(AnalysisType.java:753)
        at org.graalvm.nativeimage.pointsto/com.oracle.graal.pointsto.meta.AnalysisType.forAllSuperTypes(AnalysisType.java:736)
        at org.graalvm.nativeimage.pointsto/com.oracle.graal.pointsto.meta.AnalysisType.forAllSuperTypes(AnalysisType.java:732)
        at org.graalvm.nativeimage.pointsto/com.oracle.graal.pointsto.meta.AnalysisField.beforeFieldValueAccess(AnalysisField.java:483)
        at org.graalvm.nativeimage.builder/com.oracle.svm.hosted.ameta.FieldValueInterceptionSupport.computeAndCacheFieldValueInterceptor(FieldValueInterceptionSupport.java:158)
        at org.graalvm.nativeimage.builder/com.oracle.svm.hosted.ameta.FieldValueInterceptionSupport.lookupFieldValueInterceptor(FieldValueInterceptionSupport.java:147)
        at org.graalvm.nativeimage.builder/com.oracle.svm.hosted.ameta.FieldValueInterceptionSupport.hasFieldValueTransformer(FieldValueInterceptionSupport.java:230)
        at org.graalvm.nativeimage.builder/com.oracle.svm.hosted.ameta.CustomTypeFieldHandler.handleField(CustomTypeFieldHandler.java:61)
        at org.graalvm.nativeimage.builder/com.oracle.svm.hosted.analysis.NativeImagePointsToAnalysis.onFieldAccessed(NativeImagePointsToAnalysis.java:130)
        at org.graalvm.nativeimage.pointsto/com.oracle.graal.pointsto.meta.AnalysisUniverse.onFieldAccessed(AnalysisUniverse.java:743)
        at org.graalvm.nativeimage.pointsto/com.oracle.graal.pointsto.meta.AnalysisField.lambda$registerAsWritten$0(AnalysisField.java:273)
        at org.graalvm.nativeimage.pointsto/com.oracle.graal.pointsto.util.AtomicUtils.atomicSetAndRun(AtomicUtils.java:49)
        at org.graalvm.nativeimage.pointsto/com.oracle.graal.pointsto.meta.AnalysisField.registerAsWritten(AnalysisField.java:270)
        at org.graalvm.nativeimage.pointsto/com.oracle.graal.pointsto.flow.MethodTypeFlowBuilder.registerUsedElements(MethodTypeFlowBuilder.java:350)
        at org.graalvm.nativeimage.pointsto/com.oracle.graal.pointsto.flow.MethodTypeFlowBuilder.parse(MethodTypeFlowBuilder.java:271)
        at org.graalvm.nativeimage.pointsto/com.oracle.graal.pointsto.flow.MethodTypeFlowBuilder.apply(MethodTypeFlowBuilder.java:715)
        at org.graalvm.nativeimage.pointsto/com.oracle.graal.pointsto.flow.MethodTypeFlow.createFlowsGraph(MethodTypeFlow.java:167)
        at org.graalvm.nativeimage.pointsto/com.oracle.graal.pointsto.flow.MethodTypeFlow.ensureFlowsGraphCreated(MethodTypeFlow.java:152)
        at org.graalvm.nativeimage.pointsto/com.oracle.graal.pointsto.flow.MethodTypeFlow.getOrCreateMethodFlowsGraphInfo(MethodTypeFlow.java:110)
        at org.graalvm.nativeimage.pointsto/com.oracle.graal.pointsto.typestate.DefaultAnalysisPolicy.staticRootMethodGraph(DefaultAnalysisPolicy.java:216)
        at org.graalvm.nativeimage.pointsto/com.oracle.graal.pointsto.PointsToAnalysis.lambda$addRootMethod$1(PointsToAnalysis.java:375)
        at org.graalvm.nativeimage.pointsto/com.oracle.graal.pointsto.AbstractAnalysisEngine$1.run(AbstractAnalysisEngine.java:341)
        at org.graalvm.nativeimage.pointsto/com.oracle.graal.pointsto.util.CompletionExecutor.executeCommand(CompletionExecutor.java:166)
        at org.graalvm.nativeimage.pointsto/com.oracle.graal.pointsto.util.CompletionExecutor.lambda$executeService$0(CompletionExecutor.java:152)
        at java.base/java.util.concurrent.ForkJoinTask$RunnableExecuteAction.compute(ForkJoinTask.java:1735)
        at java.base/java.util.concurrent.ForkJoinTask$RunnableExecuteAction.compute(ForkJoinTask.java:1726)
        at java.base/java.util.concurrent.ForkJoinTask$InterruptibleTask.exec(ForkJoinTask.java:1650)
        at java.base/java.util.concurrent.ForkJoinTask.doExec(ForkJoinTask.java:507)
        at java.base/java.util.concurrent.ForkJoinPool$WorkQueue.topLevelExec(ForkJoinPool.java:1394)
        at java.base/java.util.concurrent.ForkJoinPool.runWorker(ForkJoinPool.java:1970)
        at java.base/java.util.concurrent.ForkJoinWorkerThread.run(ForkJoinWorkerThread.java:187)
------------------------------------------------------------------------------------------------------------------------
                        0.3s (4.8% of total time) in 23 GCs | Peak RSS: 0.69GB | CPU load: 11.17
========================================================================================================================
Failed generating 'java-demo' after 5.9s.
com.oracle.svm.driver.NativeImage$NativeImageError
        at org.graalvm.nativeimage.driver/com.oracle.svm.driver.NativeImage.showError(NativeImage.java:2435)
        at org.graalvm.nativeimage.driver/com.oracle.svm.driver.NativeImage.build(NativeImage.java:2017)
        at org.graalvm.nativeimage.driver/com.oracle.svm.driver.NativeImage.performBuild(NativeImage.java:1976)
        at org.graalvm.nativeimage.driver/com.oracle.svm.driver.NativeImage.main(NativeImage.java:1958)
        at java.base@24.0.2/java.lang.invoke.LambdaForm$DMH/sa346b79c.invokeStaticInit(LambdaForm$DMH)

> Task :nativeCompile FAILED

FAILURE: Build failed with an exception.

* What went wrong:
Execution failed for task ':nativeCompile'.
> Process 'command '/opt/bellsoft/liberica-vm-full-24.2.2-openjdk24/bin/native-image'' finished with non-zero exit value 1

* Try:
> Run with --stacktrace option to get the stack trace.
> Run with --info or --debug option to get more log output.
> Run with --scan to get full insights.
> Get more help at https://help.gradle.org.

BUILD FAILED in 7s
6 actionable tasks: 6 executed



```


## Contact

* Feel free to contact me.

## Links
* Github actions: https://bell-sw.com/blog/how-to-create-javafx-native-images/
* https://github.com/actions/runner-images?tab=readme-ov-file

