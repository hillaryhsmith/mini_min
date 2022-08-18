# MiniMin 

This mobile-first web app was created as a capstone projet for Ada Developers Academy, cohort 17. 

MiniMin is a mineralogy teaching tool geared towards hobbyists and students at the secondary and tertiary levels. 

### Features

This is the back-end for MiniMin. It is written in Java and uses the Spring Boot framework. It connects to a PostgreSQL database and exposes an API allowing the front-end to register new learners, login, and access mineral data.

There are three entities: learner, mineral and photo. Each has an associated controller, repository, and service. 

### Dependencies

The dependencies include Java Spring Boot and PostgreSQL. 

A full list of dependences can be found in pom.xml. 

### Setting Up

1. Clone this git repository 

To work locally: 

1. Uncomment lines 1 - 3 in application.properties, related to spring.datasource
2. Create a local postgres database called "mini-min-test" or change the name of the database you are connecting to in line 1 of application.properties 
