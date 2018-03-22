package com.example.maven.controller;

import com.example.maven.model.RectangleFrame;
import com.fasterxml.jackson.databind.util.JSONPObject;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class Controller {

    @RequestMapping(value = "/sendRec", method = RequestMethod.GET)
    public String sendRec(@RequestParam(defaultValue="null") String recFrame){
        System.out.println(recFrame);

        Gson gson = new GsonBuilder().create();
        //String objectToJson = gson.toJson(null);

        RectangleFrame rec = gson.fromJson(recFrame, RectangleFrame.class);

        System.out.println(rec.getTag());
        return "sdsfsd"+recFrame;
    }

    @RequestMapping(value = "/getRec", method = RequestMethod.GET)
    public String getRec(){
        RectangleFrame rec = new RectangleFrame(50, 50, 100, 50, "sdga");

        Gson gson = new GsonBuilder().create();
        String objectToJson = gson.toJson(rec);
        System.out.println(objectToJson);

        return objectToJson;
    }
}
