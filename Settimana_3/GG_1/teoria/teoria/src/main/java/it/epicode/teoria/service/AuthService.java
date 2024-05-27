package it.epicode.teoria.service;

import it.epicode.teoria.DTO.UserLoginDTO;
import it.epicode.teoria.entity.User;
import it.epicode.teoria.exception.UnauthorizedException;
import it.epicode.teoria.security.JwtTool;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AuthService {
    @Autowired
    private UserService userService;
    @Autowired
    private JwtTool jwtTool;

    public String authenticateUserAndCreateToken(UserLoginDTO userLoginDto){
        User user = userService.getUserByEmail(userLoginDto.getEmail());

        if(user.getPassword().equals(userLoginDto.getPassword())){
            return jwtTool.createToken(user);
        }
        else{
            throw  new UnauthorizedException("Error in authorization, relogin!");
        }
    }
}
