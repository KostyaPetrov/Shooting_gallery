package ru.konstantinpetrov.web.lab4.service;

import ru.konstantinpetrov.web.lab4.dtoLayer.CoordinateDTO;
import ru.konstantinpetrov.web.lab4.entity.Coordinate;

import java.util.ArrayList;

public interface CoordinateService {
    Boolean addCoordinate(CoordinateDTO coordinate, String login);

    ArrayList<Coordinate> getAllDataByLogin(String login);

    boolean deleteAllByLogin(String login);
}
