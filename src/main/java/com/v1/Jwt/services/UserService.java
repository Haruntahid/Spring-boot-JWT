package com.v1.Jwt.services;

import com.v1.Jwt.model.Users;
import com.v1.Jwt.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private JWTService jwtService;

    @Autowired
    AuthenticationManager authManager;

    private BCryptPasswordEncoder encoder = new BCryptPasswordEncoder(10);

    // save user
    public boolean saveUser(Users user) {
        boolean isExists = userRepository.findByEmail(user.getEmail()).isPresent();

        if(!isExists) {
            user.setPassword(encoder.encode(user.getPassword()));
            userRepository.save(user);
            return true;
        }else{
            return false;
        }
    }



    // get all users
    public List<Users> getAllUsers() {
        return userRepository.findAll();
    }


    public String verify(Users user) {
        Authentication authentication = authManager.authenticate(new UsernamePasswordAuthenticationToken(user.getUsername(),user.getPassword()));

        if(authentication.isAuthenticated()) {
            return jwtService.generateToken(user.getUsername());
        }

        return "Login Failed!!";
    }
}
