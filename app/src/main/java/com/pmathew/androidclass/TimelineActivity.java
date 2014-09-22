package com.pmathew.androidclass;

import android.app.Activity;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.loopj.android.http.JsonHttpResponseHandler;
import com.pmathew.androidclass.R;
import com.pmathew.androidclass.adapter.TweetArrayAdapter;
import com.pmathew.androidclass.models.Tweet;

import org.json.JSONArray;
import org.scribe.builder.api.TwitterApi;

import java.util.ArrayList;
import java.util.List;

public class TimelineActivity extends Activity {

    private TwitterClient twitterClient;
    private List<Tweet> tweets;
    private TweetArrayAdapter tweetArrayAdapter;
    private ListView lvTweets;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_timeline);
        twitterClient = TwitterApplication.getRestClient();
        tweets = new ArrayList<Tweet>();
        lvTweets = (ListView) findViewById(R.id.lvTweets);
        tweetArrayAdapter = new TweetArrayAdapter(this, tweets);
        lvTweets.setAdapter(tweetArrayAdapter);
        populateTimeline();
    }

    private void populateTimeline(){
        twitterClient.getHomeTimeline(new JsonHttpResponseHandler(){
            @Override
            public void onSuccess(JSONArray jsonArray) {
                tweetArrayAdapter.addAll(Tweet.fromJSONArray(jsonArray));
            }
        });

    }



}
