# Microservices-Course-ESI-SAB

## - Prerequisites

Before running this project, make sure you have the following installed:

- **Java JDK 17 or higher**
  - Check version: `java -version`
  - Download: [https://adoptium.net/](https://adoptium.net/)

- **Maven 3.6+**
  - Check version: `mvn -version`
  - Download: [https://maven.apache.org/download.cgi](https://maven.apache.org/download.cgi)

- **VS Code** (recommended) or any Java IDE 

  - Download: [https://code.visualstudio.com/](https://code.visualstudio.com/)

---

## - Project Structure

```
    Tp1/
    ├── src/
    │   ├── main/
    │   │   ├── java/
    │   │   │   └── com/example/Tp1/
    │   │   │       ├── Controllers/
    │   │   │       │   └── ClientController.java
    │   │   │       ├── Entities/
    │   │   │       │   ├── Account.java
    │   │   │       │   ├── Address.java
    │   │   │       │   ├── Client.java
    │   │   │       │   └── Gender.java
    │   │   │       ├── Repositories/
    │   │   │       │   ├── AccountRepository.java
    │   │   │       │   └── ClientRepository.java
    │   │   │       └── Tp1Application.java
    │   │   └── resources/
    │   │       └── application.properties
    │   └── test/
    ├── api.http
    ├── pom.xml
    └── README.md

```


### - Build the Project

```bash
mvn clean install
```

This will:

    - Download all dependencies
    - Compile the code
    - Run tests
    - Package the application

---

## - Running the Application

### Option : Using Maven

```bash

    mvn spring-boot:run

```

### Option : Using Java JAR

```bash
# First build the JAR
mvn clean package

# Then run it
java -jar target/Tp1-0.0.1-SNAPSHOT.jar
```

### Option : Using VS Code

    1. Open the project in VS Code
    2. Press `F5` or click "Run" → "Start Debugging"
    3. Or use the Spring Boot Dashboard (if Extension Pack for Java is installed)

