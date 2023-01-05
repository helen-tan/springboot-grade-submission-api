package com.springbootgs.springbootgradesubmissionapi.service;

import com.springbootgs.springbootgradesubmissionapi.entity.User;

public interface UserService {
    User getUser(Long id);
    User saveUser(User user);
}
