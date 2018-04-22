//package com.example.maven.controller.markLabel;
//
//import com.example.maven.model.po.AreaLabel;
//import com.google.gson.Gson;
//import com.google.gson.GsonBuilder;
//import org.springframework.web.bind.annotation.*;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RequestMethod;
//
//import java.util.ArrayList;
//
//@RestController
//public class MarkAreaLabelController {
//    //private AreaLabelDataImpl areaLabelDataImpl = new AreaLabelDataImpl();
//
//    @RequestMapping(value = "/markAreaLabel/saveAreaLabel", method = RequestMethod.GET)
//    public String saveAreaLabel(@RequestParam(defaultValue="null") String areaLabelJson){
////        System.out.println("aaaa"+areaLabelJson);
//
//        Gson gson = new GsonBuilder().create();
//
//        AreaLabel areaLabel = gson.fromJson(areaLabelJson, AreaLabel.class);
//
//        areaLabelDataImpl.saveAreaLabel(areaLabel);
//
//
//        return "success";
//    }
//
//    @RequestMapping(value = "/markAreaLabel/getAreaLabel", method = RequestMethod.POST)
//    public String getAreaLabel(String imageId){
//
//        ArrayList<AreaLabel> list = areaLabelDataImpl.getAllAreaLabel();
//        Gson gson = new GsonBuilder().create();
//        String objectToJson = gson.toJson(list.get(Integer.parseInt(imageId)));
////        System.out.println(list.get(0).getLabel());
////        ArrayList<String> s = new ArrayList<>();
////        s.add("1,20;30,50;");
////        s.add("1,20;30,50;");
////
////        AreaLabel areaLabel = new AreaLabel("0001", "adminBL", "myLabel", s);
//
//        return objectToJson;
//    }
//
//}
