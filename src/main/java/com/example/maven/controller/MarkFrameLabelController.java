package com.example.maven.controller;

import com.example.maven.model.frameLabel.FrameLabel;
import com.example.maven.model.frameLabel.FrameLabelTagItem;
import com.google.gson.*;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;

@RestController
public class MarkFrameLabelController {
    @RequestMapping(value = "/markFrameLabel/saveFrameLabel", method = RequestMethod.GET)
    public String saveFrameLabel(@RequestParam(defaultValue="null") String frameLabelJson){
        System.out.println(frameLabelJson);

        Gson gson = new GsonBuilder().create();

        FrameLabel frameLabel = gson.fromJson(frameLabelJson, FrameLabel.class);

        ////处理数组
        ////Json的解析类对象
        //JsonParser parser = new JsonParser();
        ////将JSON的String 转成一个JsonArray对象
        //JsonArray jsonArray = parser.parse(recFrame).getAsJsonArray();
        //
        //ArrayList<FrameLabel> frameLabelList = new ArrayList<>();
        ////加强for循环遍历JsonArray
        //for (JsonElement user : jsonArray) {
        //    //使用GSON，直接转成Bean对象
        //    FrameLabel tempRecFrame = gson.fromJson(user, FrameLabel.class);
        //    if (tempRecFrame != null) {
        //        frameLabelList.add(tempRecFrame);
        //    }
        return "success";
    }

    @RequestMapping(value = "/markFrameLabel/getFrameLabel", method = RequestMethod.GET)
    public String getFrameLabel(String userId, String imageId){
        String nextImageID = "1";

        ArrayList<FrameLabelTagItem> frameLabelTagItemList = new ArrayList<>();
        frameLabelTagItemList.add(new FrameLabelTagItem(50, 50, 100, 50, "sdga"));
        frameLabelTagItemList.add(new FrameLabelTagItem(150, 150, 200, 200, "hhhh"));

        FrameLabel frameLabel = new FrameLabel(nextImageID,"test", frameLabelTagItemList);

        Gson gson = new GsonBuilder().create();
        String objectToJson = gson.toJson(frameLabel);
        System.out.println(objectToJson);

        return objectToJson;
    }

}
