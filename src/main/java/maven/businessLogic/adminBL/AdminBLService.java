package maven.businessLogic.adminBL;

import maven.model.statistics.WebsiteStatistics;
import maven.model.statistics.WebsiteTrafficStatics;

import java.util.Date;

public interface AdminBLService {
    /**
     * 获得站点统计信息
     * @return 站点统计信息类
     */
    WebsiteStatistics getWebsiteStatistics();

    WebsiteTrafficStatics getWebsiteTrafficStatics(Date startDate, Date endDate);
}
