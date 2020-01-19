
# authentication

## Steps to Setup

**1. Clone the application**


**2. Create Mysql database**
```bash
create database db_atc
```

**3. Change mysql username and password as per your installation**

+ open `src/main/resources/application.properties`

+ change `spring.datasource.username` and `spring.datasource.password` as per your mysql installation

**4. Build and run the app using maven**

```bash
mvn package
java -jar target/controller-0.0.1-SNAPSHOT.jar

```

Alternatively, you can run the app without packaging it using -

```bash
mvn spring-boot:run
```

The app will start running at <http://localhost:8080>.

The swagger documentation at <http://localhost:8080/swagger-ui.html>

## Explore Rest APIs

The app defines following CRUD APIs.

Create Company 
Create Affiliate with company
Create Device with Affiliate
Create User with Affiliate
Create Attendance with User



