package ru.konstantinpetrov.web.lab4.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import ru.konstantinpetrov.web.lab4.dtoLayer.UserDTO;
import ru.konstantinpetrov.web.lab4.entitie.Users;

@Service
public class EnterService {
    private final BCryptPasswordEncoder passwordEncoder;
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
        return true;
    }


    public boolean authentic(UserDTO userDTO){
        userDTO.setPassword(passwordEncoder.encode(userDTO.getPassword()));

        return usersService.checkUserInDb(userDTO) && usersService.checkEqualsPassword(userDTO);
    }

}
