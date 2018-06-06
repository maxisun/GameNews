package com.example.max00.gamenews.API;

import com.example.max00.gamenews.Classes.News;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;

import java.lang.reflect.Type;

public class NewsDeserializer implements JsonDeserializer<News> {

    public News news;
    @Override
    public News deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
        news = new News();
        JsonObject jsonObject = json.getAsJsonObject();
        news.setId(jsonObject.get("_id").getAsString());
        news.setTitle(jsonObject.get("title").getAsString());
        news.setCoverImage(jsonObject.get("coverImage").getAsString());
        news.setCreate_date(jsonObject.get("created_date").getAsString());
        news.setDescription(jsonObject.get("description").getAsString());
        news.setBody(jsonObject.get("body").getAsString());
        news.setGame(jsonObject.get("game").getAsString());
        return news;
    }
}
