# user-account
POC for Getting statements from MS Access Database based on search request

#Tech Stack

- Springboot - 2.7.5
- Java 11 
- H2 DB
- MS Access
- Spring security with JWT
- gradle
- sonarqube
- Junit
- session

H2 DB used for spring security and MS Access for getting account data and performing search request

## Steps to execute
- Clone the project from git
- Build the project from project location `./gradlew build`
- Run the application and application will start with 8080 port
- Two default users will insert in h2db, 
`username : user,
password: use![]r
`
  `username : admin,
  password: admin
  `
- Code quality from sonarqube : `./gradlew sonarqube -D "sonar.projectKey=<PROJECT_NAME>" -D "sonar.host.url=<HOST_URL>" -D "sonar.login=<PROJECT_KEY>" -D "sonar.issuesReport.html.enable=true"
  `
- JWT token will expire every 5 mins

  ![Sonarqube ](../../../Desktop/Sonaqube.png)


