# Discovery Server

Eureka Service Registry for the SOA DevOps Incident Management System.

## Run

From this directory:

```bash
mvn clean spring-boot:run
```

Then open:

http://localhost:8761

The registry is intentionally configured as a standalone Eureka server for local development. Later, the remaining microservices will register themselves here.
