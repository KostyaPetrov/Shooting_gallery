package ru.konstantinpetrov.web.lab4.service;

import lombok.RequiredArgsConstructor;

import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import ru.konstantinpetrov.web.lab4.entitie.Users;


@Service
@RequiredArgsConstructor
public class UsersDetails implements UserDetailsService {

    private final UsersService service;

    @Override
    public UserDetails loadUserByUsername(String login) throws UsernameNotFoundException {
        Users users = service.getUserByLogin(login);


        return User
                .withUsername(login)
                .password(users.getPassword())
                .roles("USER")
                .accountExpired(false)
                .accountLocked(false)
                .credentialsExpired(false)
                .disabled(false)
                .build();
    }

}