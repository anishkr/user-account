# user-account
POC for Getting statements from MS Access Database based on search request

##Tech Stack
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
- Change the `spring.second-datasource.jdbc-url` from application.properties.Example `jdbc:ucanaccess:<MS ACESS DB File Path>;openExclusive=false;ignoreCase=true`
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
- Hit the admin url like `http://localhost:8080/api/v1/account/statements?startDate=08.08.2022&endDate=20.08.2022&accountNumber=0012250016002`
- Hit the user url like `http://localhost:8080/api/v1/account/statements`, it will generate last three months (Current date - 3)
 



  ![Sonarqube ](https://github.com/anishkr/user-account/blob/main/sonarqube-images/Sonaqube.png)

![Test Coverage Report ](https://github.com/anishkr/user-account/blob/main/sonarqube-images/TestCovarage.png)


