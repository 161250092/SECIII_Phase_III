package com.example.maven.businessLogic.markLabel.markAreaLabelBL;


import com.example.maven.model.vo.AreaLabelVO;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.ArrayList;
import java.util.List;

public class MarkAreaLableBLStub implements MarkAreaLabelBLService{
    @Override
    public int getTaskImageNumber(String taskId) {
        System.out.println("TaskID:" + taskId);
        return 3;
    }

    @Override
    public String getAreaLabel(int imageIndex, String taskId, String userId) {
        System.out.println("imageIndex:" + imageIndex);
        System.out.println("taskId:" + taskId);
        System.out.println("userId:" + userId);

        ArrayList<String> lineList  = new ArrayList<>();

        AreaLabelVO vo = new AreaLabelVO("image","label00", lineList);

        if(imageIndex == 0){
            lineList.add("10,20;100,200;50,300;");
        }else if (imageIndex == 1){
            lineList.add("10,10;10,200;100,200;100,10");
        }

        Gson gson = new GsonBuilder().create();
        String objectToJson = gson.toJson(vo);

        return objectToJson;
    }

    @Override
    public String saveAreaLabel(String areaLabelVOJson) {
        return null;
    }
}
