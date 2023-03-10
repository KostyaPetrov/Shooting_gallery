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



    @PostMapping(value = "/users")
    public String registration(@RequestBody UserDTO userDTO){

        boolean res=service.registrate(userDTO);
        if(res){
            return "All ok";
        }else{
            return "Fail";
        }
    }

    @PostMapping(value = "/login")
    public void authentication(@RequestBody UserDTO userDTO){
        service.authentic(userDTO);
    }
}
