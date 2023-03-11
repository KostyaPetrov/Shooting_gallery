package ru.konstantinpetrov.web.lab4.service;

import ru.konstantinpetrov.web.lab4.entity.Coordinate;
import ru.konstantinpetrov.web.lab4.entity.User;

import java.util.ArrayList;
import java.util.List;

public interface UserService {
    void addUser(User client);

    User getById(long id);

    User delete(long id);

    User getByLogin(String login);



}
