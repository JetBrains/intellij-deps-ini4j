# ini4j
Fork of ini4j library with patches needed for IntelliJ IDEA

Original project is located at https://sourceforge.net/projects/ini4j/

Requirements to build the lib in IDEA:
1. Set Maven to version 2
2. Configure Project to use Java 1.6 SDK
3. Set Language level to Java 6

Optionally:
- Create Run configuration to execute goals up to *package*. Further goals are not necessary for IDEA purposes.
- Change the pom.xml and src/changes/changes.xml to reflect lib version you need.

IDEA Project configuration has been committed to the repository to allow skipping the above configuration after cloning.