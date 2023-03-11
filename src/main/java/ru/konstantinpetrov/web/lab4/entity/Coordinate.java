package ru.konstantinpetrov.web.lab4.entity;

import lombok.Data;

@Data
public class Coordinate {
    private Double x;
    private Double y;
    private Double radius;
    private Boolean is_hit;

    public Coordinate(Double x, Double y, Double radius, Boolean is_hit) {
        this.x = x;
        this.y = y;
        this.radius = radius;
        this.is_hit = is_hit;
    }
}
