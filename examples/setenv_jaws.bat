rem Java Web Services examples
rem Set up common environment variables for installed software

rem Installation directory
SET WORK=c:\work

rem Location of Java 
SET JAVA_HOME=%WORK%\JDK1.3.1_02
SET J2EE_HOME=%WORK%\j2sdkee1.3.1

rem The Jakarta Tomcats are set appropriately in the chapter scripts

SET ANT_HOME=%WORK%\ANT\jakarta-ant-1.4.1

SET JAVA_XML_HOME=%WORK%\java_xml
SET JAXM_HOME=%JAVA_XML_HOME%\jaxm-1.0.1-ea1
SET JAXM_LIB=%JAVA_XML_HOME%\jaxm-1.0.1-ea1\lib
SET JAXP_HOME=%JAVA_XML_HOME%\jaxp-1.2-ea1
SET JAXR_LIB=%JAVA_XML_HOME%\jaxr-1.0-ea_01\lib
SET JAXRPC_LIB=%JAVA_XML_HOME%\jaxrpc-1.0-ea1\lib
SET SOAP_HOME=%WORK%\ApacheSoap\soap-2_2
SET XERCES_JAR=%WORK%\XERCES\xerces-1_4_4\xerces.jar
SET JSSE_HOME=%WORK%\jsse1.0.2

rem Jars for JCE, JGSS, and XALAN will be copied to where they are used.
rem WASP_UDDI is set up in the chapter 6 script.

rem Clear the PATH and then set it to the java binaries directory
SET PATH=.;%JAVA_HOME%\bin

rem Clear the CLASSPATH and then set it for commonly used jars
SET CLASSPATH=.;%JAVA_HOME%\jre\lib\rt.jar;%JAVA_HOME%\jre\lib\tools.jar;%JAVA_HOME%\jre\lib\jaws.jar;%XERCES_JAR%;%JAXM_LIB%\activation.jar;%JAXM_LIB%\mail.jar;%SOAP_HOME%\lib\soap.jar;%SOAP_HOME%

