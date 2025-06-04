# Loyalty Service

This project implements a simple loyalty points service. It consumes order events from Kafka and updates a customer's loyalty balance in Postgres.

## Building

The project uses Maven. To build and run tests use:

```bash
mvn test
```

To run the service:

```bash
mvn spring-boot:run
```

The service expects Kafka available at `localhost:9092` and Postgres available at `localhost:5432` with database `loyalty`.
