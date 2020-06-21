package com.meechai.backendtest.service;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.Claim;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.meechai.backendtest.configuration.JwtTokenUtil;
import com.meechai.backendtest.entity.UserEntity;
import com.meechai.backendtest.repository.UserRepository;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.provider.authentication.OAuth2AuthenticationDetails;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    JwtTokenUtil jwtTokenUtil;


    @Autowired
    JwtUserDetailsService jwtUserDetailsService;

    public List<UserEntity> getAllUser(){
        return userRepository.findAll();
    }

    public UserEntity approveUserStatus(long id){
        UserEntity newUserEntity = new UserEntity();
        newUserEntity = userRepository.findByUserId(id);
        newUserEntity.setApproveStatus(true);

        return userRepository.save(newUserEntity);
    }

    public UserEntity getMyUser(String token){
        JWTVerifier verifier = JWT.require(Algorithm.HMAC256("123456789")).build();
        DecodedJWT jwt = verifier.verify(token.substring(7));
        Map<String, Claim> claims = jwt.getClaims();
        String username = claims.get("user_name").asString();
        return userRepository.findFirstByUsername(username);

    }


}
