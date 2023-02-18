package com.serviceworker.serviceImpl;

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

    @Override
    public User signUpUser(User user) {
        User user1 = userRepository.findByEmail(user.getEmail());
        if (user1 != null) {
            throw new RuntimeException ("this email already exist");
        }
        validateUser(user);
        User userNew = new User();
        userNew.setEmail(user.getEmail());
        userNew.setPassword(passwordEncoder.encode(user.getPassword()));
        User saveUser = userRepository.save(userNew);
        return saveUser;
    }

    private void validateUser(User user){
        if (user.getEmail() == null || user.getEmail().equals("")) {
            throw new RuntimeException("user can not be null");
        }
        if (user.getPassword() == null || user.getPassword().equals("")) {
            throw new RuntimeException("password can not be null");
        }
    }

}
