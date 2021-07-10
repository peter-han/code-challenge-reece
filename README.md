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

## Deliverables

- API spec would be nice to have
- all acceptance criteria to be demonstrable through tests
- data can be persisted in a storage
- containerise the application using docker

## Setup

1. Make sure you have Java 8 installed in your machine.
2. Clone git respository
3. Build app, run tests and quality check from command line:

   ```./gradlew clean build```

## Quality Analysis

|Name|Report|
|:----:|:---:|
|Test Summary|```../reece/build/reports/tests/test/index.html```|
|Test Coverage|```../reece/build/reports/tests/coverage/index.html```|
|Checkstyle|```../reece/build/reports/checkstyle/main.html```|
|FindBugs|```../reece/build/reports/findbugs/main.html```|
|PMD|```../reece/build/reports/pmd/main.html```|
