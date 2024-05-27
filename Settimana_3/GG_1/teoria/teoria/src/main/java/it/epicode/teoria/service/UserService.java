package it.epicode.teoria.service;

import it.epicode.teoria.DTO.UserDTO;
import it.epicode.teoria.entity.User;
import it.epicode.teoria.exception.UserNotFoundException;
import it.epicode.teoria.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;

    public String saveUser(UserDTO userDTO) {
        User user = new User();
        user.setName(userDTO.getName());
        user.setSurname(userDTO.getSurname());
        user.setEmail(userDTO.getEmail());
        user.setPassword(userDTO.getPassword());  // non lo avevamo prima
        userRepository.save(user);
        return "User with id: " + user.getId() + " saved";
    }

    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    public Optional<User> getUserById(int id) {
        return userRepository.findById(id);
    }

    public User updateUser(int id, UserDTO userDTO) {
        Optional<User> userOptional = getUserById(id);
        if (userOptional.isPresent()) {
            User user = userOptional.get();
            user.setName(userDTO.getName());
            user.setSurname(userDTO.getSurname());
            user.setEmail(userDTO.getEmail());
            user.setPassword(userDTO.getPassword());
            return userRepository.save(user);
        }else{
            throw new UserNotFoundException("User with id:" + id + " not found");
        }
    }

    public String deleteUser(int id) {
        Optional<User> userOptional = getUserById(id);

        if (userOptional.isPresent()) {
            userRepository.deleteById(id);
            return "User with id:" + id + " correctly deleted";
        }else{
            throw new UserNotFoundException("User with id:" + id + " not found");
        }
    }
    public User getUserByEmail(String email){
        Optional<User> userOptional = userRepository.findByEmail(email);

        if(userOptional.isPresent()){
            return userOptional.get();
        }
        else{
            throw new UserNotFoundException("User with email=" + email + " not found");
        }
    }

}
