# Spring boot REST API with MongoDB

This is an example of a REST API using Spring Boot and MongoDB.

## Requirements

- Java 17
- Docker
- Docker Compose
- Maven
- MongoDB
- MongoDB Compass
- Postman

### Start the MongoDB database

```bash
docker-compose up -d
```

### Start the application

```bash
./mvnw spring-boot:run
```

### Stop the application

```bash
./mvnw spring-boot:stop
```

### Build the application

```bash
./mvnw clean package
```

## Swagger

Access Open API documentation at: http://localhost:8090/swagger-ui/index.html

API documentation is also available in JSON format at: http://localhost:8090/v3/api-docs
