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
        Blog blog = blogRepository2.findById(blogId).get();

        Image image = new Image();
        image.setDimensions(dimensions);
        image.setDescription(description);

        blog.getImageList().add(image);

        image.setBlog(blog);

        blogRepository2.save(blog);

        return image;
    }

    public void deleteImage(Integer id) {
        imageRepository2.deleteById(id);
    }

    public int countImagesInScreen(Integer id, String screenDimensions) {
        //Find the number of images of given dimensions that can fit in a screen having `screenDimensions`

        Image image = imageRepository2.findById(id).get();

        String dimension = image.getDimensions();

        int height = Integer.parseInt(dimension.split("X")[0]);
        int width = Integer.parseInt(dimension.split(("X"))[1]);

        int screenHeight = Integer.parseInt(screenDimensions.split("X")[0]);
        int screenWidth = Integer.parseInt(screenDimensions.split("X")[1]);

        if (height > screenHeight || width > screenWidth) return 0;

        int imageArea = height * width;
        int screenArea = screenHeight * screenWidth;

        int ans = screenArea / imageArea;
        return ans;
    }
}
