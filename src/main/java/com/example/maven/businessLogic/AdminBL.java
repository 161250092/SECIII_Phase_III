package com.example.maven.businessLogic;

import com.example.maven.model.WebsiteStatistics;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class AdminBL {

    public String getWebsiteStatistics(){
        Gson gson = new GsonBuilder().create();
        String objectToJson = gson.toJson(new WebsiteStatistics(10,9,8,7));
        return objectToJson;
    }
}
