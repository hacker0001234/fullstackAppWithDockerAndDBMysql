package com.example.fullStack.Configs;

import jakarta.annotation.PostConstruct;
import org.springframework.r2dbc.core.DatabaseClient;
import org.springframework.stereotype.Component;

@Component
public class Tables {
    private final DatabaseClient databaseClient;
    public Tables( DatabaseClient databaseClient1){


        this.databaseClient = databaseClient1;
    }

    @PostConstruct
    public Void init() {
       return databaseClient.sql(
                        "CREATE TABLE IF NOT EXISTS users (" +
                                "id INT AUTO_INCREMENT PRIMARY KEY, " +
                                "name VARCHAR(100) NOT NULL, " +
                                "email VARCHAR(100) NOT NULL UNIQUE, " +
                                "password VARCHAR(100) NOT NULL" +
                                ");"
                )
                .then()
                .block();
    }
}
