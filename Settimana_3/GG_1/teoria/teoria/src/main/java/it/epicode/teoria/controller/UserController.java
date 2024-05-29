package it.epicode.teoria.controller;

import it.epicode.teoria.DTO.UserDTO;
import it.epicode.teoria.entity.User;
import it.epicode.teoria.exception.BadRequestException;
import it.epicode.teoria.exception.UserNotFoundException;
import it.epicode.teoria.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
public class UserController {
    @Autowired
    private UserService userService;



    @GetMapping("/api/users")
    @PreAuthorize("hasAnyAuthority('ADMIN', 'USER')")
    public List<User> getAllUsers() {
         return userService.getAllUsers();
    }

    @GetMapping("/api/users/{id}")
    @PreAuthorize("hasAnyAuthority('ADMIN', 'USER')")
    public User getUserById(@PathVariable int id) {
        Optional<User> userOptional = userService.getUserById(id);
        if (userOptional.isPresent()) {
            return userOptional.get();
        }else {
            throw new UserNotFoundException("User with id:" + id + " not found");
        }
    }

    @PutMapping("/api/users/{id}")
    @PreAuthorize("hasAuthority('ADMIN')")
    public User updateUser(@PathVariable int id, @RequestBody @Validated UserDTO userDTO, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            throw new BadRequestException(bindingResult.getAllErrors().stream()
                    .map(error -> error.getDefaultMessage())
                    .reduce("", (s1, s2) -> s1 + s2));
        }
        return userService.updateUser(id, userDTO);
    }

    @DeleteMapping("/api/users/{id}")
    @PreAuthorize("hasAuthority('ADMIN')")
    public String deleteUser(@PathVariable int id) {
        return userService.deleteUser(id);
    }
}
