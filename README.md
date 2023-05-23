# Credit Card Comparison

This project is made out of the following separated modules:
- [Core](/comparison-core/README.md): where the use cases, the Domain model and the Infrastructure code is placed. This module is shared with both the importer and the web modules.
- [Importer](/comparison-importer/README.md): contains the FinanceAds credit card integration and the importer executable.
- [Web](/comparison-web/README.md): contains the web application and a very simple backoffice.

## Running the project

Before running the project, have Docker and Java installed in your system.

Start MySQL
    
    docker-compose up mysqldb

Apply migrations and import credit card products

    ./gradlew comparison-import:run

Run the web application

    ./gradlew comparison-web:bootRun

## Running the tests

    ./gradlew test