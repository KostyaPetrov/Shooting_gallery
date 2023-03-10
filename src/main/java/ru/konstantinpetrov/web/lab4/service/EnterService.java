package ru.konstantinpetrov.web.lab4.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import ru.konstantinpetrov.web.lab4.dtoLayer.UserDTO;
import ru.konstantinpetrov.web.lab4.entitie.Users;

@Service
public class EnterService {
    private final BCryptPasswordEncoder passwordEncoder;
    private AuthenticationManager authenticationManager;
    private final UsersService usersService;

    @Autowired
    public EnterService(BCryptPasswordEncoder passwordEncoder, UsersService usersService) {
        this.passwordEncoder = passwordEncoder;
        this.usersService = usersService;
    }


    public boolean registrate(UserDTO userDTO){
        userDTO.setPassword(passwordEncoder.encode(userDTO.getPassword()));
        if(usersService.checkUserInDb(userDTO)){
            return false;
        }
        Users newUser = usersService.getUserFromDTO(userDTO);

        usersService.addUser(newUser);
        Authentication authenticate= authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(userDTO.getLogin(), userDTO.getPassword()));
        SecurityContextHolder.clearContext();
        SecurityContextHolder.getContext().setAuthentication(authenticate);


        return true;
    }


    public boolean authentic(UserDTO userDTO){
        userDTO.setPassword(passwordEncoder.encode(userDTO.getPassword()));

        return usersService.checkUserInDb(userDTO) && usersService.checkEqualsPassword(userDTO);
    }

}
