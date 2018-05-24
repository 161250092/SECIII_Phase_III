package controller;

import businessLogic.adminBL.AdminBLImpl;
import businessLogic.adminBL.AdminBLService;
import model.JsonConverter;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * 管理员控制器
 */
@RestController
public class AdminController {

    private AdminBLService adminBL;

    public AdminController(){
        adminBL = new AdminBLImpl();
    }

    @RequestMapping(value = "/admin/getWebsiteStatistics", method = RequestMethod.GET)
    public String getWebsiteStatistics(){
        return JsonConverter.toJson(adminBL.getWebsiteStatistics());
    }
}
