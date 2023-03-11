package ru.konstantinpetrov.web.lab4.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;
import ru.konstantinpetrov.web.lab4.dtoLayer.ResponseDataDTO;
import ru.konstantinpetrov.web.lab4.dtoLayer.ResponseEnterDTO;
import ru.konstantinpetrov.web.lab4.dtoLayer.ResponseResult;
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


    @CrossOrigin
    @PostMapping(path = "/users")
    public ResponseEntity<ResponseEnterDTO> add(@RequestBody User user){
        try {
            user.setPassword(passwordEncoder.encode(user.getPassword()));
            this.userService.add(user);
            return new ResponseEntity<>(new ResponseEnterDTO(true),
                    HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(new ResponseEnterDTO(false),
                    HttpStatus.BAD_REQUEST);
        }
    }


//    @GetMapping(path = "/{id}")
//    public ResponseEntity<ResponseResult<Object>> get(@PathVariable long id){
//        try {
//            User user = this.userService.getById(id);
//            return new ResponseEntity<>(new ResponseResult<>(user, null),
//                    HttpStatus.OK);
//        } catch (Exception e) {
//            return new ResponseEntity<>(new ResponseResult<>(null, e.getMessage()),
//                    HttpStatus.BAD_REQUEST);
//        }
//    }

//    @DeleteMapping(path = "/{id}")
//    public ResponseEntity<ResponseResult<Object>> delete(@PathVariable long id){
//        try {
//            User user = this.userService.delete(id);
//            return new ResponseEntity<>(new ResponseResult<>(user, null),
//                    HttpStatus.OK);
//        } catch (Exception e) {
//            return new ResponseEntity<>(new ResponseResult<>(null, e.getMessage()),
//                    HttpStatus.BAD_REQUEST);
//        }
//    }

    @GetMapping(path="all-data")
    public ResponseEntity<ResponseDataDTO<Object>> allData(){
        ArrayList<Coordinate> coordinates=new ArrayList<>();
        coordinates.add(new Coordinate(1.0,1.5,2.0, true));
        coordinates.add(new Coordinate(2.0,3.5,4.0, false));
        coordinates.add(new Coordinate(0.0,2.5,0.0, true));

        return  new ResponseEntity<>(new ResponseDataDTO(coordinates),
                HttpStatus.OK);
    }

    @CrossOrigin
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
