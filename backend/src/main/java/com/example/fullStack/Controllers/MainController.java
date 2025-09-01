package com.example.fullStack.Controllers;

import com.example.fullStack.DTO.UserDTO;
import com.example.fullStack.Services.MainService;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;


@RestController
@RequestMapping("/api")
public class MainController {
    private final MainService mainService;

    public MainController(MainService mainService) {
        this.mainService = mainService;
    }

    @GetMapping("/backend")
    public Mono<String> TestBackend(){
        return Mono.just("correct work backend");
    }

    @PostMapping("/add")
    public Mono<String> AddUser(@RequestBody UserDTO userDTO){
        return mainService.addUser(userDTO);
    }

    @GetMapping("/get")
    public Flux<UserDTO> GetUsers(){
        return mainService.getUsers();
    }

}
