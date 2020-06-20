package com.meechai.backendtest.service;

import com.meechai.backendtest.entity.UserEntity;
import com.meechai.backendtest.model.RegisterRequest;
import com.meechai.backendtest.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
public class JwtUserDetailsService implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder bcryptEncoder;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        Optional<UserEntity> user = userRepository.findUserByUsername(username);
        Set<GrantedAuthority> grantedAuthorities = new HashSet<>();
        grantedAuthorities.add(new SimpleGrantedAuthority(user.get().getRole()));
        if (user.isPresent()) {
            return new User(user.get().getUsername(), user.get().getPassword(),
                    grantedAuthorities);
        } else {
            throw new UsernameNotFoundException("UserEntity not found with username: " + username);
        }
    }

    public UserEntity save(RegisterRequest user){
        UserEntity newUserEntity = new UserEntity(user);
        //newUserEntity.setUsername(user.getUsername());
        newUserEntity.setPassword(bcryptEncoder.encode(user.getPassword()));
        return userRepository.save(newUserEntity);
    }
    public List<UserEntity> getAllUser(){
        return userRepository.findAll();
    }

    public UserEntity approveUserStatus(long id){
        UserEntity newUserEntity = new UserEntity();
        newUserEntity = userRepository.findByUserId(id);
        newUserEntity.setApproveStatus(true);

        return userRepository.save(newUserEntity);
    }

    public UserEntity getUserByUsername(String username){
        return userRepository.findFirstByUsername(username);
    }


}