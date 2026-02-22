# Java Projects Repository

This repository contains a collection of Java mini-projects and laboratory assignments covering various aspects of Java programming, from basic collections to multi-threading, networking, databases, and object-oriented design.

## Projects Overview

| Project                                              | Description                                                                                                                                                        |
| ---------------------------------------------------- | ------------------------------------------------------------------------------------------------------------------------------------------------------------------ |
| [**lab1-collections**](./lab1-collections)           | Introduction to Java Collections Framework. Demonstrates the use of `HashSet` and `TreeSet` with custom sorting mechanisms.                                        |
| [**lab2-threads**](./lab2-threads)                   | Basic multi-threading in Java. Includes examples of concurrent execution and thread management (e.g., prime number calculation).                                   |
| [**lab3-sockets**](./lab3-sockets)                   | Network programming using Java Sockets. Contains implementations of both TCP and UDP client-server communication.                                                  |
| [**lab4-hibernate-h2**](./lab4-hibernate-h2)         | Object-Relational Mapping (ORM) using Hibernate and JPA with an H2 in-memory database.                                                                             |
| [**lab5-junit-mockito**](./lab5-junit-mockito)       | Unit testing in Java using JUnit and Mockito. Demonstrates testing controllers and repositories with mocked dependencies.                                          |
| [**lab6-image-processing**](./lab6-image-processing) | Advanced multi-threading and parallel processing using `ForkJoinPool` and Java Streams for image manipulation.                                                     |
| [**lab6-multithreading**](./lab6-multithreading)     | Further multi-threading concepts, including the Producer-Consumer problem and caching mechanisms.                                                                  |
| [**oop-virtual-world**](./oop-virtual-world)         | A comprehensive Object-Oriented Programming project. A Virtual World simulation game with a Swing GUI, featuring various animals and plants interacting on a grid. |

## Setup and Requirements

- **Java Development Kit (JDK):** Version 8 or higher (depending on the specific project requirements, JDK 11+ is recommended).
- **Build Tool:** Most projects use **Maven** (`pom.xml`). The `oop-virtual-world` project is a standard Java project.
- **IDE:** IntelliJ IDEA, Eclipse, or VS Code with the Java Extension Pack.

## How to Run

For Maven-based projects (`lab1` to `lab6`):

1. Navigate to the project directory.
2. Run `mvn clean install` to build the project.
3. Execute the `Main` class from your IDE or via the command line.

For the `oop-virtual-world` project:

1. Open the project in your preferred IDE.
2. Run the `Main` class located in `src/Main.java` to launch the GUI application.

---

_Note: Unnecessary files (like compiled `.class` files, `target/` directories, and IDE-specific files) have been removed and added to `.gitignore` to keep the repository clean._
