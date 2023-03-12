package ru.konstantinpetrov.web.lab4.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;
import ru.konstantinpetrov.web.lab4.dtoLayer.CoordinateDTO;
import ru.konstantinpetrov.web.lab4.dtoLayer.ResponseDataDTO;
import ru.konstantinpetrov.web.lab4.dtoLayer.ResponseEnterDTO;
import ru.konstantinpetrov.web.lab4.entity.Coordinate;
import ru.konstantinpetrov.web.lab4.entity.User;
import ru.konstantinpetrov.web.lab4.entity.UserDetailsImpl;
import ru.konstantinpetrov.web.lab4.service.UserService;

import java.util.ArrayList;

@RestController
public class UserController {
    private UserService userService;

    private PasswordEncoder passwordEncoder;

    @Autowired
    public void setUserService(UserService userService) {
        this.userService = userService;
    }

    @Autowired
    public void setPasswordEncoder(PasswordEncoder passwordEncoder) {
        this.passwordEncoder = passwordEncoder;
    }


    @PostMapping(path = "/users")
    public ResponseEntity<ResponseEnterDTO> addUser(@RequestBody User user){
        try {
            user.setPassword(passwordEncoder.encode(user.getPassword()));
            this.userService.addUser(user);
            return new ResponseEntity<>(new ResponseEnterDTO(true),
                    HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(new ResponseEnterDTO(false),
                    HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping(path = "/login")
    public ResponseEntity<ResponseEnterDTO> auth(Authentication authentication){
        if(authentication != null && authentication.isAuthenticated()){
            String login = ((UserDetailsImpl)authentication.getPrincipal()).getUsername();
            System.out.println("Login: "+login);
            return new ResponseEntity<>(new ResponseEnterDTO(true),
                    HttpStatus.OK);
        }
        else {
            return new ResponseEntity<>(new ResponseEnterDTO(false), HttpStatus.OK);
        }
    }
}
