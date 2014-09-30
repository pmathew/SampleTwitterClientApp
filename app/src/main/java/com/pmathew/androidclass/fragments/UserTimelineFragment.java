package com.pmathew.androidclass.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.loopj.android.http.JsonHttpResponseHandler;
import com.pmathew.androidclass.ProfileActivity;
import com.pmathew.androidclass.models.Tweet;

import org.json.JSONArray;

import java.util.List;

/**
 * Created by pmathew on 9/30/14.
 */
public class UserTimelineFragment extends TweetListFragment {
    private Long uid;
   @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        uid = ((ProfileActivity) getActivity()).getUid();
        return super.onCreateView(inflater, container, savedInstanceState);
    }

    public void populateTimeline(int page){


        twitterClient.getUserTimeline(new JsonHttpResponseHandler() {
            @Override
            public void onSuccess(JSONArray jsonArray) {
                List<Tweet> tweets = Tweet.fromJSONArray(jsonArray);

                if (tweets != null && tweets.size() > 0) {
                    lastTweetId = String.valueOf(tweets.get(tweets.size() - 1).getUid());
                }

                tweetArrayAdapter.addAll(tweets);
            }
        }, uid, lastTweetId);
    }

}
