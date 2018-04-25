package com.example.maven.businessLogic.markLabel.markLabelBL;

import com.example.maven.model.vo.frameLabel.FrameLabelListItem;
import com.example.maven.model.vo.frameLabel.FrameLabelVO;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.ArrayList;
import java.util.List;

public class MarkFrameLabelBLStub implements MarkLabelBLService {
    @Override
    public int getTaskImageNumber(String taskId) {
        return 3;
    }

    @Override
    public String getLabel(String taskId, String userId, String type, int imageIndex) {
        System.out.println("taskId:" + taskId);
        System.out.println("userId:" + userId);
        System.out.println("labelType:" + type);
        System.out.println("imageIndex:" + imageIndex);

        List<FrameLabelListItem> labelList  = new ArrayList<>();

        FrameLabelVO vo = null;

        if(imageIndex == 0){
            vo = new FrameLabelVO("F5F25A4A91A52AB03FB45F901968C557.jpg", labelList);
            labelList.add(new FrameLabelListItem(0, 0, 5, 10, "tag0-1"));
            labelList.add(new FrameLabelListItem(50, 50, 15, 5, "tag0-2"));
            labelList.add(new FrameLabelListItem(100, 100, 20, 10, "tag0-3"));
        }else if (imageIndex == 1){
            vo = new FrameLabelVO("14829933001340406.png", labelList);
            labelList.add(new FrameLabelListItem(100, 0, 250, 100, "tag1-1"));
            labelList.add(new FrameLabelListItem(400, 50, 150, 60, "tag2-2"));
        }

        Gson gson = new GsonBuilder().create();
        String objectToJson = gson.toJson(vo);

        return objectToJson;
    }

    @Override
    public boolean saveLabel(String taskId, String userId, String type, int imageIndex, String labelVOJson) {
        System.out.println("taskId:" + taskId);
        System.out.println("userId:" + userId);
        System.out.println("labelType:" + type);
        System.out.println("imageIndex:" + imageIndex);
        System.out.println("frameLabelJson:" + labelVOJson);
        return false;
    }

    @Override
    public boolean setTaskAccomplished(String taskId, String userId) {
        System.out.println("taskId:" + taskId);
        System.out.println("userId:" + userId);
        return true;
    }
}
