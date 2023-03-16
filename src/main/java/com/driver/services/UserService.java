package com.driver.services;

import com.driver.models.*;
import com.driver.repositories.BlogRepository;
import com.driver.repositories.ImageRepository;
import com.driver.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserService {
    @Autowired
    UserRepository userRepository3;

    @Autowired
    BlogRepository blogRepository;

    @Autowired
    ImageRepository imageRepository;

    public User createUser(String username, String password) {
        User user = new User(username, password, "test", "test");
        userRepository3.save(user);
        return user;
    }

    public void deleteUser(int userId) {
        User user;
        try {
            user = userRepository3.findById(userId).get();
        } catch (Exception e) {
            return;
        }

        for (Blog blog : user.getBlogList()) {
            blog.getImageList().clear();
        }

        user.getBlogList().clear();

        userRepository3.save(user);

        userRepository3.deleteById(userId);

    }

    public User updateUser(Integer id, String password) {
        User user;
        try {
            user = userRepository3.findById(id).get();
        } catch (Exception e) {
            return null;
        }
        user.setPassword(password);

        for (Blog blog : user.getBlogList()) {
            blog.setUser(user);
        }
        return userRepository3.save(user);
    }
}
