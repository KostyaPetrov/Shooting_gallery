package ru.konstantinpetrov.web.lab4.service;

import ru.konstantinpetrov.web.lab4.dtoLayer.CoordinateDTO;

public interface ValidatorService {
    boolean validateInput(CoordinateDTO coordinateDTO);
    Boolean validateHit();
}
