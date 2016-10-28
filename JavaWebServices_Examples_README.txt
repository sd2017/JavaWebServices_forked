README Notes for the O'Reilly book "Java Web Services" examples

______________________________________________________________________________
CONTENTS

    - Introduction
    - Software technologies 
    - Downloading and installing 
    - Setting up the installed software
    - Running the samples
______________________________________________________________________________
INTRODUCTION

The examples in this book use the public distributions of several technologies.
If you want to explore all the samples in the book you need to install all
the software listed in this document. 

As these distributions are public products that are continuously evolving, 
the Web locations, compatibilities, and file names could be obsoleted at 
any time. Try to stay close to the prescribed releases for the best results.
For example, in early 2002, when we are writing this document, we recommend
several Tomcat releases for different purposes, release 3.2.4 and release 4.0.2. 
We reference them as 3.2.n and 4.0.n to suggest that no 3.3 release, for 
example, is a preferred release for either of the sets of samples.

While some of the software packages include other components, such as the 
XML parser, we recommend that you access the package we describe to get the 
components and documents we set up and used.

Each product has its own instructions for installation and startup.
This document is not a substitute for the software provider's documentation.
We intend instead to focus you on quickly accessing and using these products
as we did with a networked Windows 2000 Professional system with 256MB RAM.

IMPORTANT - The companies that provide software technologies might constrain
            downloads of some products or versions to some geopolitical 
            locations, companies, and individuals.

______________________________________________________________________________
SOFTWARE TECHNOLOGIES

The following software is referenced by the samples in this book:

Apache:   www.apache.org and xml.apache.org
	  -- Ant 1.4.1
	  -- SOAP 2.2
	  -- Tomcat 3.2.n
	  -- Tomcat 4.0.n
	  -- Xalan-J2.2
	  -- Xerces_Java 1.4.n

JavaSoft: java.sun.com
	  -- JAXM
	  -- JAXR
          -- JAXP
          -- J2SE         
          -- J2EE
          -- JCE
	  -- JSSE
          -- JAAS

Systinet: www.systinet.com
	  -- WASP UDDI Standard 3.1
          -- WASP Java GSS API

The next sections provide basic info for accessing and installing each of the 
software technologies listed. In general, the download is the binary zip file and 
the typical installation (usually everything) is accepted.

As presented in the following sections, the total footprint of the download files
is   MB and the installed files use an additional   MB.

_____________________________________________________________________________
DOWNLOADING AND INSTALLING

JAVASOFT J2SE 1.3.1_02

This software is essential to some installations so you might as well install 
it first.
Basic doc: http://java.sun.com/j2se/1.3/install-windows.html
Download URL: http://java.sun.com/Download5  (license agreement)
Package (size): j2sdk-1_3_1_02-win.exe (45 MB) into a temp directory.
Install: Run the installer.
Footprint: After you delete the installer, 60 MB

------------------------------------------------------------------------------
JAVASOFT J2EE
Basic doc & Download URL: http://java.sun.com/j2ee/sdk_1.3/1.3_01/
Package (size): j2sdkee-1_3_01-win.exe  ( 17 MB)
WE RECOMMEND USING THE STANDARD J2EE 1.3 REFERENCE IMPLEMENTATION 
SINCE IT INCLUDES A CLOUDSCAPE DBMS THAT IS NEEDED FOR CHAPTER 6 EXAMPLES.

Install: Unpack (choose the option to "use folder names") into a new directory.
Footprint: After you delete the installer, 25 MB

------------------------------------------------------------------------------
JAVASOFT J2SE 1.3.1_02
Basic doc & Download URL: http://java.sun.com/j2se/1.3/
Package (size): j2sdkee-1_3_01-win.exe  ( 17 MB)
Install: Unpack (choose the option to "use folder names") into a new directory.
Footprint: After you delete the installer, 25 MB


------------------------------------------------------------------------------
APACHE ANT 1.4.1

Basic doc: http://jakarta.apache.org/ant/manual/index.html
Download URL: http://jakarta.apache.org/builds/jakarta-ant/release/v1.4.1/bin/
Package (size): jakarta-ant-1.4.1-bin.zip (2.7 MB)
Install: Unpack (choose the option to "use folder names") into a new directory.
Footprint: After you delete the installer, 12 MB

------------------------------------------------------------------------------
APACHE SOAP 2.2

Basic doc: http://xml.apache.org/soap/docs/index.html
Download URL: http://xml.apache.org/dist/soap/version-2.2/
Package (size): soap-bin-2.2.zip (1 MB)
Install: Unpack (choose the option to "use folder names") into a new directory.
Footprint: After you delete the installer, 5 MB

------------------------------------------------------------------------------
APACHE TOMCAT 3.2.n

This Tomcat release is used to interact with Apache SOAP under the original 
Tomcat design. Note that two Tomcat servers are described in the 
samples, both using the same default port,8080. The two Tomcat servers aren't 
intended to interact so install both Tomcat servers but run one at a time. 

Basic doc: http://jakarta.apache.org/tomcat/tomcat-3.2-doc/index.html
Download URL: http://jakarta.apache.org/tomcat/tomcat-3.2-doc/index.html
Package (Size): jakarta-tomcat-3.2.4.zip  (2.4 MB)             
Install: Unpack (choose the option to "use folder names") into a new directory.
Footprint: After you delete the download package, 4.5 MB

------------------------------------------------------------------------------
APACHE TOMCAT 4.0.1

This Tomcat release is used in chapter 7 to interact with JAX.  

Basic doc: http://jakarta.apache.org/tomcat/tomcat-4.0-doc/introduction.html
Download URL: http://jakarta.apache.org/builds/jakarta-tomcat-4.0/release/v4.0.1/bin
Package (Size): jakarta-tomcat-4.0.1.zip. (5 MB) 
Install: Unpack (choose the option to "use folder names") into a new directory.
Footprint: After you delete the download package, 18 MB

------------------------------------------------------------------------------
APACHE XALAN-J2.2

Basic doc: http://xml.apache.org/xalan-j/getstarted.html
Download URL: same
Package (size): xalan-j_2_2-bin.zip 
Install: Unpack (choose the option to "use folder names") into a new directory.
Footprint: After you delete the download package, 29 MB

------------------------------------------------------------------------------
APACHE XERCES-JAVA 1.4.n

Basic doc: http://xml.apache.org/xerces2-j/install.html
Download URL: http://xml.apache.org/dist/xerces-j/
Package (size): Xerces-J-bin.1.4.4.zip (4 MB)
Install: Unpack (choose the option to "use folder names") into a new directory.
Footprint: After you delete the download package, 23 MB

------------------------------------------------------------------------------
JAVASOFT JAXM, JAXR, and JAXP

The Java XML Pack is an all-in-one download of Java technologies for XML. 
The version at the time of this book is "Winter 2001".

Basic doc: http://java.sun.com/xml/downloads/javaxmlpack.html
Download URL: http://java.sun.com/Download5 (license agreement)
Package (size): java_xml_pack-winter01_01-dev.zip (12 MB)
Install: Unpack (choose the option to "use folder names") into a new directory.
Footprint: After you delete the download package, 26 MB

------------------------------------------------------------------------------
JAVASOFT JSSE 1.0.2

Basic doc: http://java.sun.com/products/jsse/INSTALL.html
Download URL: http://java.sun.com/products/jsse/index-102.html
              Choose "Download JSSE 1.0.2 global software and 
                      documentation with support for strong encryption."
              You must sign on or register to obtain this software product.  
Package (size): jsse-1_0_2-gl.zip (0.6 MB)
Install: Unpack (choose the option to "use folder names") into a new directory.
Footprint: After you delete the download package, 1.4 MB

------------------------------------------------------------------------------
JAVASOFT JCE
Basic doc: http://java.sun.com/products/jce/jce121_readme.txt
Download URL: http://java.sun.com/products/jce/index-121.html
Package (size): jce-1_2_1.zip  ( .43 MB)
Install: Unpack (choose the option to "use folder names") into a new directory.
Footprint: After you delete the installer, 1 MB

------------------------------------------------------------------------------
JAVASOFT JAAS
Basic doc: http://java.sun.com/products/jaas/index-10.html
Download URL: http://java.sun.com/cgi-bin/download3.cgi
Package (size): jaas1_0.zip ( .1 MB)
Install: Unpack (choose the option to "use folder names") into a new directory.
Footprint: After you delete the installer, .2 MB

------------------------------------------------------------------------------
SYSTINET WASP UDDI SERVER 3.1 (includes TOMCAT 3.2.4)

Basic doc: http://www.systinet.com/products/wasp_uddi/doc/index.html
Download URL: http://www.systinet.com/products/wasp-uddi/download
Package (size): wasp_uddi_3.1final_tomcat.zip (15 MB)
Install: Unpack (choose the option to "use folder names") into a new directory.
Footprint: 37 MB (add'l Tomcat Server, 4.5 MB)

---------------------------------------------------------------------
SYSTINET WASP GSS API

Basic doc: http://www.systinet.com/products/gss/doc/index.html
Download URL: http://www.systinet.com/products/gss/download
Package (size): jgss-10.zip (1.2 MB)
Install: Unpack (choose the option to "use folder names") into a new directory.
Footprint: 2 MB 

____________________________________________________________________________
SETTING UP

1. Install all the downloads into one working directory. The environment 
   settings are preset for installation into a Windows directory at "c:\work".

2. Copy the JAWS examples directory into the working directory. 
  (In other words, "c:\work\examples" The working directory is %WORK% in scripts.)

3. Review the script %WORK%\examples\setenv_jaws.bat. The working directory is %WORK%.
   Adjust the working directory and paths as needed. The environment script 
   in each chapter folder is called by the sample scripts which in turn calls
   setenv_jaws.bat. The examples are preset to the default port values 
   of the components.

4. In each chapter, edit the chapter setup script 
   -- for example, %WORK%\examples\ch3\ch3.bat --
   to confirm that the script's paths agree with your installation.

5. For the examples in Chapters 3, 4,and 9 set up Tomcat 3.2.n as described below.

6. For the examples in Chapter 6, set up WASP and the UDDI server as described below.

7. For the examples in Chapter 7, set up Tomcat 4.0.1 as described below.

-----------------------------------------------------------------------------------
SETTING UP TOMCAT 3.2.n
   
1. Test the basic installation. Open a console window in the
   %WORK%\examples\ch3 folder and enter "ch3_tomcat". Tomcat should 
   start and display its port usage. Close the Tomcat window.
   
2. Adjust the Tomcat script %TOMCAT_HOME%\bin\tomcat.bat by locating the line:
     :chkClasspath
   Two lines down from that, change the set classpath statement to
   explicitly put the xerces jar at the beginning of the list. For example:
     set CP=c:\work\XERCES\xerces-1_4_4\xerces.jar;%CP%;%CLASSPATH%
   Then save the script.

3. Test the revised installation. 
   a. Start tomcat again.
   b. Open a browser and enter the address:  
        http://localhost:8080/soap/servlet/rpcrouter 
      The response expected is:
        "Sorry, I don't speak via HTTP GET- you have to use HTTP POST 
         to talk to me.".
   c. Close the Tomcat window.

4. Set up Tomcat for SOAP.
   a. Copy the SOAP webapps file soap.war into the 3.2.n Tomcat \webapps directory.
   b. Start tomcat again. 
   c. In a browser, enter "http://localhost:8080/soap"
      The expected result is an Apache SOAP page with options to 
      'Run the admin client' and to access the SOAP RPC router.

5. Set up new services
   a. Run the admin client.
   b. Click Deploy to create a new service.
      --------------------------------------------------------------
      The Apache SOAP User's Guide has good information on the steps 
      necessary to create message or rpc services. The User's Guide
      is located at %WORK%\ApacheSoap\soap-2_2\docs\guide\index.html. 
      Information specific to managing your services can be found at 
      %WORK%\ApacheSoap\soap-2_2\docs\guide\manage.html.
      --------------------------------------------------------------
   c. Use the StockQuantity RPC book sample as a test by entering in the form:
        -- ID Value "urn:stockhand"
        -- Scope "Application"
        -- Methods "getQty"
        -- Provider type "Java"
        -- Provider class "StockQuantity"
        -- Static value "No"
      then click Deploy at the bottom of the form.
      Click the List button to see the new service listed.
   d. In a console window at the %WORK%\examples\ch3 directory, enter as one line:
        java org.apache.soap.server.ServiceManagerClient ~   
          http://hostname:8080/soap/servlet/rpcrouter ~ 
          deploy PurchaseOrderAcceptorDD.xml
   e. Confirm the deployment by entering in the browser:
        http://localhost:8080/soap/admin
      then pressing the List button to see the new Services listed.

6. Copy the files in the %WORK%\examples\tomcat_3_webapps\classes directory
   to the Tomcat 3 subdirectory \webapps\examples\WEB-INF\classes.

You can run the samples in Chapters 3, 4 and 9 now.

_________________________________________________________________________________
SETTING UP TOMCAT 4.0.1
   
1. Test the basic installation. Open a console window in the
   %WORK%\examples\ch7 folder and enter "ch7_tomcat". Tomcat should start.
   In a browser and display its port usage. Close the Tomcat window.

2. Do the steps described in  the document %JAXM_HOME%\doc\tomcat.html". 
   In brief:
   a. Copy %JAXM_LIB%\*.jars and %JAXM_HOME%\jaxm\jaxm-client.jar 
      to the Tomcat 4 installation's \common\lib directory.
   b. Copy %JAXM_HOME%\samples\*.war and %JAXM_HOME%\jaxm\jaxm-provider.war 
      to the Tomcat 4 installation's \webapps directory
   
   Note that in our setup, setenv_jaws sets JAVA_HOME and chapter 7 scripts 
   set CATALINA_HOME.

3. Copy the files in the %WORK%\examples\tomcat_4_webapps\classes directory
   to the Tomcat 4 installation's \webapps\examples\WEB-INF\classes.

4. Edit the Tomcat 4 installation's \bin\catalina.bat. Change:
     set CLASSPATH=%CP%
   to (as a single line):
     set CLASSPATH=%CLASSPATH%;
                   %CATALINA_HOME%\common\lib\soap\classes;
                   %CATALINA_HOME%\webapps\examples\WEB-INF\classes;
                   %CATALINA_HOME%\webapps\examples;
                   %CP%

4. Test the installation.
   a. Open a console window to %WORK%\examples\ch7 then enter "ch7_tomcat".
   b. Open a browser then enter the address: http://localhost:8080/index.html
   
As they say: "If you're seeing this page via a web browser, it means you've 
setup Tomcat successfully. Congratulations!"


You can run the samples in Chapters 7 now.

_________________________________________________________________________________
SETTING UP SECURITY, THE UDDI SERVER AND JAXR

Setting Up Security
-------------------
1. Confirm that you the java that is set as Java_HOME is version 1.3.

2. Copy the %WORK%\jsse1.0.2\lib jars into the %JAVA_HOME%\jre\lib\ext 
   directory: jsse.jar, jnet.jar, and jcert.jar.

3. Copy the %WORK%\jce1.2.1\lib jars into the %JAVA_HOME%\jre\lib\ext 
   directory: US_export_policy.jar, local_policy.jar, jce1_2_1.jar, and
   sunjce_provider.jar.

4. Change the security providers in this file to show only
   the following list of providers in this preference order:
     # List of providers and their preference orders (see above):
     #
         security.provider.1=com.sun.crypto.provider.SunJCE
         security.provider.2=sun.security.provider.Sun
         security.provider.3=com.sun.rsajca.Provider
         security.provider.4=cryptix.jce.provider.CryptixCrypto
         security.provider.5=com.sun.net.ssl.internal.ssl.Provider
     #
5.  Save the edited file.


Setting Up the UDDI Server
--------------------------
Systinet WASP UDDI server requires a Tomcat server and a database server.  
WASP provides a pre-configured Cloudscape database and the examples in 
this book were tested using that database.

The Systinet WASP installation is quite complex. The following instructions 
focus you on the success we had in setting up the versions of the various
software component versions to demonstrate the functionality described in
the book. 

To Set Up the UDDI Server

1. From the %J2EE_HOME%\lib\cloudscape directory, copy RmiJdbc.jar and 
   cloudclient.jar to both %WASP_HOME%\app\uddi\WASP-INF\lib and
   %WASP_TOMCAT%\lib

2. Open a console window %WORK%\examples\ch6 directory then enter "ch6".
   This sets up all the path and classpaths for the installation. 
   (You might look at the scripts examples\ch6.bat and setenv_jaws.bat
   to confirm that they agree with your folder hierarchies and names.) 

3. Copy %WASP_HOME%\app\uddi\WASP-INF\lib\uddi.jar and security-ng.jar  
   to %WASP_TOMCAT%\lib


4. Change directory the WASP UDDI \bin directory then enter "install.bat".  
   a. When prompted for a host name, enter "localhost". 
   b. When prompted for a password for "admin", use the default value "changeit".
   
   When this process indicates a successful build, you have set up the UDDI
   server, the Tomcat server, and the Cloudscape database.

5. Test the installation.
   a. Open a console window in \examples\ch6 and enter "ch6_UDDI".
      Two windows open, one for Cloudscape and the other for Tomcat 3.2.
      The last line of the Tomcat startup indicates "HttpConnectionHandler on 8443"
   b. In a browser address line, enter:
         https://localhost:8443/web/uddi?configuration=yes.  
      You might have to accept certificates the first time you run this.       
      When you succeed, use the username "admin" and the password "changeit".

JAXR Reference Implementation
=============================

Chapter 6 uses JAXR for some UDDI client examples and uses Tomcat to 
run a simple client example. While you could install yet another Tomcat
server, the JAXR and UDDI functionality coexist peacefully on the
same Tomcat instance so this document suggests that you use the %WASP_TOMCAT%
for the JAXR setup. 

1. Check the location of the JAXR libraries and Tomcat in the setenv_jaws.bat file.

2. Copy the WAR file from %JAXR_HOME%\webapps to %CATALINA_HOME%\webapps.

4. Copy all JAR files from %JAXR_HOME%\lib to %CATALINA_HOME%\lib.

5.  Open a console window in \examples\ch6 and enter "ch6_UDDI".  

6. To test the JAXR installation, compile and run the JAXRFindBusiness.java
   example.  If you receive business information on the console, you did it!
 
_________________________________________________________________________________
RUNNING THE SAMPLES
After all that setup, the samples are scripted and ready to run just the way they are 
entered in the book. Each sample calls the chapter specific settings and then call the
common environment settings so you have very few opportunities for mistakes.

The pages in the book that reference the examples in these scripts are as follows:

Page  Script    Description
----  ------    ------------------------------
 33   3_tomcat 	 (implicit) 
 33   3_1 	 SimpleGenericHTTPSoapClient #1
 40   3_2 	 SimpleGenericHTTPSoapClient #2
 42   3_3 	 GenericHTTPSoapClient  #1
 46   3_tunnel   (explicit)
      3_4        GenericHTTPSoapClient #2
      3_5        GenericHTTPSoapClient #3
 49   3_6        GenericHTTPSWAClient #1
 50   3_7        GenericHTTPSWAClient #2
 54   4_tomcat   (implicit)
 54   4_1        GetBookPrice #1
 55   4_2        GetBookPrice #2
 65   4_tunnel   (implicit)
      4_3        CheckStock
110   6_UDDI     (implicit)
      6_1        UDDISoapClient #1
114   6_2        SystinetFindBusiness
118   6_3        JAXRFindBusiness
134   6_4        UDDISoapClient #2
144   7_tomcat   (implicit)
      7_1        SimpleJAXMClient
149   7_2        GenericJAXMSWAClient
210   9_tomcat   (implicit)
      9_1        samples.interop.EchoTestClient #1
212   9_2        samples.interop.EchoTestClient #2
228   9_3        EchoTestClient


The detailed steps that work through all the scripts and the starting 
and stopping of the supporting servers in each chapter are as follows:

Chapter 3:
==========
1. Open a console window at %WORK%\examples\ch3
2. Enter "ch3_tomcat". A new window opens for the Tomcat instance.
3. In the console window, enter "ch3_1".
4. Enter "ch3_2".
5. Enter "ch3_3".
6. Open another console window at %WORK%\examples\ch3 then enter "ch3_tunnel".
7. In the first console window, enter "ch3_4".
8. Enter "ch3_5".
9. Enter "ch3_6".
10.Enter "ch3_7".
11.Close the tunnel GUI window.
12.Close the Tomcat server.

Chapter 4:
==========
1. Change the console window directory to %WORK%\examples\ch4
2. Enter "ch4_tomcat". A new window opens for the Tomcat instance.
3. In the console window, enter "ch4_1".
4. Enter "ch4_2".
5. Open another console window at %WORK%\examples\ch4 then enter "ch4_tunnel".
6. In the first console window, enter "ch4_3".
7. Close the tunnel GUI window.
8. Close the Tomcat server.

Chapter 5:
==========
Samples files are included that are discussed in the chapter.



Chapter 6:
==========
1. Change the console window directory to %WORK%\examples\ch6
2. Enter "ch6_UDDI". A new window opens for the Tomcat instance and Cloudscape.
3. In the console window, enter "ch6_1".
4. Enter "ch6_2".
5. Enter "ch6_3".
6. Enter "ch6_4".
7. Stop the WASP UDDI server.
8. Close the Tomcat window.

Chapter 7:
==========
1. Change the console window directory to %WORK%\examples\ch7
2. Enter "ch7_tomcat". A new window opens for the Tomcat instance.
3. In the console window, enter "ch7_1".
4. Enter "ch7_2".
5. Close the Tomcat window.

Chapter 8:
==========
For a JMS implementation, visit the Web site of companies that produce 
evaluation editions of JMS implementations such as SonicMQ at 
http://www.sonicsoftware.com.

For content-based routing and transformations, visit the Web site of
companies the produce evaluation editions of business services software
such SonicXQ, Sonic Software's product that uses the reliable, 
asynchronous messaging of their SonicMQ software to support web services.

See the SonicXQ Developer's Guide on page 102 for an example of 
JMS Soap Replier.

Chapter 9:
==========
1. Change the console window directory to %WORK%\examples\ch9
2. Enter "ch9_tomcat". A new window opens for the Tomcat instance.
3. In the console window, enter "ch9_1".
4. Enter "ch9_2".
5. Enter "ch9_3".
6. Close all windows.

Chapter 10:
===========
No sample files are provided for this chapter.


end

______________________________________________________________________
JavaWebServices_Examples_README.txt gsaintma  March 20, 2002 version 1
______________________________________________________________________