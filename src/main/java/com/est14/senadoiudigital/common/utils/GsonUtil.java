package com.est14.senadoiudigital.common.utils;

import com.google.gson.Gson;

public class GsonUtil {


    // Java object to Json
    public static String serialize(Object src){
        Gson gson = new Gson();
        return gson.toJson(src);
    }

    // Json to Java object
    public static <D> D toObject(String json, Class<D> dClass){
        Gson gson = new Gson();
        return gson.fromJson(json, dClass);
    }

    // Java object to Java object
    public static <D> D toObject(Object src, Class<D> dClass){
        Gson gson = new Gson();
        String srcJson = gson.toJson(src);
        return gson.fromJson(srcJson, dClass);
    }
}
