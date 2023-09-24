# My-pastebin
This is my equivalent of the pastebin service. It can register new users, authenticate users, save, and retrieve pastes.

***
## Technology stack
Java 17, Maven 3, Spring (Boot, MVC, Data, Cloud, Security),
Hibernate, Postgresql, Liquibase, REST, Logback, Swagger, Docker Compose, Junit

***

## Running app

The easiest way to spin up a pastebin instance is using Docker compose.

Assuming you're in the my-pastebin directory, just run:
```bash
$ mvn clean install
$ docker-compose up
```

You should then be able to access the swagger at `http://localhost:8765/swagger-ui.html`.
