package com.pmathew.androidclass;

import android.app.ActionBar;
import android.app.Activity;
import android.content.Intent;
import android.support.v4.app.FragmentActivity;
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
import com.pmathew.androidclass.fragments.HomeTimelineFragment;
import com.pmathew.androidclass.fragments.MentionsTimelineFragment;
import com.pmathew.androidclass.fragments.TweetListFragment;
import com.pmathew.androidclass.listener.EndlessScrollListener;
import com.pmathew.androidclass.listeners.FragmentTabListener;
import com.pmathew.androidclass.models.Tweet;

import org.json.JSONArray;
import org.scribe.builder.api.TwitterApi;

import java.util.ArrayList;
import java.util.List;

public class TimelineActivity extends FragmentActivity {
    public static final int REQUEST_CODE=123;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_timeline);

        setupTabs();
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

    public void profileView(MenuItem menuItem){
        Intent i  = new Intent(this,ProfileActivity.class);
        startActivityForResult(i, REQUEST_CODE);
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {

    }

    private void setupTabs() {
        ActionBar actionBar = getActionBar();
        actionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_TABS);
        actionBar.setDisplayShowTitleEnabled(true);

        ActionBar.Tab tab1 = actionBar
                .newTab()
                .setText("Home")
                .setIcon(R.drawable.ic_home)
                .setTag("HomeTimelineFragment")
                .setTabListener(
                        new FragmentTabListener<HomeTimelineFragment>(R.id.flContainer, this, "home",
                                HomeTimelineFragment.class));

        actionBar.addTab(tab1);
        actionBar.selectTab(tab1);

        ActionBar.Tab tab2 = actionBar
                .newTab()
                .setText("Mentions")
                .setIcon(R.drawable.ic_mentions)
                .setTag("MentionsTimelineFragment")
                .setTabListener(
                        new FragmentTabListener<MentionsTimelineFragment>(R.id.flContainer, this, "mentions",
                                MentionsTimelineFragment.class));

        actionBar.addTab(tab2);
    }


}
