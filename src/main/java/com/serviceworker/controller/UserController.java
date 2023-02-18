package com.serviceworker.controller;

import com.serviceworker.dto.responsedto.JwtResponse;
import com.serviceworker.jwt.JwtTokenUtil;
import com.serviceworker.model.User;
import com.serviceworker.repository.UserRepository;
import com.serviceworker.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;


@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private JwtUserDetailsService userDetailsService;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private JwtTokenUtil jwtTokenUtil;

    @PostMapping("/signup")
    public User signUp(@RequestBody User user){
     User newUser = userService.signUpUser(user);
        return newUser;
    }

    @RequestMapping(value = "/authenticate", method = RequestMethod.POST)
    public ResponseEntity<Map<String,Object>> createAuthenticationToken(@RequestBody User user) throws Exception {
        Map<String,Object> objMp=new HashMap<>();
        authenticate(user.getEmail(), user.getPassword());
        final UserDetails userDetails = userDetailsService.loadUserByUsername(user.getEmail(),user.getRole().getId());
        User users=userRepository.findByEmailAndRoleId(user.getEmail(),user.getRole().getId());
        if(userDetails==null) {
            objMp.put("error"," your credential not matched");
            return new ResponseEntity<>(objMp, HttpStatus.BAD_REQUEST);
        }
        else {
            final String token = jwtTokenUtil.generateToken(userDetails);
            objMp.put("token",new JwtResponse(token));
            objMp.put("data", users);
            return new ResponseEntity<>(objMp, HttpStatus.CREATED);
        }
    }


    private void authenticate(String email, String password) throws Exception {
        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(email, password));
        } catch (DisabledException e) {
            throw new Exception("USER_DISABLED", e);
        } catch (BadCredentialsException e) {
            throw new Exception("INVALID_CREDENTIALS", e);
        }
    }

}
