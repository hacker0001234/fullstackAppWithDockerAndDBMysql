package com.example.fullStack.Configs;
import io.r2dbc.spi.ConnectionFactories;
import io.r2dbc.spi.ConnectionFactory;
import io.r2dbc.spi.ConnectionFactoryOptions;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.r2dbc.core.DatabaseClient;

import static io.r2dbc.spi.ConnectionFactoryOptions.*;

@Configuration
public class R2DBCConfig {

    @Value("${database.host}")
    private String host;
    @Value("${database.port}")
    private String port;
    @Value("${database.user}")
    private String user;
    @Value("${database.password}")
    private String password;
    @Value("${database.name}")
    private String database;

    @Bean
    public ConnectionFactory connectionFactory() {
        ConnectionFactoryOptions options = ConnectionFactoryOptions.builder()
                .option(DRIVER, "mysql")
                .option(HOST, host)
                .option(PORT, Integer.parseInt(port))
                .option(USER,user)
                .option(PASSWORD,password)
                .option(DATABASE,database)
                .build();

        return ConnectionFactories.get(options);
    }

    @Bean
    public DatabaseClient adminClient(ConnectionFactory connectionFactory){
        return DatabaseClient.create(connectionFactory);
    }
}

