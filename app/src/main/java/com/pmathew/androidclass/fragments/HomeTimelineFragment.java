package com.pmathew.androidclass.fragments;

import android.os.Bundle;

import com.loopj.android.http.JsonHttpResponseHandler;
import com.pmathew.androidclass.models.Tweet;

import org.json.JSONArray;

import java.util.List;

/**
 * Created by pmathew on 9/30/14.
 */
public class HomeTimelineFragment extends TweetListFragment {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        populateTimeline(0);
    }

    public void populateTimeline(int page){
        twitterClient.getHomeTimeline(new JsonHttpResponseHandler(){
            @Override
            public void onSuccess(JSONArray jsonArray) {
                List<Tweet> tweets = Tweet.fromJSONArray(jsonArray);

                if(tweets != null && tweets.size() > 0){
                    lastTweetId = String.valueOf(tweets.get(tweets.size() - 1).getUid());
                }

                tweetArrayAdapter.addAll(tweets);
            }
        }, lastTweetId);
    }
}
