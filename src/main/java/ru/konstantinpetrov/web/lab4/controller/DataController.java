package ru.konstantinpetrov.web.lab4.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;
import ru.konstantinpetrov.web.lab4.dtoLayer.CoordinateDTO;
import ru.konstantinpetrov.web.lab4.dtoLayer.ResponseDataDTO;
import ru.konstantinpetrov.web.lab4.dtoLayer.ResponseEnterDTO;
import ru.konstantinpetrov.web.lab4.dtoLayer.ResponseHitDTO;
import ru.konstantinpetrov.web.lab4.service.CoordinateService;

@RestController
public class DataController {

    private CoordinateService coordinateService;

    @Autowired
    public void setCoordinateService(CoordinateService coordinateService) {
        this.coordinateService = coordinateService;
    }

    @PostMapping(value = "/compile")
    public ResponseEntity<ResponseHitDTO> attempt(@RequestBody CoordinateDTO coordinateDTO, Authentication authentication){


        try {
            return new ResponseEntity<>(new ResponseHitDTO(coordinateService.addCoordinate(coordinateDTO, authentication.getName())),
                    HttpStatus.OK);
        }catch (Exception e) {
            return new ResponseEntity<>(null,
                    HttpStatus.BAD_REQUEST);
        }


    }

    @GetMapping(path="/all-data")
    public ResponseEntity<ResponseDataDTO> allData(Authentication authentication){
        return new ResponseEntity<>(new ResponseDataDTO(coordinateService.getAllDataByLogin(authentication.getName())),
                HttpStatus.OK);
    }

    @DeleteMapping(path = "all-data")
    public ResponseEntity<ResponseEnterDTO> deleteAll(Authentication authentication){
        try {
            coordinateService.deleteAllByLogin(authentication.getName());
            return new ResponseEntity<>(new ResponseEnterDTO(true),
                    HttpStatus.OK);

        }catch (Exception e){
            return new ResponseEntity<>(new ResponseEnterDTO(false),
                    HttpStatus.BAD_REQUEST);
        }

    }
}
