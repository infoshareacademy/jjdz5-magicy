package com.infoshareacademy.usersengine.users;

import com.infoshareacademy.User;
import com.infoshareacademy.usersengine.repository.UsersRepository;

import javax.inject.Inject;
import java.util.List;
import java.util.Optional;

public class UsersManagerBean implements UsersManager {
    private UsersRepository usersRepository;

    @Override
    public Optional<User> findUserByUsernameAndPassword(String username, String password) {
        return usersRepository.findUserByUsernameAndPassword(username, password);
    }

    @Override
    public List<User> findAllUsers() {
        return usersRepository.findAllUsers();
    }
}
