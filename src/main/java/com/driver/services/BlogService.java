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

        Blog blog = new Blog();

        User user = null;

        try {
            user = userRepository1.findById(userId).get();
        } catch (Exception e) {
            return blog;
        }

        blog.setUser(user);
        blog.setTitle(title);
        blog.setContent(content);

        user.getBlogList().add(blog);
        userRepository1.save(user);

        return blog;
    }

    public void deleteBlog(int blogId) {
        //delete blog and corresponding images
        Blog blog = null;
        try {
            blog = blogRepository1.findById(blogId).get();
        } catch (Exception e) {
            return;
        }

        for (Image image : blog.getImageList()) {
            imageRepository.deleteById(image.getId());
            blog.getImageList().remove(image);
        }

        blogRepository1.deleteById(blogId);
    }
}
