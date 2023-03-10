package ru.konstantinpetrov.web.lab4.service;

import org.modelmapper.ModelMapper;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import ru.konstantinpetrov.web.lab4.dtoLayer.UserDTO;
import ru.konstantinpetrov.web.lab4.entitie.Users;
import ru.konstantinpetrov.web.lab4.exception.OwnerNotFoundException;
import ru.konstantinpetrov.web.lab4.repository.UserRepository;

import javax.persistence.EntityNotFoundException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Objects;

@Service
public class UsersService {
    private final UserRepository userRepository;
    private ModelMapper modelMapper;
    private List<Users> userList =new ArrayList<>();


    public UsersService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }


    public List<Users> getAllUser(){
        return userRepository.findAll();
    }

    public Users getUserFromDTO(UserDTO userDTO){
        return new Users(userDTO.getLogin(), userDTO.getPassword());
    }

    public void addUser(Users newUser) {
        userRepository.save(newUser);
    }

    public boolean checkUserInDb(UserDTO userDTO){
        return userRepository.findById(userDTO.getLogin()).isPresent();
    }

    public boolean checkEqualsPassword(UserDTO userDTO){
        return getUserByLogin(userDTO.getLogin()).getPassword().equals(userDTO.getPassword());
    }

    private UserDTO convertToUserDto(Users user) {
        return modelMapper.map(user, UserDTO.class);
    }
    public Users getUserByLogin(String login) {

        return userRepository.findByLogin(login);

    }

//    @Override
//    public UserDetails loadUserByUsername(String login) throws UsernameNotFoundException {
//        Users user = getUserByLogin(login);
//        if (Objects.isNull(user)) {
//            throw new UsernameNotFoundException(String.format("User %s is not found", login));
//        }
//        return new org.springframework.security.core.userdetails.User(user.getLogin(), user.getPassword(), true, true, true, true, new HashSet<>());
//    }
}
