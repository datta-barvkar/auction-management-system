set PWD=%~dp0
set TBAPI_CLASSPATH=.,"%PWD%config"
set TBAPI_JAR="%PWD%target/boot-0.0.1-SNAPSHOT.war"
set JAVA_OPTS=-Xmx2048M  -agentlib:jdwp=transport=dt_socket,server=y,address=8000,suspend=n -Dloader.path=%TBAPI_CLASSPATH% -Darchaius.configurationSource.defaultFileName=application.properties

if not "%JAVA_HOME8%" == "" goto SetJ8Home
if not "%JAVA_HOME%" == "" goto OkJHome

echo.
echo Error: JAVA_HOME not found in your environment. >&2
echo Please set the JAVA_HOME variable in your environment to match the >&2
echo location of your Java 8 installation. >&2
echo.
goto error

:SetJ8Home
set JAVA_HOME=%JAVA_HOME8%
echo %JAVA_HOME%

:OkJHome

SET TBAPI_JAVA_EXE="%JAVA_HOME%\bin\java.exe"
%TBAPI_JAVA_EXE% %JAVA_OPTS% -jar %TBAPI_JAR%
