package com.v1.Jwt.services;

import com.v1.Jwt.model.Users;
import com.v1.Jwt.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

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


}
