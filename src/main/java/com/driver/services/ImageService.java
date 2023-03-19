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

        String[] imageDim = dimension.split("X");

        int height = Integer.parseInt(imageDim[0]);
        int width = Integer.parseInt(imageDim[1]);

        String[] scrDim = screenDimensions.split("X");

        int screenHeight = Integer.parseInt(scrDim[0]);
        int screenWidth = Integer.parseInt(scrDim[1]);

        int imgA = screenHeight / height;
        int imgB = screenWidth / width;

        return imgA * imgB;
    }
}
