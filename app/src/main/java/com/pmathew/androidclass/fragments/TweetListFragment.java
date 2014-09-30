package com.pmathew.androidclass.fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.loopj.android.http.JsonHttpResponseHandler;
import com.pmathew.androidclass.R;
import com.pmathew.androidclass.TwitterApplication;
import com.pmathew.androidclass.TwitterClient;
import com.pmathew.androidclass.adapter.TweetArrayAdapter;
import com.pmathew.androidclass.listener.EndlessScrollListener;
import com.pmathew.androidclass.models.Tweet;

import org.json.JSONArray;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by pmathew on 9/30/14.
 */
public class TweetListFragment extends Fragment {
    protected TwitterClient twitterClient;
    protected List<Tweet> tweets;
    protected TweetArrayAdapter tweetArrayAdapter;
    protected ListView lvTweets;

    protected String lastTweetId=null;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        tweets = new ArrayList<Tweet>();
        tweetArrayAdapter = new TweetArrayAdapter(getActivity().getApplicationContext(), tweets);
        twitterClient = TwitterApplication.getRestClient();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        final View v = inflater.inflate(R.layout.fragment_tweet_list, container, false);

        lvTweets = (ListView) v.findViewById(R.id.lvTweets);
        lvTweets.setAdapter(tweetArrayAdapter);
        lvTweets.setOnScrollListener(new EndlessScrollListener() {
            @Override
            public void onLoadMore(int page, int totalItemsCount) {
                // Triggered only when new data needs to be appended to the list
                // Add whatever code is needed to append new items to your AdapterView
                populateTimeline(page);
                // or customLoadMoreDataFromApi(totalItemsCount);
            }
        });

        populateTimeline(0);


        return v;
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



    public void refresh(){
        lastTweetId = null;
        tweetArrayAdapter.clear();
        populateTimeline(0);
    }

    public void addAll(List<Tweet> tweets) {
        tweetArrayAdapter.addAll(tweets);
    }
}
