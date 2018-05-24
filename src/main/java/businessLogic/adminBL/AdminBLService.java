package businessLogic.adminBL;

import model.statistics.WebsiteStatistics;

public interface AdminBLService {
    /**
     * 获得站点统计信息
     * @return 站点统计信息类
     */
    WebsiteStatistics getWebsiteStatistics();
}
