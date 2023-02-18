package com.serviceworker.serviceImpl;

import com.serviceworker.dto.LogInResponse;
import com.serviceworker.model.User;
import com.serviceworker.repository.UserRepository;
import com.serviceworker.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private UserRepository userRepository;

    private void validateUser(User user){
        if (user.getEmail() == null || user.getEmail().equals("")) {
            throw new RuntimeException("user can not be null");
        }
        if (user.getPassword() == null || user.getPassword().equals("")) {
            throw new RuntimeException("password can not be null");
        }
    }

    @Override
    public LogInResponse signUpUser(LogInResponse response) {
        User user = toEntity(response);
        User byEmail = userRepository.findByEmail(response.getEmail());
        if (byEmail != null) {
            throw new RuntimeException ("this email already exist");
        }
        user.setEmail(response.getEmail());
        user.setPassword(passwordEncoder.encode(response.getPassword()));
        user.setRoleName(response.getRoleName());
        User saveUser = userRepository.save(user);
        LogInResponse responseSave = toDto(user);
        return responseSave;

    }


    public LogInResponse toDto(User user){
        LogInResponse response = new LogInResponse();
        response.setEmail(user.getEmail());
        response.setPassword(user.getPassword());
        response.setRoleName(user.getRoleName());
        return  response;
    }

    public User toEntity(LogInResponse response){
        User user=new User();
        user.setEmail(response.getEmail());
        user.setPassword(response.getPassword());
        user.setRoleName(response.getRoleName());
        return user;
    }

}
