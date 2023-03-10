package ru.konstantinpetrov.web.lab4.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.konstantinpetrov.web.lab4.entitie.Users;

@Repository
public interface UserRepository extends JpaRepository<Users, String> {
    Users findByLogin(String login);
}
