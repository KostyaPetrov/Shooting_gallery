package ru.konstantinpetrov.web.lab4.entity;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import lombok.ToString;

import javax.persistence.*;

@Data
@ToString
@RequiredArgsConstructor
@Entity
@Table(name = "coordinates")
public class Coordinate {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private Long id;


    @Column(nullable = false)
    private Double x;
    @Column(nullable = false)
    private Double y;
    @Column(nullable = false)
    private Double radius;
    @Column(nullable = false)
    private Boolean is_hit;

    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    @Column(nullable = false)
    private String login;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }


    public Coordinate(Double x, Double y, Double radius, Boolean is_hit, String login) {
        this.x = x;
        this.y = y;
        this.radius = radius;
        this.is_hit = is_hit;
        this.login = login;
    }
}
