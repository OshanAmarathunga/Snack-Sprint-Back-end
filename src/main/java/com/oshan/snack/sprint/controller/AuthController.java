package com.oshan.snack.sprint.controller;

import com.oshan.snack.sprint.config.JwtProvider;
import com.oshan.snack.sprint.models.User;
import com.oshan.snack.sprint.repository.CartRepository;
import com.oshan.snack.sprint.repository.UserRepository;
import com.oshan.snack.sprint.response.AuthResponse;
import com.oshan.snack.sprint.service.CustomUserDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
public class AuthController {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private JwtProvider jwtProvider;
    @Autowired
    private CustomUserDetailService customUserDetailService;
    @Autowired
    private CartRepository cartRepository;

    public ResponseEntity<AuthResponse> createUserHandler(@RequestBody User user) throws Exception{
        User isEmailExist=userRepository.findByEmail(user.getEmail());
        if (isEmailExist!=null){
            throw new Exception("Email is already used with another account");
        }
        User createdUser=new User();
        createdUser.setEmail(user.getEmail());
        createdUser.setFullName(user.getFullName());
        createdUser.setRole(user.getRole());
        createdUser.setPassword(passwordEncoder.encode(user.getPassword()));

        User savedUser=userRepository.save(createdUser);
        return null;
    }
}
