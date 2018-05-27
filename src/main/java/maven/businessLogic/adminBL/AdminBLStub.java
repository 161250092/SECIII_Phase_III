package maven.businessLogic.adminBL;

import maven.model.statistics.WebsiteStatistics;

public class AdminBLStub implements AdminBLService{
    @Override
    public WebsiteStatistics getWebsiteStatistics() {
        return new WebsiteStatistics(10, 9, 8, 7);
    }
}
