package ru.konstantinpetrov.web.lab4.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.konstantinpetrov.web.lab4.entity.User;
import ru.konstantinpetrov.web.lab4.repository.UserRepository;

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

//    @Override
//    public UserDetails loadUserByUsername(String login) throws UsernameNotFoundException {
//        Users user = getUserByLogin(login);
//        if (Objects.isNull(user)) {
//            throw new UsernameNotFoundException(String.format("User %s is not found", login));
//        }
//        return new org.springframework.security.core.userdetails.User(user.getLogin(), user.getPassword(), true, true, true, true, new HashSet<>());
//    }

    private UserRepository userRepository;

    @Autowired
    public void setUserRepository(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public void add(User client) {
        try {
            this.userRepository.save(client);
        } catch (Exception e) {
            throw new IllegalArgumentException("Client with current login is already exists!");
        }
    }

    @Override
    public User get(long id) {
        return this.userRepository.findById(id).orElseThrow(
                () -> new IllegalArgumentException("User does not exists"));
    }

    @Override
    public User delete(long id) {
        User client = this.get(id);
        this.userRepository.deleteById(id);
        return client;
    }
}
