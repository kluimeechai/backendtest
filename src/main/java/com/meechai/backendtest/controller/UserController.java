package com.meechai.backendtest.controller;

import com.meechai.backendtest.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping("/users")
    private ResponseEntity<?> getAllUsers(){

        return ResponseEntity.ok(userService.getAllUser());
    }

    @GetMapping("/users/me")
    private ResponseEntity<?> getMyUser(@RequestHeader(name="Authorization") String token){

        return ResponseEntity.ok(userService.getMyUser(token));
    }
}
