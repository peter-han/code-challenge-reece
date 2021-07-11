# AddressBook Manager

A simple yet powerful address book and contact manager (Reece.com.au coding challenge).<br>

## Table of contents:

* [Challenge statement](./README.md#Challenge-statement)
* [Acceptance Criteria](./README.md#Acceptance-Criteria)
* [Deliverables](./README.md#Deliverables)
* [Setup](./README.md#Setup)
* [Quality Analysis](./README.md#Quality-Analysis)

## Challenge statement

As a Reece Branch Manager I would like an address book application So that I can keep track of my customer contacts

## Acceptance Criteria

- Address book will hold name and phone numbers of contact entries
- Create a REST API which will have endpoints for the following
    - Users should be able to add new contact entries
    - Users should be able to remove existing contact entries
    - Users should be able to print all contacts in an address book
    - Users should be able to maintain multiple address books
    - Users should be able to print a unique set of all contacts across multiple address books

## Assumptions

- the concurrent calls is not covered. The "name" on either user nor Address Book are not unique, means when concurrent
  call happens with the same name, could create duplicated records. (to avoid such duplication, need to implement
  database level of lock, but might reduece performance)

## Not Implemented

- Callable API
- Managing ThreadPool
- Performance test
- Blackbox test, API swagger validation (required CI pipeline to be setup)

## Deliverables

- API spec would be nice to have
- all acceptance criteria to be demonstrable through tests
- data can be persisted in a storage
- containerise the application using docker

## Setup

1. Make sure you have Java 8 and Docker installed in your machine.
2. Clone git respository
3. Build app, run tests and quality check from command line:

   ```./gradlew clean build```

4. build docker

   ```./gradlew bootBuildImage```

5. Startup testing env (postgres)

   ```./gradlew composeUp```

6. Run App

   ```docker run --rm -it -p 8081:8081 --network=host address-book:latest```

7. Health API

   ``http://localhost:8081/addressbook/actuator``

8. API doc

   ``http://localhost:8081/addressbook/swagger-ui.html``

![img.png](swagger-ui.png)

## Quality Analysis

|Name|Report|
|:----:|:---:|
|Test Summary|```../build/reports/tests/test/index.html```|
|Test Coverage|```../build/reports/tests/coverage/index.html```|
|Checkstyle|```../build/reports/checkstyle/main.html```|
|FindBugs|```../build/reports/findbugs/main.html```|
|PMD|```../build/reports/pmd/main.html```|
