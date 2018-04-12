package com.example.maven.businessLogic.markLabel.markLabelBL;

public class MarkLabelBLImpl implements MarkLabelBLService{
    @Override
    public int getTaskImageNumber(String taskId) {
        return 0;
    }

    @Override
    public String getLabel(String taskId, String userId, String type, int imageIndex) {
        return null;
    }

    @Override
    public boolean saveLabel(String taskId, String userId, String type, String labelVOJson) {
        return false;
    }

    @Override
    public boolean setTaskAccomplished(String userId, String taskId) {
        return false;
    }
}
