package com.example.project.Services;

import com.example.project.Models.User;

public interface UserService {

    public User saveUser(User user, String url);

    public void removeSessionMessage();

    public void sendEmail(User user, String path);

    public boolean verifyAccount(String verificationCode);
    public User findByEmail(String email);

}