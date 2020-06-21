package com.meechai.backendtest.service;

import com.meechai.backendtest.entity.UserEntity;
import com.meechai.backendtest.exception.InvalidSalaryException;
import com.meechai.backendtest.exception.UsernameAlreadyExistException;
import com.meechai.backendtest.model.RegisterRequest;
import com.meechai.backendtest.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;

import javax.xml.bind.ValidationException;

@Service
public class RegisterService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder bcryptEncoder;

    public UserEntity save(RegisterRequest user) throws Exception{
        UserEntity newUserEntity = new UserEntity(user);
        if(user.getSalary() == null || user.getSalary() < 15000){
            throw new InvalidSalaryException("Sorry, you're not eligible");
        }else if(user.getSalary()>= 15000 && user.getSalary()<30000){
            newUserEntity.setMemberType("Silver");
        }else if(user.getSalary()>= 30000 && user.getSalary()<50000){
            newUserEntity.setMemberType("Gold");
        }else if(user.getSalary()>= 50000 ){
            newUserEntity.setMemberType("Platinum");
        }

        newUserEntity.setPassword(bcryptEncoder.encode(user.getPassword()));
        try {
            return userRepository.save(newUserEntity);
        }catch (DataIntegrityViolationException ex){
            throw new UsernameAlreadyExistException("Username is already exist, please try something else");
        }
    }
}
