package com.example.maven.service.DataService;

import com.example.maven.model.Label;
import java.util.List;

public interface LabelDataService {

    /**
     * 获取用户已经标注的信息
     * @return 标签List
     */
    public List<Label> getLabel(String userId,String taskId);

    /**
     * 保存用户标注信息
     * @param number 对应的图片的位置
     */
    public boolean saveLabel(String userId,String taskId,int number,Label label);

    /**
     * 判断是否完成标注
     */
    public boolean completedLabel(String userId,String taskId);

    /**
     * 用户接受任务
     * 创建文件夹
     * @param number 任务的图片数量
     */
    public boolean accpetTask(String userId,String taskId,int number);
}
