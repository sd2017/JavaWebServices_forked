rem
rem 

call ..\setenv_jaws.bat
rem

rem Add the sample classes, and the JAXM and JAXP jars
set CLASSPATH=%CLASSPATH%;%WORK%\examples\ch9;%JAXM_LIB%\dom4j.jar;%JAXM_LIB%\xalan.jar;%JAXM_LIB%\jaxm.jar;%JAXM_LIB%\crimson.jar;%JAXM_LIB%\log4j.jar;%JAXM_HOME%\jaxm\jaxm-client.jar;%JAXP_HOME%\xalan.jar


rem Set the Tomcat home for 3.2.n
set TOMCAT_HOME=%WORK%\jakarta-tomcat-3.2.4
set CATALINA_HOME=%TOMCAT_HOME%



