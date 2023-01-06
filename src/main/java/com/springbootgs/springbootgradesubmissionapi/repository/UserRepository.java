package com.springbootgs.springbootgradesubmissionapi.repository;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

import com.springbootgs.springbootgradesubmissionapi.entity.User;

public interface UserRepository extends CrudRepository<User, Long> {
    Optional<User> findByUsername(String username);
}
