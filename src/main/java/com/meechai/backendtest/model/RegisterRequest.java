package com.meechai.backendtest.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RegisterRequest {
    private String firstName;
    private String lastName;
    private String email;
    private String mobileNo;
    private String address;
    private String username;
    private String password;
    private Integer salary;
}
