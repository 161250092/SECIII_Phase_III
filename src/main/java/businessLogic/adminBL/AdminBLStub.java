package businessLogic.adminBL;

import model.po.WebsiteStatistics;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class AdminBLStub implements AdminBLService{
    @Override
    public WebsiteStatistics getWebsiteStatistics() {
        return new WebsiteStatistics(10, 9, 8, 7);
    }
}
