package ru.konstantinpetrov.web.lab4.dtoLayer;

import lombok.Data;

@Data
public class CoordinateDTO {
    private Double x;
    private Double y;
    private Double radius;
    private String login;
}
