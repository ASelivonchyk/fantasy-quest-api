package dev.task.dndquest.controller;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/character/")
public class CharacterController {

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping
    public void createCharacter(@RequestBody Character character){

    }
}
