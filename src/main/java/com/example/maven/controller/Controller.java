package com.example.maven.controller;

import com.example.maven.model.RectangleFrame;
import com.fasterxml.jackson.databind.util.JSONPObject;
import com.google.gson.*;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;

@RestController
public class Controller {

    @RequestMapping(value = "/sendRec", method = RequestMethod.GET)
    public String sendRec(@RequestParam(defaultValue="null") String recFrame){
        System.out.println(recFrame);

        Gson gson = new GsonBuilder().create();

        //处理数组
        //Json的解析类对象
        JsonParser parser = new JsonParser();
        //将JSON的String 转成一个JsonArray对象
        JsonArray jsonArray = parser.parse(recFrame).getAsJsonArray();

        ArrayList<RectangleFrame> rectangleFrameList = new ArrayList<>();
        //加强for循环遍历JsonArray
        for (JsonElement user : jsonArray) {
            //使用GSON，直接转成Bean对象
            RectangleFrame tempRecFrame = gson.fromJson(user, RectangleFrame.class);
            rectangleFrameList.add(tempRecFrame);
        }

        System.out.println(rectangleFrameList.size());
        System.out.println(rectangleFrameList.get(0).getTag());
        //String objectToJson = gson.toJson(null);


        //处理单个对象
        //RectangleFrame rec = gson.fromJson(recFrame, RectangleFrame.class);
        //
        //System.out.println(rec.getTag());
        return "sdsfsd"+recFrame;
    }

    @RequestMapping(value = "/getRec", method = RequestMethod.GET)
    public String getRec(){
        //RectangleFrame rec = new RectangleFrame(50, 50, 100, 50, "sdga");

        ArrayList<RectangleFrame> list = new ArrayList<>();

        list.add(new RectangleFrame(50, 50, 100, 50, "sdga"));
        list.add(new RectangleFrame(150, 150, 200, 200, "hhhh"));

        Gson gson = new GsonBuilder().create();
        String objectToJson = gson.toJson(list);
        System.out.println(objectToJson);

        return objectToJson;
    }
}
