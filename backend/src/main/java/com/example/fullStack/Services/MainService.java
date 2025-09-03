package com.example.fullStack.Services;

import com.example.fullStack.DTO.UserDTO;
import org.springframework.r2dbc.core.DatabaseClient;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.List;

@Service
public class MainService {
    private final DatabaseClient databaseClient;

    public MainService(DatabaseClient databaseClient) {
        this.databaseClient = databaseClient;
    }

    public Mono<String> addUser(UserDTO userDTO){
        return databaseClient.sql("""
                            INSERT INTO users (name,email,password)VALUES(:name,:email,:password);
                         """)
                .bind("name", userDTO.getName())
                .bind("email", userDTO.getEmail())
                .bind("password", userDTO.getPassword())
                .fetch()
                .rowsUpdated()
                .then(Mono.just("User added successfully"))
                .onErrorResume((e) -> Mono.just("Error adding user"));

    }

    public Flux<UserDTO> getUsers(){
        return databaseClient.sql("SELECT * FROM users;")
                .map((row, rowMetadata) -> {
                    UserDTO userDTO = new UserDTO();
                    userDTO.setName(row.get("name", String.class));
                    userDTO.setEmail(row.get("email", String.class));
                    userDTO.setPassword(row.get("password", String.class));
                    return userDTO;
                })
                .all();
    }
}
