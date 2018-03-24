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

    @RequestMapping(value = "/markFrameLabel/getImageById", method = RequestMethod.GET)
    public String getImageById(String imageId){
        System.out.println(imageId);
        if(imageId.equals("1")){
            return  "url(https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1521639514661&di=9e04cf69a7d903dec78f6920566a7da1&imgtype=0&src=http%3A%2F%2Fimgsrc.baidu.com%2Fimgad%2Fpic%2Fitem%2Ff2deb48f8c5494eeedc9563026f5e0fe99257e2b.jpg)";
        }
        return "url(https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1521777668715&di=c3e8387371028b1d0b831106405e09b2&imgtype=0&src=http%3A%2F%2Fimgsrc.baidu.com%2Fimage%2Fc0%253Dshijue1%252C0%252C0%252C294%252C40%2Fsign%3D91ce57251530e924dba9947224610473%2Fb999a9014c086e068b53756608087bf40ad1cb20.jpg)";
    }

    @RequestMapping(value = "/markFrameLabel/getNextImageId", method = RequestMethod.GET)
    public String getNextImageId(String currentImageId){
        System.out.println(currentImageId);
        return String.valueOf(Integer.parseInt(currentImageId) + 1);
    }
}
