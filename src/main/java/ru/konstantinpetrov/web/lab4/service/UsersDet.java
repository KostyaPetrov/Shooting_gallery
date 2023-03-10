package ru.konstantinpetrov.web.lab4.service;

import lombok.AllArgsConstructor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import ru.konstantinpetrov.web.lab4.entitie.Users;
import ru.konstantinpetrov.web.lab4.repository.UserRepository;


@Service
@AllArgsConstructor
public class UsersDet implements UserDetailsService {

    @Autowired
    UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String login) throws UsernameNotFoundException {
//        Users users = service.getUserByLogin(login);
        Users users = userRepository.findByLogin(login);

        if(users==null){
            System.out.println("I do not now how this user: "+login);
            throw new UsernameNotFoundException("Unknown user: "+login);
        }

        return User.builder()
                .username(users.getLogin())
                .password(users.getPassword())
                .roles("USER")
                .build();
    }

}