package ru.konstantinpetrov.web.lab4.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.konstantinpetrov.web.lab4.entitie.Users;

public interface UserRepository extends JpaRepository<Users, String> {
}
