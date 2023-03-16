package com.driver.services;

import com.driver.models.*;
import com.driver.repositories.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ImageService {

    @Autowired
    BlogRepository blogRepository2;
    @Autowired
    ImageRepository imageRepository2;

    public Image addImage(Integer blogId, String description, String dimensions) {
        //add an image to the blog

        Blog blog;
        Image image = new Image();
        try {
            blog = blogRepository2.findById(blogId).get();
        } catch (Exception e) {
            return image;
        }

        image.setDescription(description);
        image.setDimensions(dimensions);
        image.setBlog(blog);

        blog.getImageList().add(image);
        blogRepository2.save(blog);

        return image;
    }

    public void deleteImage(Integer id) {

        Blog blog;
        Image image;
        try {
            image = imageRepository2.findById(id).get();
            blog = blogRepository2.findById(image.getBlog().getId()).get();
        } catch (Exception e) {
            return;
        }

        blog.getImageList().remove(id);

        blogRepository2.save(blog);

        imageRepository2.deleteById(id);

    }

    public int countImagesInScreen(Integer id, String screenDimensions) {
        //Find the number of images of given dimensions that can fit in a screen having `screenDimensions`

        Image image;
        try {
            image = imageRepository2.findById(id).get();
        } catch (Exception e) {
            return 0;
        }

        String dimension = image.getDimensions();

        int height = Integer.parseInt(dimension.split("X")[0]);
        int width = Integer.parseInt(dimension.split(("X"))[1]);

        int screenHeight = Integer.parseInt(screenDimensions.split("X")[0]);
        int screenWidth = Integer.parseInt(screenDimensions.split("X")[1]);

        int imageArea = height * width;
        int screenArea = screenHeight * screenWidth;

        return screenArea / imageArea;
    }
}
