//package com.example.maven.businessLogic.markLabel.markFrameLabelBL;
//
//import com.example.maven.model.po.frameLabel.FrameLabel;
//import com.example.maven.data.frameLabelData.FrameLabelDataImpl;
//import com.example.maven.data.frameLabelData.FrameLabelDataService;
//import com.google.gson.Gson;
//import com.google.gson.GsonBuilder;
//
//public class MarkFrameLabelBLImpl implements MarkFrameLabelBLService {
//
//    private FrameLabelDataService frameLabelData;
//
//    public MarkFrameLabelBLImpl(){
//        frameLabelData = new FrameLabelDataImpl();
//    }
//
//    @Override
//    public int getTaskImageNumber(String taskId) {
//        //return 0;
//    }
//
//    @Override
//    public String getFrameLabel(int imageIndex, String taskId, String userId){
//        FrameLabel frameLabel = frameLabelData.getFrameLabelByImageId(imageId);
//
//        Gson gson = new GsonBuilder().create();
//        String objectToJson = gson.toJson(frameLabel);
//
//        return objectToJson;
//    }
//
//    @Override
//    public String saveFrameLabel(String frameLabelVOJson){
//        Gson gson = new GsonBuilder().create();
//        FrameLabel frameLabel = gson.fromJson(frameLabelVOJson, FrameLabel.class);
//
//        if(frameLabelData.saveFrameLabel(frameLabel)){
//            return "Success";
//        }else{
//            return "FailInJava";
//        }
//    }
//}