package com.example.maven.service.DataImpl;

import com.example.maven.model.AreaLabel;
import com.example.maven.service.DataService.AreaLabelDataService;

import java.util.ArrayList;

public class AreaLabelDataImpl implements AreaLabelDataService{
    @Override
    public boolean saveAreaLabel(AreaLabel label) {
        return false;
    }

    @Override
    public ArrayList<AreaLabel> getAllAreaLabel() {
        return null;
    }

    @Override
    public AreaLabel getAreaLabelByImageId(String ImageId) {
        return null;
    }
}
