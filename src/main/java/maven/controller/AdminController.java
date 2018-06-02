package maven.controller;

import maven.businessLogic.adminBL.AdminBLImpl;
import maven.businessLogic.adminBL.AdminBLService;
import maven.businessLogic.adminBL.AdminBLStub;
import maven.model.JsonConverter;
import maven.model.statistics.WebsiteStatistics;
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
        adminBL = new AdminBLStub();
    }

    @RequestMapping(value = "/admin/getWebsiteStatistics", method = RequestMethod.GET)
    public WebsiteStatistics getWebsiteStatistics(){
        return adminBL.getWebsiteStatistics();
    }
}
