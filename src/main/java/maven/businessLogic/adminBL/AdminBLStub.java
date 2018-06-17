package maven.businessLogic.adminBL;

import maven.model.statistics.WebsiteStatistics;
import maven.model.statistics.WebsiteTrafficStatics;

import java.util.Date;

public class AdminBLStub implements AdminBLService{
    @Override
    public WebsiteStatistics getWebsiteStatistics() {
        return new WebsiteStatistics(10, 9, 8, 6,2, 0, 0);
    }

    @Override
    public WebsiteTrafficStatics getWebsiteTrafficStatics(Date startDate, Date endDate) {
        return null;
    }
}
