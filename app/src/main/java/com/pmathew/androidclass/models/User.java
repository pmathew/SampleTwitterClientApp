package com.pmathew.androidclass.models;

import org.json.JSONObject;

/**
 * Created by pmathew on 9/22/14.
 */
public class User {
    private String name;
    private long uid;
    private String screenName;
    private String profileImgUrl;



    public static User fromJSON(JSONObject jsonObject){
        User user = new User();
        try {
            user.name = jsonObject.getString("name");
            user.uid = jsonObject.getLong("id");
            user.screenName = jsonObject.getString("screen_name");
            user.profileImgUrl = jsonObject.getString("profile_image_url");
        } catch (Exception e){
            e.printStackTrace();
            return null;
        }
        return user;
    }

    public String getName() {
        return name;
    }

    public long getUid() {
        return uid;
    }

    public String getScreenName() {
        return screenName;
    }

    public String getProfileImgUrl() {
        return profileImgUrl;
    }
}
