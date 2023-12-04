package com.example.project.Services;

import com.example.project.Models.User;

import java.util.List;

public interface UserService {

    public User saveUser(User user, String url);

    public List<User> findAll();

    public void removeSessionMessage();

    public void sendEmail(User user, String path);

    public boolean verifyAccount(String verificationCode);
    public User findByEmail(String email);

    public User getByEmail(String email);

    public void updateUser(User user);

    public void changePassword(User user, String newPass);

    public boolean oldPasswordIsValid(User user, String oldPass);
}