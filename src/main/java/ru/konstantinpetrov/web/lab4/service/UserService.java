package ru.konstantinpetrov.web.lab4.service;

import ru.konstantinpetrov.web.lab4.entity.User;

public interface UserService {
    void add(User client);

    User getById(long id);

    User delete(long id);

    User getByLogin(String login);


}
