rem @echo off

rem Start Cloudscape and then Systinet WASP UDDI Server.

call ch6
call start %J2EE_HOME%\bin\cloudscape -start

cd %WASP_HOME%\bin
call startup.bat

cd %WORK%\examples\ch6