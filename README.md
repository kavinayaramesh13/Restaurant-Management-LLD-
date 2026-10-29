# Restaurant Management System (LLD)

A simple Low-Level Design (LLD) of a Restaurant Management System developed using Java. The project demonstrates object-oriented programming and modular design for managing menus, orders, tables, and payments.

## Features

- **Menu Management** — View and manage menu items.
- **Order Management** — Create and manage customer orders.
- **Table Management** — View table details and availability.
- **Payment Processing** — Support for cash, card, and UPI payment methods.
- **Layered Structure** — Separates models, repositories, services, and command-line interfaces (CLI).

## Technologies Used

- Java
- Object-Oriented Programming (OOP)
- Git and GitHub

## Project Structure

```text
src/
└── com/
    └── Restaurant/
        ├── Main.java
        ├── CLI/
        ├── Model/
        ├── Payment/
        ├── Repository/
        └── Service/
```

- **Model** — Represents entities such as menu items, orders, payments, and tables.
- **Repository** — Handles storage and retrieval of application data.
- **Service** — Contains application logic and validation.
- **CLI** — Provides the command-line interface for interacting with the system.
- **Payment** — Contains payment methods and payment processing abstractions.

## Prerequisites

- Java Development Kit (JDK) installed
- PowerShell or another terminal

Check your Java installation:

```powershell
java -version
javac -version
```

## Compile and Run

Run these commands from the project root directory in PowerShell.

### 1. Compile the source files

```powershell
javac -d out src\com\Restaurant\Payment\*.java src\com\Restaurant\Model\*.java src\com\Restaurant\Repository\*.java src\com\Restaurant\Service\*.java src\com\Restaurant\CLI\*.java src\com\Restaurant\Main.java
```

### 2. Run the application

```powershell
java -cp out com.Restaurant.Main
```

## Concepts Demonstrated

- Classes and objects
- Encapsulation
- Abstraction
- Inheritance and polymorphism
- Separation of responsibilities across application layers
- Basic repository and service patterns

## Future Improvements

- Connect the application to a database.
- Add automated unit tests.
- Improve input validation and exception handling.
- Build a graphical or web-based user interface.

## Author

**Kavinaya R**

---

This project was created for learning and practising Java-based low-level design.
