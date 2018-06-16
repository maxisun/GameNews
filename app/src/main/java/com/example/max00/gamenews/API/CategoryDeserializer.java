package com.example.max00.gamenews.API;

import com.google.gson.JsonArray;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonParseException;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

public class CategoryDeserializer implements JsonDeserializer<List<String>>{

    @Override
    public List<String> deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
        List<String> category = new ArrayList<>();
        //el api establece que regresara un ARRAY de strings
        JsonArray jsonArray = json.getAsJsonArray();
        //recorriendo el jsonArray de Jsonelements
        for(JsonElement i : jsonArray){
            category.add(i.getAsString());
        }
        return category;
    }
}
