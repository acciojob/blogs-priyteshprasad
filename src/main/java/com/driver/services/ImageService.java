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

    public Image addImage(Integer blogId, String description, String dimensions){
        //add an image to the blog
        Image image =new Image();
        image.setBlog(blogRepository2.findById(blogId).get());
        image.setDescription(description);
        image.setDimensions(dimensions);
        imageRepository2.save(image);
        return image;

    }

    public void deleteImage(Integer id){
        imageRepository2.deleteById(id);
    }

    public int countImagesInScreen(Integer id, String screenDimensions) {
        //Find the number of images of given dimensions that can fit in a screen having `screenDimensions`
        Image image = imageRepository2.findById(id).get();
        String imageDimensions = image.getDimensions();
        int indexOfX = imageDimensions.indexOf("X");
        int a = Integer.parseInt(imageDimensions.substring(0,indexOfX));
        int b = Integer.parseInt(imageDimensions.substring(indexOfX+1));

        int indexOfXScreen = screenDimensions.indexOf("X");
        int x = Integer.parseInt(screenDimensions.substring(0,indexOfXScreen));
        int y = Integer.parseInt(screenDimensions.substring(indexOfXScreen+1));

        int heightRatio = x/a;
        int widthRatio = y/b;

        int count = heightRatio*widthRatio;

        return count;
    }
}
