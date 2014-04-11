icomplain-desktop
=================

发牢骚软件桌面版(I complain)

## Introduction
* Put your dirty, ugly, secret complaint into this software. Nobody is able to see it except you.

## Build
* To build this project, you have to dowload SWT jar file manually due to the lack of SWT in Maven Repository.
* After that, set groupId to org.eclipse.swt, artifactId to swt-linux-64 and version to 4.2.2.RELEASE and install it to your local Maven Repository.
* Finally, execute `mvn clean package -Dmaven.test.skip=true`
