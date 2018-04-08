package com.example.maven.controller;

import com.example.maven.model.Label;
import com.example.maven.model.Task;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class WorkerController {

    /**
     * 获取所有未完成的任务
     * @param userId 用户Id
     * @return 所有未完成的任务
     */
    @RequestMapping(value = "/WorkerController/getAllUnfinishedTasks", method = RequestMethod.GET)
    public List<Task> getAllUnfinishedTasks(String userId){
        return null;
    }

    /**
     * 获取所有已完成的任务
     * @param userId 用户Id
     * @return 所有已完成的任务
     */
    @RequestMapping(value = "/WorkerController/getAllFinishedTasks", method = RequestMethod.GET)
    public List<Task> getAllFinishedTasks(String userId){
        return null;
    }

    /**
     * 根据任务Id获取任务
     * @param taskId 任务Id
     * @return 任务详情
     */
    @RequestMapping(value = "/WorkerController/getTaskById", method = RequestMethod.GET)
    public Task getTaskById(String taskId){
        return null;
    }

    /**
     *  保存一次标注
     *  @param label 一次标注
     *  @return 是否保存成功
     */
    @RequestMapping(value = "/WorkerController/saveLabel", method = RequestMethod.GET)
    public boolean saveLabel(Label label){
        return false;
    }

    /**
     * 提交一次完成的任务
     * @param task
     * @return 是否提交成功
     */
    @RequestMapping(value = "/WorkerController/submitFinishedTask", method = RequestMethod.GET)
    public boolean submitFinishedTask(Task task){
        return false;
    }

    /**
     * 获取用户的积分
     * @param userId 用户编号
     * @return 用户积分
     */
    @RequestMapping(value = "/WorkerController/getUserScore", method = RequestMethod.GET)
    public int getUserScore(String userId){
        return 0;
    }

    /**
     * 获取用户的排名
     * @param userId 用户Id
     * @return 用户排名
     */
    @RequestMapping(value = "/WorkerController/getUserRanking", method = RequestMethod.GET)
    public int getUserRanking(String userId){
        return 0;
    }
}
