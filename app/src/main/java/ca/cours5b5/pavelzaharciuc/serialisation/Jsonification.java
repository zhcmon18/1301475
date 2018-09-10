package ca.cours5b5.pavelzaharciuc.serialisation;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.HashMap;
import java.util.Map;

public class Jsonification {

    private static Gson gson = new GsonBuilder().create();

    public static Map<String, Object> enObjetJson(String json) {
        Map<String, Object> objetJson = gson.fromJson(json, Map.class);
        return objetJson;
    }

    public static String enChaine(Map<String, Object> objetJson) {
        return gson.toJson(objetJson);
    }

}
