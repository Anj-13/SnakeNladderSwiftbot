@ECHO OFF
SETLOCAL
SET "BASEDIR=%~dp0"
FOR %%I IN ("%BASEDIR%") DO SET "MAVEN_PROJECTBASEDIR=%%~fI"
IF "%MAVEN_PROJECTBASEDIR:~-1%"=="\" SET "MAVEN_PROJECTBASEDIR=%MAVEN_PROJECTBASEDIR:~0,-1%"
SET "JAVA_EXE=%JAVA_HOME%\bin\java.exe"
IF NOT EXIST "%JAVA_EXE%" SET "JAVA_EXE=java.exe"
"%JAVA_EXE%" -classpath "%MAVEN_PROJECTBASEDIR%\.mvn\wrapper\maven-wrapper.jar" "-Dmaven.multiModuleProjectDirectory=%MAVEN_PROJECTBASEDIR%" org.apache.maven.wrapper.MavenWrapperMain %*
ENDLOCAL