# Bobcat AEM tests
Contains integration tests and examples for Bobcat's AEM modules.

Each folder contains tests for specific AEM version.

# Building and running tests

## Prerequisites
- running instance of AEM in correct version
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
