package com.example.maven.service.DataImpl;

import com.example.maven.model.ImageLabel;
import com.example.maven.service.DataService.ImageLabelDataService;

import java.util.ArrayList;

public class ImageLabelDataImpl implements ImageLabelDataService{
    @Override
    public boolean saveImageLabel(ImageLabel label) {
        return false;
    }

    @Override
    public ArrayList<ImageLabel> getAllImageLabel() {
        return null;
    }

    @Override
    public ImageLabel getAreaLabelByImageId(String ImageId) {
        return null;
    }
}
