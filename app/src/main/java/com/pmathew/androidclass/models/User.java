package com.pmathew.androidclass.models;

import org.json.JSONObject;

import java.io.Serializable;

/**
 * Created by pmathew on 9/22/14.
 */
public class User implements Serializable{
    private String name;
    private long uid;
    private String screenName;
    private String profileImgUrl;
    private String tagline;
    private long followersCount;
    private long followingCount;



    public static User fromJSON(JSONObject jsonObject){
        User user = new User();
        try {
            user.name = jsonObject.getString("name");
            user.uid = jsonObject.getLong("id");
            user.screenName = jsonObject.getString("screen_name");
            user.profileImgUrl = jsonObject.getString("profile_image_url");
            user.tagline = jsonObject.getString("description");
            user.followersCount = jsonObject.getLong("followers_count");
            user.followingCount = jsonObject.getLong("friends_count");
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

    public String getTagline() {
        return tagline;
    }

    public long getFollowersCount() {
        return followersCount;
    }

    public long getFollowingCount() {
        return followingCount;
    }
}
