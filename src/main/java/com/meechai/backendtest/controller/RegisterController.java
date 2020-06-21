package com.meechai.backendtest.controller;

import com.meechai.backendtest.model.RegisterRequest;
import com.meechai.backendtest.service.JwtUserDetailsService;
import com.meechai.backendtest.service.RegisterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class RegisterController {

    @Autowired
    private RegisterService registerService;


    @PostMapping("/register")
    private ResponseEntity<?> saveUser(@RequestBody RegisterRequest user) throws Exception{
        return ResponseEntity.status(201).body(registerService.save(user));
    }
}
