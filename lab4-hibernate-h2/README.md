# Lab 4: Hibernate and H2 Databases

This mini-project introduces Object-Relational Mapping (ORM) using Hibernate and the Java Persistence API (JPA) with an H2 in-memory database.

## Features

- **Entity Mapping:** Demonstrates mapping Java classes (`Mage`, `Tower`) to database tables using JPA annotations (`@Entity`, `@Id`, `@ManyToOne`, etc.).
- **Database Operations:** Basic CRUD (Create, Read, Update, Delete) operations using `EntityManager`.
- **H2 Database:** Uses an embedded, in-memory database for easy setup and testing without requiring an external database server.

## How to Run

1. Open the project in your IDE.
2. Ensure the `persistence.xml` file is correctly configured in `src/main/resources/META-INF/`.
3. Run the `Main` class located in `src/main/java/main/Main.java`.
4. The application will connect to the H2 database, create the schema, and perform database operations.

## Technologies

- Java
- Maven
- Hibernate ORM
- JPA (Java Persistence API)
- H2 Database Engine
