package maven.controller;

import maven.businessLogic.adminBL.AdminBLImpl;
import maven.businessLogic.adminBL.AdminBLService;
import maven.model.JsonConverter;
import maven.model.statistics.WebsiteStatistics;
import maven.model.statistics.WebsiteTrafficStatics;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

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
    public WebsiteStatistics getWebsiteStatistics(){
        return adminBL.getWebsiteStatistics();
    }

    /**
     * 获取从开始日期（包括开始日期）到结束日期（不包括结束日期）内 网站流量统计信息
     * @param startDate 开始日期
     * @param endDate 结束日期
     * @return 统计信息
     */
    @RequestMapping(value = "/admin/getWebsiteTrafficStatics", method = RequestMethod.GET)
    public WebsiteTrafficStatics getWebsiteTrafficStatics(String startDate, String endDate){
        System.out.println(startDate);
        System.out.println(endDate);
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        Date start = null;
        Date end = null;
        try {
            start = format.parse(startDate);
            end = format.parse(endDate);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return adminBL.getWebsiteTrafficStatics(start, end);
    }
}
