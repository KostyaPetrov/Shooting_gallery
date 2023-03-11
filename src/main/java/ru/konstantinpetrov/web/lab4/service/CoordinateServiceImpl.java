package ru.konstantinpetrov.web.lab4.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.konstantinpetrov.web.lab4.dtoLayer.CoordinateDTO;
import ru.konstantinpetrov.web.lab4.entity.Coordinate;
import ru.konstantinpetrov.web.lab4.repository.AttemptRepository;

import java.util.ArrayList;
import java.util.InputMismatchException;


@Service
public class CoordinateServiceImpl implements CoordinateService{

    AttemptRepository attemptRepository;
    ValidatorService validatorService;

    @Autowired
    public void setAttemptRepository(AttemptRepository attemptRepository){this.attemptRepository=attemptRepository;}

    @Autowired
    public void setValidatorService(ValidatorService validatorService) {
        this.validatorService = validatorService;
    }

    @Override
    public Boolean addCoordinate(CoordinateDTO coordinateDTO, String login) {
        if(validatorService.validateInput(coordinateDTO)){
            try {
                Boolean isHit = validatorService.validateHit();
                Coordinate coordinate = new Coordinate(coordinateDTO.getX(), coordinateDTO.getY(), coordinateDTO.getRadius(),
                        isHit, login);
                attemptRepository.save(coordinate);
                return isHit;
            }catch (Exception e) {
                throw new IllegalArgumentException("Error save coordinate!");
            }
        }else{
            throw new IllegalArgumentException("Incorrect input!");
        }

    }

    @Override
    public ArrayList<Coordinate> getAllDataByLogin(String login) {
        return this.attemptRepository.findAllByLogin(login);
    }

    @Override
    public boolean deleteAllByLogin(String login) {
        try {
            ArrayList<Coordinate> listOfHitsByUser = attemptRepository.findAllByLogin(login);
            attemptRepository.deleteAll(listOfHitsByUser);
            return true;
        }catch(Exception e){
            throw new IllegalArgumentException("Error save coordinate!");
        }
    }
}
