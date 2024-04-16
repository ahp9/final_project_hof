# Lokaverkefni 2024 - Hugbúnaðarhönnun og forritun
Ástríður Haraldsdóttir Passauer og Sylvía Hanna Tyrfingsdóttir

## Overview
We decided to implement an old project from Graphical User Interface Programming 2022, but without the JavaFX. 
The project is an version of BlackJack, where you as a player are playing Black Jack against the dealer.
The project skeleton implementes *Observer* pattern and some sort of *Template Method* pattern. 

This project is a Maven project, i.e. uses the standard Maven project sturcture. 

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
```sh
.\runjar.cmd
```



## Site
You can find the UML Class diagram with the folllowing link
[UML-diagram](src\site\markdown\UML.md)


## License
Hægt er að finna License á eftirfarandi link: [LICENCE](LICENSE)