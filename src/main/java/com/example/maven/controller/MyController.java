package com.example.maven.controller;

import com.example.maven.exception.loginException.AdministerLoginException;
import com.example.maven.model.po.Task;
import com.example.maven.model.vo.ImageLabelVO;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;

@RestController
public class MyController {

//    @RequestMapping(value = "/saveLabel", method = RequestMethod.GET)
//    public boolean saveLabel(String labelJson){
//
//        Gson gson = new GsonBuilder().create();
//
//        Label label = gson.fromJson(labelJson, ImageLabel.class);
//
////        System.out.println(gson.toJson(label));
////        {"label":"HelloWorld","taskId":"task00","imageId":"img00","userId":"123456","type":"ImageLabel"}
//
//        System.out.println(label.getType());
//
//        String s = gson.toJson(label);
//        ImageLabel imgLabel = gson.fromJson(s, ImageLabel.class);
//        System.out.println(imgLabel.getLabel());
//
//        //System.out.println(label.getLabel());
//        //ImageLabel y = new ImageLabel("task01","img01","user01","null");
//        //ImageLabel x = (ImageLabel) label;
//        //System.out.println(temp.getImageId());
//        return true;
//    }


    @RequestMapping(value = "/hello", method = RequestMethod.GET)
    public ArrayList<Task> getTaskList( String labelType,  String label){
        System.out.println(labelType);
        Gson gson = new GsonBuilder().create();

        ImageLabelVO mylabel = gson.fromJson(label, ImageLabelVO.class);

        System.out.println(mylabel.getLabel()+"  ");


        ArrayList<Task> list = new ArrayList<>();
        String[] info = {"a","b","c"};
        list.add(new Task("task00","ImageLabel", info, "nothing", 15,16,100));
        list.add(new Task("task01","FrameLabel", info, "nothing", 22,0,100));
        return list;
    }


    @RequestMapping(value = "/exception", method = RequestMethod.GET)
    public Exception getException( String userName){
        return new AdministerLoginException();
    }



}
