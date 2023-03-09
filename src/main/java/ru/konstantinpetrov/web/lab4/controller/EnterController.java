package ru.konstantinpetrov.web.lab4.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import ru.konstantinpetrov.web.lab4.dtoLayer.UserDTO;
import ru.konstantinpetrov.web.lab4.service.EnterService;

@RestController
public class EnterController {
    private EnterService service;

    @Autowired
    public EnterController(EnterService service){
        this.service=service;
    }



    @PostMapping(value = "/user", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public void registration(@RequestBody UserDTO userDTO){

        service.registrate(userDTO);
    }

    @PostMapping(value = "/login", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public void authentication(@RequestBody UserDTO userDTO){
        service.authentic(userDTO);
    }
}
