package com.microservices.auth.controller;

import com.microservices.auth.persistence.dto.RequestCreateUser;
import com.microservices.auth.persistence.dto.RequestLogin;
import com.microservices.auth.persistence.dto.Response;
import com.microservices.auth.persistence.services.UserDetailServiceImpl;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
public class ControllerLoginRegister {
    @Autowired
    UserDetailServiceImpl userDetailService;
    @PostMapping("/login")
    public ResponseEntity<Response> login(@RequestBody @Valid RequestLogin requestLogin){
        try {
            return new ResponseEntity<>(userDetailService.loginUser(requestLogin), HttpStatus.OK);
        }catch (Exception e){
            System.out.println(e);
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }
    @PostMapping("/register")
    public ResponseEntity<Response> register(@RequestBody @Valid RequestCreateUser requestCreateUser){
        try {
            return new ResponseEntity<>(userDetailService.createUser(requestCreateUser), HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

}
