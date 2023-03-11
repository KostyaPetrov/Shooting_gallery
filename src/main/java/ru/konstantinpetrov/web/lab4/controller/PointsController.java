package ru.konstantinpetrov.web.lab4.controller;


import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;
import ru.konstantinpetrov.web.lab4.dtoLayer.CoordinateDTO;

@RestController
public class PointsController {


    @PostMapping(value = "/compile")
    public CoordinateDTO attempt(@RequestBody CoordinateDTO coordinateDTO, Authentication authentication){
        coordinateDTO.setLogin(authentication.getName());
        return coordinateDTO;

        //TODO переделать в объект ответа
    }



    
}
