package com.springbootgs.springbootgradesubmissionapi.service;

import java.util.Optional;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.springbootgs.springbootgradesubmissionapi.entity.User;
import com.springbootgs.springbootgradesubmissionapi.exception.EntityNotFoundException;
import com.springbootgs.springbootgradesubmissionapi.repository.UserRepository;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class UserServiceImpl implements UserService {

    private UserRepository userRepository;
	private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Override
    public User getUser(Long id) {
        Optional<User> user = userRepository.findById(id);
        return unwrapUser(user, id);
    }

    @Override
    public User getUser(String username) {
        Optional<User> user = userRepository.findByUsername(username);
        return unwrapUser(user, 404L);
    }

    @Override
    public User saveUser(User user) {
        // hash the pw that user passed in
        String hashedPassword = bCryptPasswordEncoder.encode(user.getPassword());
        // set the user's pw to the hashed pw for saving in the db
        user.setPassword(hashedPassword);
        return userRepository.save(user);
    }

    static User unwrapUser(Optional<User> entity, Long id) {
        if (entity.isPresent()) return entity.get();
        else throw new EntityNotFoundException(id, User.class);
    }
}
