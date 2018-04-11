package com.example.maven.controller;

import com.example.maven.businessLogic.adminBL.AdminBLImpl;
import com.example.maven.businessLogic.adminBL.AdminBLService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * 管理员控制器
 */
@RestController
public class AdminController implements AdminBLService {

    private AdminBLService adminBL;

    public AdminController(){
        adminBL = new AdminBLImpl();
    }

    @RequestMapping(value = "/AdministerController/uploadImages", method = RequestMethod.GET)
    public String getWebsiteStatistics(){
        return adminBL.getWebsiteStatistics();
    }
}
