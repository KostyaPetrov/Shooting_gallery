package ru.konstantinpetrov.web.lab4.entitie;


import lombok.*;
import org.hibernate.Hibernate;

import javax.persistence.*;
import java.util.Objects;

@Getter
@Setter
@ToString
@RequiredArgsConstructor
@Entity
@Table(name = "users")
public class Users {

//    @Column(name = "id", nullable = false)
//    @GeneratedValue(strategy = GenerationType.AUTO)
//    private int id;
    @Id
    @Column(name = "login", nullable = false)
    private String login;

    @Column(name = "password", nullable = false)
    private String password;

    public Users(String login, String password) {
        this.login = login;
        this.password = password;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Users users = (Users) o;
        return login.equals(users.login) && password.equals(users.password);
    }

    @Override
    public int hashCode() {
        return Objects.hash(login, password);
    }

    //    @Override
//    public boolean equals(Object o) {
//        if (this == o) return true;
//        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
//        Users user = (Users) o;
//        return Objects.equals(id, user.id);
//    }
//
//    @Override
//    public int hashCode() {
//        return getClass().hashCode();
//    }
}
