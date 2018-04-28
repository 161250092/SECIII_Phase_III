package com.example.maven.businessLogic.markLabel.markLabelBL;

import com.example.maven.data.LabelData.LabelDataImpl;
import com.example.maven.data.LabelData.LabelDataService;
import com.example.maven.data.TaskData.TaskDataImpl;
import com.example.maven.data.TaskData.TaskDataService;
import com.example.maven.data.UserData.UserDataImpl;
import com.example.maven.data.UserData.UserDataService;
import com.example.maven.model.po.AreaLabel;
import com.example.maven.model.po.ImageLabel;
import com.example.maven.model.po.Label;
import com.example.maven.model.po.Task;
import com.example.maven.model.po.frameLabel.FrameLabel;
import com.example.maven.model.po.frameLabel.FrameLabelListItem;
import com.example.maven.model.vo.AreaLabelVO;
import com.example.maven.model.vo.ImageLabelVO;
import com.example.maven.model.vo.frameLabel.FrameLabelListItemVO;
import com.example.maven.model.vo.frameLabel.FrameLabelVO;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.ArrayList;
import java.util.List;

public class MarkLabelBLImpl implements MarkLabelBLService{
    private TaskDataService taskDataService;
    private LabelDataService labelDataService;
    private UserDataService userDataService;

    public MarkLabelBLImpl(){
        taskDataService = new TaskDataImpl();
        labelDataService = new LabelDataImpl();
        userDataService = new UserDataImpl();
    }

    @Override
    public int getTaskImageNumber(String taskId) {
        /**
         * 图片信息获取未完成 ----------------------------------------
         */
        Task task = taskDataService.getTask(taskId);
        int imageNumber = task.getImageFileName().length;
        return imageNumber;
    }

    @Override
    public String getLabel(String taskId, String userId, String labelType, int imageIndex) {
        List<Label> labelList = labelDataService.getLabel(userId, taskId);

        if(labelList == null || labelList.size() == 0 || labelList.size() <= imageIndex)
            return null;
        else{
            Task task = taskDataService.getTask(taskId);
            String[] imageInfo = task.getImageFileName();
            String image = imageInfo[imageIndex];
            Label label = labelList.get(imageIndex);

            Gson gson = new GsonBuilder().create();
            String objectToJson = null;

            switch (labelType) {
                case "ImageLabel":
                    ImageLabel imageLabel = (ImageLabel)label;
                    ImageLabelVO imageLabelVO = new ImageLabelVO(image, imageLabel.getLabelList());
                    objectToJson = gson.toJson(imageLabelVO);
                    break;
                case "AreaLabel":
                    AreaLabel areaLabel = (AreaLabel)label;
                    AreaLabelVO areaLabelVO = new AreaLabelVO(image, areaLabel.getLabel(), areaLabel.getLineList());
                    objectToJson = gson.toJson(areaLabelVO);
                    break;
                case "FrameLabel":
                    FrameLabel frameLabel = (FrameLabel)label;
                    FrameLabelVO frameLabelVO = null;
                    List<FrameLabelListItem> poList = frameLabel.getLabelList();
                    ArrayList<FrameLabelListItemVO> voList = new ArrayList<>();
                    for(FrameLabelListItem po: poList){
                        voList.add(new FrameLabelListItemVO(po.getStartX(),po.getStartY(),po.getWidth(),po.getHeight(),po.getTag()));
                    }
                    frameLabelVO = new FrameLabelVO(image, voList);
                    objectToJson = gson.toJson(frameLabelVO);
                    break;
                default:
                    return  "TypeError!";
            }

            return objectToJson;
        }

    }

    @Override
    public boolean saveLabel(String taskId, String userId, String labelType, int imageIndex, String labelVOJson) {
        Gson gson = new GsonBuilder().create();
        Label label = null;

        switch (labelType) {
            case "ImageLabel":
                ImageLabel imageLabel = gson.fromJson(labelVOJson, ImageLabel.class);
                label = imageLabel;
                break;
            case "AreaLabel":
                AreaLabel areaLabel = gson.fromJson(labelVOJson, AreaLabel.class);
                label = areaLabel;
                break;
            case "FrameLabel":
                FrameLabel frameLabel = gson.fromJson(labelVOJson, FrameLabel.class);
                label = frameLabel;
                break;
            default:
                return  false;
        }

        return labelDataService.saveLabel(userId, taskId, imageIndex, label);
    }

    @Override
    public boolean setTaskAccomplished(String taskId, String userId) {

        //获取任务详情
        Task task = taskDataService.getTask(taskId);
        int score = task.getScore();

//        //判断本次任务是否每一张图片都已经被标注
//        if(!labelDataService.isAccomplished(userId, taskId))
//            return false;

        //将用户接受的任务设定成已完成的状态
        if(!taskDataService.setAcceptedTaskAccomplished(userId, taskId))
            return false;

        //若后台修改积分失败，则报错
        if(!userDataService.reviseScore(userId, score))
            return false;

        //修改该任务的完成人数，使其加一
        if(!taskDataService.reviseTask(taskId))
            return false;

        return true;
    }
}
