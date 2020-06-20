package com.meechai.backendtest.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.meechai.backendtest.model.RegisterRequest;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class UserEntity {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long userId;
    private String firstName;
    private String lastName;
    private String email;
    private String mobileNo;
    private String address;
    @Column(unique=true)
    private String username;
    @JsonIgnore
    private String password;
    private String role;
    private String refCode;
    private Boolean approveStatus;

    public UserEntity(RegisterRequest registerRequest) {
        this.firstName = registerRequest.getFirstName();
        this.lastName = registerRequest.getLastName();
        this.email = registerRequest.getEmail();
        this.mobileNo = registerRequest.getMobileNo();
        this.address = registerRequest.getAddress();
        this.username = registerRequest.getUsername();
        this.password = registerRequest.getPassword();
        this.role = "USER";
        this.approveStatus = false;
        Random random = new Random();
        this.refCode = new SimpleDateFormat("yyyyMMdd").format(new Date()) +
                String.format("%04d", random.nextInt(10000));
    }
}
