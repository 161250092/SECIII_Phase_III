package com.example.maven.data.LabelData;

import com.example.maven.model.po.Label;

import java.util.List;

public class LabelDataImpl implements LabelDataService{
    /**
     * 获取用户已经标注的信息
     * @return 标签List
     */
    public List<Label> getLabel(String userId, String taskId){
        return null;
    }

    /**
     * 保存用户标注信息
     * @param imageIndex 对应的图片的位置
     */
    public boolean saveLabel(String userId,String taskId,int imageIndex,Label label){
        return false;
    }

    /**
     * 判断是否完成标注
     */
    public boolean isCompletedTask(String userId,String taskId){
        return false;
    }

    /**
     * 用户接受任务
     * 创建文件夹
     * @param number 任务的图片数量
     */
    public boolean accpetTask(String userId,String taskId,int number){
        return false;
    }
}
