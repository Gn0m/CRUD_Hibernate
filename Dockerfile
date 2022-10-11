FROM tomcat:10-jdk16
ADD target/crud-1.0-SNAPSHOT.war /usr/local/tomcat/webapps/crud-1.0-SNAPSHOT.war
EXPOSE 8080
CMD ["catalina.sh","run"]