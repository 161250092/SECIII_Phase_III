package com.example.maven.controller.template;

import com.example.maven.model.WebsiteStatistics;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * 管理员控制器
 */
@RestController
public class AdminController {

    /**
     * 获得站点统计信息
     * @return 站点统计信息类
     */
    @RequestMapping(value = "/AdministerController/uploadImages", method = RequestMethod.GET)
    public WebsiteStatistics getWebsiteStatistics(){
        return null;
    }
}
