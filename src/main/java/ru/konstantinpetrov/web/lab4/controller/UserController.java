package ru.konstantinpetrov.web.lab4.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;
import ru.konstantinpetrov.web.lab4.dtoLayer.ResponseResult;
import ru.konstantinpetrov.web.lab4.entity.User;
import ru.konstantinpetrov.web.lab4.entity.UserDetailsImpl;
import ru.konstantinpetrov.web.lab4.service.UserService;

@RestController
@RequestMapping("/user")
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

    @PostMapping
    public ResponseEntity<ResponseResult<Object>> add(@RequestBody User user){
        try {
            user.setPassword(passwordEncoder.encode(user.getPassword()));
            this.userService.add(user);
            return new ResponseEntity<>(new ResponseResult<>(user, null),
                    HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(new ResponseResult<>(null, e.getMessage()),
                    HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping(path = "/{id}")
    public ResponseEntity<ResponseResult<Object>> get(@PathVariable long id){
        try {
            User user = this.userService.get(id);
            return new ResponseEntity<>(new ResponseResult<>(user, null),
                    HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(new ResponseResult<>(null, e.getMessage()),
                    HttpStatus.BAD_REQUEST);
        }
    }

    @DeleteMapping(path = "/{id}")
    public ResponseEntity<ResponseResult<Object>> delete(@PathVariable long id){
        try {
            User user = this.userService.delete(id);
            return new ResponseEntity<>(new ResponseResult<>(user, null),
                    HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(new ResponseResult<>(null, e.getMessage()),
                    HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping(path = "/auth")
    public ResponseEntity<ResponseResult<Object>> auth(Authentication authentication){
        if(authentication != null && authentication.isAuthenticated()){
            long id = ((UserDetailsImpl)authentication.getPrincipal()).getId();
            return new ResponseEntity<>(new ResponseResult<>(id, null),
                    HttpStatus.OK);
        }
        else {
            return new ResponseEntity<>(new ResponseResult<>(null,
                    "User does not exists"), HttpStatus.OK);
        }
    }

}
