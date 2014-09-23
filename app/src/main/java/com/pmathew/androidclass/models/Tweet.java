package com.pmathew.androidclass.models;

import org.json.JSONArray;
import org.json.JSONObject;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
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

    public User getUser() {
        return user;
    }


    public String getCreatedAt() {
        //add logic for converting to expected format
        try {
            Date tweetDate =  getTwitterDate(createdAt);
            Date now = new Date();

            long diff = now.getTime() - tweetDate.getTime();

            long diffSeconds = diff / 1000 % 60;
            long diffMinutes = diff / (60 * 1000) % 60;
            long diffHours = diff / (60 * 60 * 1000) % 24;
            long diffDays = diff / (24 * 60 * 60 * 1000);

            System.out.print(diffDays + " days, ");
            System.out.print(diffHours + " hours, ");
            System.out.print(diffMinutes + " minutes, ");
            System.out.print(diffSeconds + " seconds.");
            if(diffDays >0){
                return Long.toString(diffDays) + "d";
            }
            if(diffHours >0 ){
                return Long.toString(diffHours)+ "h";
            }
            if(diffMinutes>0){
                return Long.toString(diffMinutes)+ "m";
            }
            if(diffSeconds>0){
                return Long.toString(diffMinutes)+ "s";
            }

        } catch (ParseException e) {
            e.printStackTrace();
        }
        return "";
    }

    public static Date getTwitterDate(String date) throws ParseException {
        final String TWITTER="EEE MMM dd HH:mm:ss ZZZZZ yyyy";
        SimpleDateFormat sf = new SimpleDateFormat(TWITTER);
        sf.setLenient(true);
        return sf.parse(date);
    }

}
