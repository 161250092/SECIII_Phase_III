package com.example.maven.businessLogic.markLabel.markLabelBL;

import com.example.maven.model.vo.frameLabel.FrameLabelListItemVO;
import com.example.maven.model.vo.frameLabel.FrameLabelVO;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.ArrayList;
import java.util.List;

public class MarkFrameLabelBLStub implements MarkLabelBLService {
    @Override
    public int getTaskImageNumber(String taskId) {
        return 11;
    }

    @Override
    public String getLabel(String taskId, String userId, String type, int imageIndex) {
        System.out.println("taskId:" + taskId);
        System.out.println("userId:" + userId);
        System.out.println("labelType:" + type);
        System.out.println("imageIndex:" + imageIndex);

        List<FrameLabelListItemVO> labelList  = new ArrayList<>();

        FrameLabelVO vo = new FrameLabelVO("image", labelList);

        if(imageIndex == 0){
            vo = new FrameLabelVO("test1.jpg", labelList);
            labelList.add(new FrameLabelListItemVO(0, 0, 5, 10, "tag0-1"));
            labelList.add(new FrameLabelListItemVO(50, 50, 15, 5, "tag0-2"));
            labelList.add(new FrameLabelListItemVO(100, 100, 20, 10, "tag0-3"));
        }else if (imageIndex == 1){
            vo = new FrameLabelVO("test2.jpg", labelList);
            labelList.add(new FrameLabelListItemVO(100, 0, 25, 10, "tag1-1"));
            labelList.add(new FrameLabelListItemVO(300, 50, 15, 6, "tag2-2"));
        }else if (imageIndex == 2){
            vo = new FrameLabelVO("test3.jpg", labelList);
            labelList.add(new FrameLabelListItemVO(100, 0, 250, 100, "tag1-1"));
            labelList.add(new FrameLabelListItemVO(500, 50, 100, 100, "tag2-2"));
        }else{
            vo = new FrameLabelVO("test"+ (imageIndex+1) + ".jpg", labelList);
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
