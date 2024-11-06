package dev.marvin.service;

import dev.marvin.model.User;

public interface UserService {
    boolean isUserRegistered(String mobile);
    User create(String username, String password);
}
