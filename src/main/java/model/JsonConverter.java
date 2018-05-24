package model;

import com.google.gson.*;

import java.lang.reflect.Type;

public class JsonConverter {
    private static Gson gson = new GsonBuilder().create();

    public static String objectToJson(Object object){
        return gson.toJson(object);
    }

    public static Object jsonToObject(String jsonStr, Type objectType){
        return gson.fromJson(jsonStr, objectType);
    }
}
