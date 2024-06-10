# maps-app-backend

## Pre-requisites
Make sure you have the following installed:
- maven
- Java 17
- docker

## Setup
Create a `.env` file with the following environment variables
```
DB_USERNAME=postgres
DB_PASSWORD=maps
DB_NAME=TESTDB
DB_LOCAL_PORT=5432
DB_DOCKER_PORT=5432

SPRING_LOCAL_PORT=8080
SPRING_DOCKER_PORT=8080
```

Run the following commands
```bin\bash
mvn install
docker-compose up --build
```

## Postman
Import the collection in `docs/maps-app-backend.postman_collection.json`
