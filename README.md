# Java Projects Repository

This repository serves as an archive for my Java mini-projects and university laboratory assignments. The projects cover a wide range of topics, from core language features to multi-threading, networking, and object-oriented design.

## Repository Structure

### 💻 Console Applications & Core Logic

- **[lab1-collections](./lab1-collections)**: An introduction to the Java Collections Framework. Demonstrates the use of `HashSet` and `TreeSet` with custom sorting mechanisms.
- **[lab2-threads](./lab2-threads)**: Basic multi-threading examples, including concurrent execution and thread management for prime number calculations.
- **[lab6-multithreading](./lab6-multithreading)**: Advanced multi-threading concepts, featuring the Producer-Consumer pattern and implementation of caching mechanisms.
- **[lab5-junit-mockito](./lab5-junit-mockito)**: A project focused on unit testing. Demonstrates how to test controllers and repositories using JUnit and Mockito for dependency mocking.

### 🌐 Networking & Databases

- **[lab3-sockets](./lab3-sockets)**: Implementation of network communication using Java Sockets, covering both TCP and UDP client-server protocols.
- **[lab4-hibernate-h2](./lab4-hibernate-h2)**: Object-Relational Mapping (ORM) project using Hibernate and JPA with an H2 in-memory database integration.

### 🖼️ Desktop & Parallel Processing

- **[oop-virtual-world](./oop-virtual-world)**: A comprehensive Object-Oriented Programming project. A Virtual World simulation game with a Swing GUI, featuring animal and plant interactions on a grid.
- **[lab6-image-processing](./lab6-image-processing)**: Advanced parallel processing project using `ForkJoinPool` and Java Streams for complex image manipulation tasks.

## Setup and Requirements

- **Java Development Kit (JDK)**: Version 8 or higher (JDK 11+ is recommended for most projects).
- **Build Tool**: Most projects are managed via **Maven** (`pom.xml`).
- **IDE**: IntelliJ IDEA, Eclipse, or VS Code with the Java Extension Pack.

## How to Run

1. **For Maven projects**: 
   - Navigate to the project folder.
   - Run `mvn clean install` to build.
   - Execute the `Main` class from your IDE or terminal.
2. **For standard Java projects (like Virtual World)**:
   - Open the project in your IDE.
   - Run the `Main.java` file to launch the application.
