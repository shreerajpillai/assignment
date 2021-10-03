#Prerequisites
1. Java 11 - Please ensure JAVA_HOME environment variable is setup on your machine.
2. Tomcat 8.5+
#Tech stack
1. Spring MVC 5.1x
2. Spring ORM 5.1x
3. Lombok 1.18x
4. H2 1.4x
5. Hibernate 5.4x
# How to run the application on your machine?
1. Clone the repo to your workspace
2. Create a ‘Databases’ folder in %userprofile%
3. Copy assignment.mv.db file from {YOUR WORKSPACE}/db-mgmt to %userprofiles%/Databases
4. Open command prompt and run {YOUR WORKSPACE}/db-mgmt/run.bat – this will start the H2 database server on your machine
      a.	Confirm H2 is running by going to http://192.168.1.8:8888 – you should see a H2 Login screen
5. Confirm Tomcat is running on your machine by going to http://localhost:8080. You should see a welcome screen. If not, then install tomcat server 8.5 from https://tomcat.apache.org/download-80.cgi 
6. Copy & paste {YOUR WORKSPACE}/war/assignment.war file to <YOUR TOMCAT FOLDER>/webapps 
7. Start tomcat server
8. Go to http://localhost:8080/assignment to start the application
###Database Connection String: jdbc:h2:tcp://localhost:10400/~/Databases/assignment