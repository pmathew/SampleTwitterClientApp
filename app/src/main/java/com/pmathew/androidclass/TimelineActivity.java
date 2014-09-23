package com.pmathew.androidclass;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.loopj.android.http.JsonHttpResponseHandler;
import com.pmathew.androidclass.R;
import com.pmathew.androidclass.adapter.TweetArrayAdapter;
import com.pmathew.androidclass.listener.EndlessScrollListener;
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
    public static final int REQUEST_CODE=123;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_timeline);
        twitterClient = TwitterApplication.getRestClient();
        tweets = new ArrayList<Tweet>();
        lvTweets = (ListView) findViewById(R.id.lvTweets);
        tweetArrayAdapter = new TweetArrayAdapter(this, tweets);
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
    }

    private void populateTimeline(int page){
        twitterClient.getHomeTimeline(new JsonHttpResponseHandler(){
            @Override
            public void onSuccess(JSONArray jsonArray) {
                tweetArrayAdapter.addAll(Tweet.fromJSONArray(jsonArray));
            }
        });

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.timeline, menu);
        return true;
    }


    public void composeNew(MenuItem menuItem){
        Intent i  = new Intent(this,ComposeActivity.class);
        startActivityForResult(i, REQUEST_CODE);
    }

    public void refresh(MenuItem menuItem){
        tweetArrayAdapter.clear();
        populateTimeline(0);
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        tweetArrayAdapter.clear();
        populateTimeline(0);
    }


}
