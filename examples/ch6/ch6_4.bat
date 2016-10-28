call ch6
java -Djava.protocol.handler.pkgs=com.sun.net.ssl.internal.www.protocol UDDISoapClient -url https://localhost:8443/wasp/uddi/publishing/ -df Ch6_GetAuthToken.xml
