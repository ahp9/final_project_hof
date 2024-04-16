# Lokaverkefni 2024 - Hugbúnaðarhönnun og forritun
Ástríður Haraldsdóttir Passauer og Sylvía Hanna Tyrfingsdóttir

## Overview
This project implements an old project from Graphical User Interface Programming 2022, focusing on BlackJack, but without the JavaFX dependency. In this version of BlackJack, players engage in a classic game against the dealer.

The project skeleton incorporates the Observer pattern for managing game state changes and features some aspects of the Template Method pattern for defining the structure of the game.

Additionally, this project is structured as a Maven project. This means it follows the standard Maven project structure, facilitating easy dependency management and project build automation.

## Installation

### System requirements
Make sure you have the following installed on your system:
- Java Development Kit (JDK)

### Clone repo
To be able to run the project locally, you will have to clone the repo with the following command:
```sh
git clone https://github.com/ahp9/final_project_hof.git
```

### Maven
If user has Maven in the system, the following command will setup and run the project:
Compiles all implementation classes
```sh
mvn compile
```
Packages the project classes into a JAR file
```sh
mvn package
```
Generates a site report for the project
```sh
mvn site
```
Runs all test cases (i.e. all classes with a name that end with `Test`)
```sh
mvn test
```
Runs the project
```sh
mvn exec:java
```

### Running without Maven
If user does not have Maven, the project can be executed with the following command:

Package classes and create jar file:
```sh
.\createjar.cmd
```
Run jar file
```sh
.\runjar.cmd
```



## Site
You can find the UML Class diagram with the folllowing link
[UML-diagram](./src/site/markdown/UML.md)


## License
You can find the License with the following link 
[LICENCE](LICENSE)