package com.example.maven.controller.markLabel;

import com.example.maven.businessLogic.markLabel.imageBL.ImageBLImpl;
import com.example.maven.businessLogic.markLabel.imageBL.ImageBLService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ImageController implements ImageBLService {

    private ImageBLService imageBL;

    public ImageController(){
        imageBL = new ImageBLImpl();
    }

    @RequestMapping(value = "/imageController/getImageById", method = RequestMethod.GET)
    public String getImageById(String imageId){
        return imageBL.getImageById(imageId);
    }

    @RequestMapping(value = "/imageController/getPreviousImageId", method = RequestMethod.GET)
    public String getPreviousImageId(String currentImageId){
        return imageBL.getPreviousImageId(currentImageId);
    }

    @RequestMapping(value = "/imageController/getNextImageId", method = RequestMethod.GET)
    public String getNextImageId(String currentImageId){
        return imageBL.getNextImageId(currentImageId);
    }

}
