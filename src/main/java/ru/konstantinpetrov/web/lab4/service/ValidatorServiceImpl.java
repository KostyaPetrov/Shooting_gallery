package ru.konstantinpetrov.web.lab4.service;

import org.springframework.stereotype.Service;
import ru.konstantinpetrov.web.lab4.dtoLayer.CoordinateDTO;

@Service
public class ValidatorServiceImpl implements ValidatorService{

    private double x;
    private double y;
    private double r;


    private boolean validateX() { return (x >=-5) && (x <= 3); }

    private boolean validateY() { return (y >= -3) && (y <= 3); }

    private boolean validateR() { return (r >=-5) && (r <= 3); }


    @Override
    public boolean validateInput(CoordinateDTO coordinateDTO) {
        this.x=coordinateDTO.getX();
        this.y=coordinateDTO.getY();
        this.r=coordinateDTO.getRadius();

        return validateX() && validateY() && validateR();
    }


    @Override
    public Boolean validateHit() {
        boolean firstQuarter= (x >= 0 && y >= 0 && y<=-2*x+1);
        boolean secondQuarter= (x>=0 && y<=0 && (x*x)+(y*y)<=(r/2)*(r/2));
        boolean fourthQuarter= (x<=0 && y>=0 && (-1*x)<=r && y<=(r/2));
        return firstQuarter || secondQuarter || fourthQuarter;
    }
}
