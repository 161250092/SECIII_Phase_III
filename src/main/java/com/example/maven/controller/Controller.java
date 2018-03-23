package com.example.maven.controller;

import com.example.maven.model.RectangleFrame;
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
            if (tempRecFrame != null) {
                rectangleFrameList.add(tempRecFrame);
            }
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

    @RequestMapping(value = "/getPhoto", method = RequestMethod.GET)
    public String getPhoto(){
        return "url(https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1521777668715&di=c3e8387371028b1d0b831106405e09b2&imgtype=0&src=http%3A%2F%2Fimgsrc.baidu.com%2Fimage%2Fc0%253Dshijue1%252C0%252C0%252C294%252C40%2Fsign%3D91ce57251530e924dba9947224610473%2Fb999a9014c086e068b53756608087bf40ad1cb20.jpg)";
    }
}
