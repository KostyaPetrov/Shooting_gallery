package ru.konstantinpetrov.web.lab4.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.konstantinpetrov.web.lab4.entity.Coordinate;
import ru.konstantinpetrov.web.lab4.entity.User;
import ru.konstantinpetrov.web.lab4.repository.AttemptRepository;
import ru.konstantinpetrov.web.lab4.repository.UserRepository;

import java.util.ArrayList;
import java.util.List;

@Service
public class UsersServiceImpl implements UserService {
    /*private final UserRepository userRepository;
    private ModelMapper modelMapper;
    private List<User> userList =new ArrayList<>();


    public UsersService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }


    public List<User> getAllUser(){
        return userRepository.findAll();
    }

    public User getUserFromDTO(UserDTO userDTO){
        return new User(userDTO.getLogin(), userDTO.getPassword());
    }

    public void addUser(User newUser) {
        userRepository.save(newUser);
    }

    public boolean checkUserInDb(UserDTO userDTO){
        return userRepository.findById(userDTO.getLogin()).isPresent();
    }

    public boolean checkEqualsPassword(UserDTO userDTO){
        return getUserByLogin(userDTO.getLogin()).getPassword().equals(userDTO.getPassword());
    }

    private UserDTO convertToUserDto(User user) {
        return modelMapper.map(user, UserDTO.class);
    }
    public User getUserByLogin(String login) {

        return userRepository.findByLogin(login);

    }*/


    private UserRepository userRepository;

    @Autowired
    public void setUserRepository(UserRepository userRepository) {
        this.userRepository = userRepository;
    }


    @Override
    public void addUser(User user) {
        try {
            this.userRepository.save(user);
        } catch (Exception e) {
            throw new IllegalArgumentException("Client with current login is already exists!");
        }
    }

    @Override
    public User getById(long id) {
        return this.userRepository.findById(id).orElseThrow(
                () -> new IllegalArgumentException("User does not exists"));
    }

    @Override
    public User getByLogin(String login){
        return this.userRepository.findByLogin(login).orElseThrow(
                () -> new IllegalArgumentException("User does not exists"));
    }


    @Override
    public User delete(long id) {
        User client = this.getById(id);
        this.userRepository.deleteById(id);
        return client;
    }


}
