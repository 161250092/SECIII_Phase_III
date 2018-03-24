package com.example.maven.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

public class ImageController {
    @RequestMapping(value = "/imageController/getImageById", method = RequestMethod.GET)
    public String getImageById(String imageId){
        System.out.println(imageId);
        if(imageId.equals("1")){
            return  "url(https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1521639514661&di=9e04cf69a7d903dec78f6920566a7da1&imgtype=0&src=http%3A%2F%2Fimgsrc.baidu.com%2Fimgad%2Fpic%2Fitem%2Ff2deb48f8c5494eeedc9563026f5e0fe99257e2b.jpg)";
        }
        return "url(https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1521777668715&di=c3e8387371028b1d0b831106405e09b2&imgtype=0&src=http%3A%2F%2Fimgsrc.baidu.com%2Fimage%2Fc0%253Dshijue1%252C0%252C0%252C294%252C40%2Fsign%3D91ce57251530e924dba9947224610473%2Fb999a9014c086e068b53756608087bf40ad1cb20.jpg)";
    }

    @RequestMapping(value = "/imageController/getNextImageId", method = RequestMethod.GET)
    public String getNextImageId(String currentImageId){
        System.out.println(currentImageId);
        return String.valueOf(Integer.parseInt(currentImageId) + 1);
    }

    @RequestMapping(value = "/imageController/getNextImageId", method = RequestMethod.GET)
    public String getPreviousImageId(String currentImageId){
        System.out.println(currentImageId);
        return String.valueOf(Integer.parseInt(currentImageId) - 1);
    }

}
