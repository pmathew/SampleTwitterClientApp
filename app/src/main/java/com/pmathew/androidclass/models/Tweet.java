package com.pmathew.androidclass.models;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by pmathew on 9/22/14.
 */
public class Tweet {
    private String body;
    private long uid;
    private String createdAt;
    private User user;

    public static Tweet fromJSON(JSONObject jsonObject){
        Tweet tweet = new Tweet();
        try {
            tweet.body = jsonObject.getString("text");
            tweet.uid = jsonObject.getLong("id");
            tweet.createdAt = jsonObject.getString("created_at");
            tweet.user = User.fromJSON(jsonObject.getJSONObject("user"));
        } catch (Exception e){
            e.printStackTrace();
            return null;
        }
        return tweet;
    }

    public static List<Tweet> fromJSONArray(JSONArray jsonArray){
        List<Tweet> items = new ArrayList<Tweet>();
        try{
            for( int i =0; i < jsonArray.length(); i++){
                items.add(fromJSON(jsonArray.getJSONObject(i)));
            }
        } catch (Exception e){
            e.printStackTrace();
            return null;
        }

        return items;
    }

    public String getBody() {
        return body;
    }

    public long getUid() {
        return uid;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    public User getUser() {
        return user;
    }
}
