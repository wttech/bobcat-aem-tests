# Bobcat AEM tests
This repository contains integration tests and examples for Bobcat's AEM modules.

Each folder contains tests for specific AEM version:

- Aem64 - AEM 6.4.0
- aem64sp2 - AEM 6.4.2 (Service pack 2)

# Building and running tests

## Prerequisites
- a running instance of AEM in correct version
    - instance should be available at localhost:4502 with default user and password
- JDK 1.8
- Mozilla Firefox and Geckodriver added to PATH

## Building the project
Run the following:
```
./gradlew clean assembly
```

## Running tests
Run the following:
```
./gradlew clean test
```
