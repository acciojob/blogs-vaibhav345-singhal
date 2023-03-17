package com.driver.services;

import com.driver.models.Blog;
import com.driver.models.Image;
import com.driver.models.User;
import com.driver.repositories.BlogRepository;
import com.driver.repositories.ImageRepository;
import com.driver.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class BlogService {
    @Autowired
    BlogRepository blogRepository1;

    @Autowired
    UserRepository userRepository1;

    @Autowired
    ImageRepository imageRepository;

    public Blog createAndReturnBlog(Integer userId, String title, String content) {
        //create a blog at the current time
        User user;
        Blog blog = new Blog();
        try {
            user = userRepository1.findById(userId).get();
        } catch (Exception e) {
            return blog;
        }
// set blog fields

        blog.setContent(content);
        blog.setTitle(title);
        blog.setUser(user);

        // updated user blog list
        user.getBlogList().add(blog);

        //save parnet user
        userRepository1.save(user);

        return blog;
    }

    public void deleteBlog(int blogId) {
        //delete blog and corresponding images

        Blog blog;
        User user;
        try {
            blog = blogRepository1.findById(blogId).get();
            user = userRepository1.findById(blog.getUser().getId()).get();
        } catch (Exception e) {
            return;
        }

        blog.getImageList().clear();

        user.getBlogList().remove(blog);

        blog.setUser(user);

        userRepository1.save(user);
    }
}
