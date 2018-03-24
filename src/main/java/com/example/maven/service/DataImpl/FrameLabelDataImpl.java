package com.example.maven.service.DataImpl;

import com.example.maven.model.frameLabel.FrameLabel;
import com.example.maven.service.DataService.FrameLabelDataService;

public class FrameLabelDataImpl implements FrameLabelDataService{
    @Override
    public boolean saveFrameLabel(FrameLabel frameLabel) {
        return false;
    }

    @Override
    public FrameLabel getFrameLabelByUserId(String userId) {
        return null;
    }

    @Override
    public FrameLabel getFrameLabelByImageId(String imageId) {
        return null;
    }
}
