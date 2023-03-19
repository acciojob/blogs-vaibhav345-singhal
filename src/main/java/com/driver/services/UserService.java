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
        User user = new User();
        user.setPassword(password);
        user.setFirstName("test");
        user.setLastName("test");
        user.setUsername(username);
        userRepository3.save(user);
        return user;
    }

    public void deleteUser(int userId) {
        userRepository3.deleteById(userId);
    }

    public User updateUser(Integer id, String password) {
        User user = userRepository3.findById(id).get();

        user.setPassword(password);

        userRepository3.save(user);

        return user;
    }
}
