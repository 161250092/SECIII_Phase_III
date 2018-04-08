package com.example.maven.controller;

import com.example.maven.businessLogic.AdminBL;
import com.example.maven.model.WebsiteStatistics;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * 管理员控制器
 */
@RestController
public class AdminController {

    private AdminBL adminBL;

    public AdminController(){
        adminBL = new AdminBL();
    }

    /**
     * 获得站点统计信息
     * @return 站点统计信息类Json字符串
     */
    @RequestMapping(value = "/AdministerController/uploadImages", method = RequestMethod.GET)
    public String getWebsiteStatistics(){
        return adminBL.getWebsiteStatistics();
    }
}
