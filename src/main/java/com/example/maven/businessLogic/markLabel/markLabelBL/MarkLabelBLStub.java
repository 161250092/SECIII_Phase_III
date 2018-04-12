package com.example.maven.businessLogic.markLabel.markLabelBL;

import com.example.maven.model.vo.frameLabel.FrameLabelListItem;
import com.example.maven.model.vo.frameLabel.FrameLabelVO;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.ArrayList;
import java.util.List;

public class MarkLabelBLStub implements MarkLabelBLService {
    @Override
    public int getTaskImageNumber(String taskId) {
        return 3;
    }

    @Override
    public String getLabel(String taskId, String userId, String type, int imageIndex) {
        System.out.println("taskId:" + taskId);
        System.out.println("userId:" + userId);
        System.out.println("type:" + type);
        System.out.println("imageIndex:" + imageIndex);

        List<FrameLabelListItem> labelList  = new ArrayList<>();

        FrameLabelVO vo = new FrameLabelVO("image", labelList);

        if(imageIndex == 0){
            labelList.add(new FrameLabelListItem(0, 0, 5, 10, "tag0-1"));
            labelList.add(new FrameLabelListItem(50, 50, 15, 5, "tag0-2"));
            labelList.add(new FrameLabelListItem(100, 100, 20, 10, "tag0-3"));
        }else if (imageIndex == 1){
            labelList.add(new FrameLabelListItem(100, 0, 25, 10, "tag1-1"));
            labelList.add(new FrameLabelListItem(300, 50, 15, 6, "tag2-2"));
        }

        Gson gson = new GsonBuilder().create();
        String objectToJson = gson.toJson(vo);

        return objectToJson;
    }

    @Override
    public boolean saveLabel(String taskId, String userId, String type, String labelVOJson) {
        System.out.println("taskId:" + taskId);
        System.out.println("userId:" + userId);
        System.out.println("type:" + type);
        System.out.println("frameLabelJson:" + labelVOJson);
        return true;
    }

    @Override
    public boolean setTaskAccomplished(String userId, String taskId) {
        return false;
    }
}
