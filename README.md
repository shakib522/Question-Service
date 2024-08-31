# Question Service

The `question-service` is a microservice responsible for managing questions within the quiz application. It is designed to operate within a microservices architecture, enabling seamless communication with other services, such as the `quiz-service`.This service responsible for all operation of question and also calculate score for quiz, generate question for specific quiz

## Overview

This service handles CRUD operations for questions, allowing quizzes to fetch and manage their associated questions. It leverages OpenFeign for inter-service communication, registers with the Eureka Server for service discovery, and uses Spring Data JPA to interact with a PostgreSQL database.

## Technologies Used

- **OpenFeign**: Facilitates inter-service communication by providing a declarative REST client.
- **API Gateway**: Acts as a single entry point, routing client requests to the appropriate microservices.
- **Eureka Client**: Enables the service to register with the Eureka Server for service discovery.
- **Eureka Server**: A service registry that helps in locating microservices.
- **PostgreSQL**: A relational database for storing question data.
- **Spring Data JPA**: Simplifies database operations using JPA to work with the data layer.

 ## Postman API Collection
 
[Quiz Craft.postman_collection.json](https://github.com/user-attachments/files/16823586/Quiz.Craft.postman_collection.json)

## Other Service repository
-Service Registry : https://github.com/shakib522/Service-Registry
-API Gateway : https://github.com/shakib522/Api-Getway
-Quiz Service : https://github.com/shakib522/Question-Service


## Prerequisites

- Java 17 or later
- Maven 3.6 or later
- PostgreSQL 13 or later
- Spring Boot 3.x
- Docker (optional, for containerization)

## Getting Started

### Clone the Repository

```bash
git clone <repository-url>
cd question-service
```

### Configuration

1. **Database Configuration**:
   Update the `application.yml` or `application.properties` file with the PostgreSQL connection details.

   ```yaml
   spring:
     datasource:
       url: jdbc:postgresql://localhost:5432/questiondb
       username: your-username
       password: your-password
     jpa:
       hibernate:
         ddl-auto: update
       show-sql: true
       database-platform: org.hibernate.dialect.PostgreSQLDialect
   ```

2. **Eureka Client Configuration**:
   Configure the service to register with the Eureka Server by setting the appropriate Eureka client properties.

   ```yaml
   eureka:
     client:
       service-url:
         defaultZone: http://localhost:8761/eureka/
   ```

### Running the Question Service

To start the `question-service`, run the following command from the root directory:

```bash
mvn spring-boot:run
```

Alternatively, you can package the application as a JAR and run it:

```bash
mvn clean package
java -jar target/question-service-0.0.1-SNAPSHOT.jar
```

### Accessing the API

Once the service is up and running, you can access the API at:

```
http://localhost:<port>/api/questions
```

The default port can be set in the `application.yml` or `application.properties` file.


## Inter-Service Communication

The `question-service` utilizes OpenFeign to communicate with the `quiz-service` and other microservices, ensuring that questions are correctly associated with their respective quizzes. This communication relies on the service discovery facilitated by the Eureka Server.

## Docker Support

### Build Docker Image

```bash
docker build -t question-service .
```

### Run Docker Container

```bash
docker run -d -p <port>:<port> --name question-service question-service
```

Ensure that PostgreSQL and Eureka Server are running and accessible to the Docker container.

## Troubleshooting

- **Service Not Registering with Eureka**: Verify the Eureka Server URL and ensure that the service can reach it. Check the Eureka Client configuration in `application.yml`.
- **Database Connectivity Issues**: Confirm that PostgreSQL is running, and the connection details (URL, username, password) in the configuration file are correct.
- **API Gateway Routing Problems**: Ensure that the API Gateway is correctly configured to route requests to the `question-service` based on the correct URL paths.

## License

This project is licensed under the MIT License - see the [LICENSE](LICENSE) file for details.

## Contact

For any questions or issues, please contact [Your Name] at [Your Email].
```

This README template provides a comprehensive guide for the `question-service`. Adjust the details as needed to match your specific project requirements and environment!
