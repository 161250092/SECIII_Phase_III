package data.LabelData;

import model.label.Label;

import java.util.ArrayList;
import java.util.List;

public class LabelDataStub implements LabelDataService{
    /**
     * 获取用户已经标注的信息
     * @return 标签List
     */
    public List<Label> getLabel(String userId, String taskId){
        List<Label> label = new ArrayList<Label>();
        return label;
    }

    /**
     * 保存用户标注信息
     *@param imageIndex 图片位置
     */
    public boolean saveLabel(String userId,String taskId,int imageIndex,Label label){
        return true;
    }

    /**
     * 判断是否完成标注
     */
    public boolean isAccomplished(String userId,String taskId){
        return true;
    }

    /**
     * 用户接受任务
     * 创建文件夹
     * @param number 任务的图片数量
     */
    public boolean acceptTask(String userId,String taskId,int number){
        return true;
    }
}
