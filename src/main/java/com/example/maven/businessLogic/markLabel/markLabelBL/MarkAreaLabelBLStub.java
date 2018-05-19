//package com.example.maven.businessLogic.markLabel.markLabelBL;
//
//import com.example.maven.model.vo.AreaLabelVO;
//import com.google.gson.Gson;
//import com.google.gson.GsonBuilder;
//
//import java.util.ArrayList;
//
//public class MarkAreaLabelBLStub implements MarkLabelBLService {
//    @Override
//    public int getTaskImageNumber(String taskId) {
//        return 4;
//    }
//
//    @Override
//    public String getLabel(String taskId, String userId, String labelType, int imageIndex) {
//        System.out.println("taskId:" + taskId);
//        System.out.println("userId:" + userId);
//        System.out.println("labelType:" + labelType);
//        System.out.println("imageIndex:" + imageIndex);
//
//        ArrayList<String> lineList = new ArrayList<>();
//        AreaLabelVO vo = null;
//
//        if(imageIndex == 0){
//            vo = new AreaLabelVO("test10.jpg", "area", lineList);
//            lineList.add("10,20;100,200;50,300;");
//        }else if (imageIndex == 1){
//            vo = new AreaLabelVO("test11.jpg", "area", lineList);
//            lineList.add("100,100;100,300;200,300;50,100;");
//        }
//
//        Gson gson = new GsonBuilder().create();
//        String objectToJson = gson.toJson(vo);
//
//        return objectToJson;
//    }
//
//    @Override
//    public boolean saveLabel(String taskId, String userId, String labelType, int imageIndex, String labelVOJson) {
//        System.out.println("taskId:" + taskId);
//        System.out.println("userId:" + userId);
//        System.out.println("labelType:" + labelType);
//        System.out.println("imageIndex:" + imageIndex);
//        System.out.println("LabelJson:" + labelVOJson);
//        return true;
//    }
//
//    @Override
//    public boolean setTaskAccomplished(String taskId, String userId) {
//        System.out.println("taskId:" + taskId);
//        System.out.println("userId:" + userId);
//        return true;
//    }
//}
