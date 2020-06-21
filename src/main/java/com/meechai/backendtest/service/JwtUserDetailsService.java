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

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        UserEntity user = userRepository.findFirstByUsername(username);
        Set<GrantedAuthority> grantedAuthorities = new HashSet<>();
        grantedAuthorities.add(new SimpleGrantedAuthority(user.getRole()));
        if (user != null) {
            return new User(user.getUsername(), user.getPassword(),
                    grantedAuthorities);
        } else {
            throw new UsernameNotFoundException("UserEntity not found with username: " + username);
        }
    }
    public UserEntity getUserByUsername(String username){
        return userRepository.findFirstByUsername(username);
    }


}