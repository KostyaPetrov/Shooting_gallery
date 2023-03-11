package ru.konstantinpetrov.web.lab4.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.konstantinpetrov.web.lab4.entity.Coordinate;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Repository
public interface AttemptRepository extends JpaRepository<Coordinate, Long> {
    ArrayList<Coordinate> findAllByLogin(String login);
}
