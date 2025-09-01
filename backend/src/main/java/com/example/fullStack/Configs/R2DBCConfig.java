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

    @Value("${SPRING_R2DBC_URL:r2dbc:mysql://localhost:3307/test}")
    private String url;

    @Value("${SPRING_R2DBC_USERNAME:root}")
    private String username;

    @Value("${SPRING_R2DBC_PASSWORD:root}")
    private String password;

    @Bean
    public ConnectionFactory factory() {
        // Парсим URL формату r2dbc:mysql://host:port/database
        String[] parts = url.replace("r2dbc:mysql://", "").split("/");
        String hostPort = parts[0];
        String database = parts[1];
        String host = hostPort.split(":")[0];
        int port = Integer.parseInt(hostPort.split(":")[1]);

        ConnectionFactoryOptions options = ConnectionFactoryOptions.builder()
                .option(DRIVER, "mysql")
                .option(HOST, host)
                .option(PORT, port)
                .option(USER, username)
                .option(PASSWORD, password)
                .option(DATABASE, database)
                .option(SSL, false)
                .build();

        return ConnectionFactories.get(options);
    }

    @Bean
    public DatabaseClient adminClient(ConnectionFactory connectionFactory){
        return DatabaseClient.create(connectionFactory);
    }
}
