rem 
rem 

call ..\setenv_jaws.bat
rem
SET WASP_HOME=%WORK%\WaspUDDI3.1_final
SET WASP_LIB=%WASP_HOME%\lib
SET WASP_TOMCAT=%WORK%\tomcat

rem aliases
SET TOMCAT_HOME=%WASP_TOMCAT%
SET CATALINA_HOME=%WASP_TOMCAT%

rem
set CLASSPATH=%CLASSPATH%;%WORK%\examples\ch6;%WASP_HOME%\app\uddi\WASP-INF\lib\uddiclient.jar;%WASP_LIB%\wasp.jar;%JAXR_LIB%;%JAXR_LIB%\fscontext.jar;%JAXR_LIB%\jaxr-api.jar;%JAXR_LIB%\jaxr-apidoc.jar;%JAXR_LIB%\jaxr-ri.jar;%JAXR_LIB%\jcert.jar;%JAXR_LIB%\jnet.jar;%JAXR_LIB%\jsse.jar;%JAXR_LIB%\providerutil.jar;%JAXR_LIB%\relaxer.jar


REM The properties file needs to be in your home directory to get JAXR to work.
REM On Windows 2000 and NT, your home directory is the value of
REM the USERPROFILE variable.  This is VERY important!

copy /Y .jaxr.properties "%USERPROFILE%"
