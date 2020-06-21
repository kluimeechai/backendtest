package com.meechai.backendtest.repository;

import com.meechai.backendtest.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<UserEntity,Integer> {

    Optional<UserEntity> findUserByUsername(String username);
    UserEntity findByUserId(long id);
    UserEntity findFirstByUsername(String username);
}
