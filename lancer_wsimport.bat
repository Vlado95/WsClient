set WSDL_URL=http://localhost:8080/WsJdk/calculateur?wsdl
cd /d %~dp0
wsimport -keep -d src/main/java %WSDL_URL%
pause

REM ne pas oublier de lancer le serveur avant de lancer wsimport
REM pour que l'URL ?wsdl soit accessible
REM open with ../ text editor
REM open with ..system editor + refresh eclipse
