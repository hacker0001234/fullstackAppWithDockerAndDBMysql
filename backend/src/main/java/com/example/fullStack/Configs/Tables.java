package com.example.fullStack.Configs;

import jakarta.annotation.PostConstruct;
import org.springframework.r2dbc.core.DatabaseClient;
import org.springframework.stereotype.Component;
import reactor.core.Disposable;
import reactor.util.retry.Retry;

import java.time.Duration;

@Component
public class Tables {
    private final DatabaseClient databaseClient;
    public Tables( DatabaseClient databaseClient1){


        this.databaseClient = databaseClient1;
    }

    @PostConstruct
    public Disposable init() {
       return databaseClient.sql(
                        "CREATE TABLE IF NOT EXISTS users (" +
                                "id INT AUTO_INCREMENT PRIMARY KEY, " +
                                "name VARCHAR(100) NOT NULL, " +
                                "email VARCHAR(100) NOT NULL UNIQUE, " +
                                "password VARCHAR(100) NOT NULL" +
                                ");"
                )
               .then()
               .retryWhen(Retry.backoff(10, Duration.ofSeconds(5)))
               .doOnError(err -> System.err.println("Cannot connect to DB: " + err.getMessage()))
               .subscribe();
    }

}
