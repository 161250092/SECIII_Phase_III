package com.example.maven.controller;

import com.example.maven.model.AreaLabel;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.ArrayList;

@RestController
public class MarkAreaLabelController {
    @RequestMapping(value = "/saveAreaLabel", method = RequestMethod.GET)
    public String saveAreaLabel(@RequestParam(defaultValue="null") String areaLabelJson){
        System.out.println(areaLabelJson);

        Gson gson = new GsonBuilder().create();

        AreaLabel areaLabel = gson.fromJson(areaLabelJson, AreaLabel.class);

        return "success";
    }

    @RequestMapping(value = "/getAreaLabel", method = RequestMethod.GET)
    public String getAreaLabel(String imageID){

        ArrayList<String> s = new ArrayList<>();
        s.add("1,20;30,50;");
        s.add("1,20;30,50;");

        AreaLabel areaLabel = new AreaLabel("0001", "admin", "myLabel", s);
        Gson gson = new GsonBuilder().create();
        String objectToJson = gson.toJson(areaLabel);
        System.out.println(objectToJson);

        return objectToJson;
    }

}
