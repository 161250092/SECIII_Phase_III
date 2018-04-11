package com.example.maven.controller;

import com.example.maven.businessLogic.requestorBL.RequestorBLService;
import com.example.maven.model.Task;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * 众包发起者控制器
 */
@RestController
public class RequestorController implements RequestorBLService {

    /**
     * 上传图片集
     */
    @RequestMapping(value = "/RequestorController/uploadImages", method = RequestMethod.GET)
    public void uploadImages(){

    }

    /**
     * 发布任务
     * @param task 任务
     * @return 后端处理发布请求的结果
     */
    @RequestMapping(value = "/RequestorController/assignTask", method = RequestMethod.GET)
    public Exception assignTask(Task task){
        return null;
    }

    /**
     * 获取发布且已完成任务的列表
     * @param userId 用户Id
     * @return 发布且已完成任务的列表
     */
    @RequestMapping(value = "/RequestorController/getAssignedAndAccomplishedTaskList", method = RequestMethod.GET)
    public List<Task> getAssignedAndAccomplishedTaskList(String userId){
        return null;
    }

    /**
     * 获取发布但未完成任务的列表
     * @param userId 用户Id
     * @return 发布但未完成任务的列表
     */
    @RequestMapping(value = "/RequestorController/getAssignedButIncompleteTaskList", method = RequestMethod.GET)
    public List<Task> getAssignedButIncompleteTaskList(String userId){
        return null;
    }

    /**
     * 获取发布任务总数
     * @return 发布任务总数
     */
    @RequestMapping(value = "/RequestorController/getAssignedTaskNum", method = RequestMethod.GET)
    public int getAssignedTaskNum(){
        return 0;
    }
}
