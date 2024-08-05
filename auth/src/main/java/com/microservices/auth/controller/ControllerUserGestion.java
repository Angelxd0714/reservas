package com.microservices.auth.controller;

import com.microservices.auth.persistence.services.ServiceUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.Mapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user")
public class ControllerUserGestion {
    @Autowired
    ServiceUser userDetailService;
    @GetMapping("/all")
    public String all(){
        return "all";
    }
}
