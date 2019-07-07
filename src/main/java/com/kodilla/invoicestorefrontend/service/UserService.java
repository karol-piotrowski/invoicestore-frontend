package com.kodilla.invoicestorefrontend.service;

import com.kodilla.invoicestorefrontend.client.UserClient;
import com.kodilla.invoicestorefrontend.domain.User;

import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

public class UserService {

    private Set<User> users;
    private static UserService userService;
    private UserClient userClient = UserClient.getInstance();

    private UserService() {
        this.users = new HashSet<>();
    }

    public static UserService getInstance() {
        if (userService == null) {
            userService = new UserService();
        }
        return userService;
    }

    public Set<User> getUsers() {
        users = userClient.getUsers();
        return users;
    }

    public void addUser(User user) {

    }

    public void saveUser(User user) {

    }

    public void deleteUser(User user) {

    }

    public Set<User> findByLoginOrFirstnameOrLastName(String str) {
        return users.stream().filter(user -> user.getLogin().toLowerCase().contains(str.toLowerCase()) ||
                user.getFirstname().toLowerCase().contains(str.toLowerCase()) ||
                user.getLastname().toLowerCase().contains(str.toLowerCase())).collect(Collectors.toSet());
    }

    public User getUser(Long userId) {
        return userClient.getUser(userId);
    }
}
