Santa's Workshop

A small application to help Santa streamline gift production and manage deliveries.

Project Overview

A simple back-end application for managing gifts and deliveries using Java, Spring Boot, and PostgreSQL.


Technologies Used

Java 21
Spring Boot
Maven
PostgreSQL


Setup / Installation / Run
Prerequisites

PostgreSQL installed on your computer.
Create a database named santa_workshop in pgAdmin or via your PostgreSQL server.
Update application.properties with your database username and password:

spring.datasource.username=${DB_USERNAME}
spring.datasource.password=${DB_PASSWORD}

If your OS already has environment variables set for the DB credentials, you can skip this step.

The server is set to run on port 8081. Make sure this port is free.


Running the Application

1. Download the JAR file from the release page: app-0.0.1-SNAPSHOT.jar
2. Open a terminal or command prompt in the folder where the JAR is located.
3. Run the application:
java -jar app-0.0.1-SNAPSHOT.jar
4. After Spring Boot starts, open your browser and go to:
   http://localhost:8081

Make sure the port is not used by another application.

Testing

It is recommended to use Postman or similar tools to send HTTP requests to test the API endpoints.
