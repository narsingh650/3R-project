package com.serviceworker.controller;

import com.serviceworker.dto.LogInResponse;
import com.serviceworker.dto.requestdto.JwtRequest;
import com.serviceworker.dto.responsedto.JwtResponse;
import com.serviceworker.jwt.JwtTokenUtil;
import com.serviceworker.model.User;
import com.serviceworker.repository.UserRepository;
import com.serviceworker.service.UserService;
import com.serviceworker.serviceImpl.JwtUserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

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
    public LogInResponse signUp(@RequestBody LogInResponse response){
     LogInResponse newUser = userService.signUpUser(response);
        return newUser;
    }

    @PostMapping(value = "/authenticate")
    public ResponseEntity<?> createAuthenticationToken(@RequestBody JwtRequest authenticationRequest) throws Exception {

        authenticate(authenticationRequest.getEmail(), authenticationRequest.getPassword());

        final UserDetails userDetails = userDetailsService.loadUserByUsername(authenticationRequest.getEmail());
      UserDetails details =  userDetailsService.loadUserByUsername(authenticationRequest.getEmail(),authenticationRequest.getRollName());
        final String token = jwtTokenUtil.generateToken(details);
        User byEmail = userRepository.findByEmail(authenticationRequest.getEmail());
           byEmail.setToken(token);
           userRepository.save(byEmail);
        return ResponseEntity.ok(new JwtResponse(token));
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
