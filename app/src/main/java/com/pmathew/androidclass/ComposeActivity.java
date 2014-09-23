package com.pmathew.androidclass;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.content.Intent;
import android.util.Log;
import android.view.View;
import android.widget.EditText;


import com.loopj.android.http.JsonHttpResponseHandler;

import org.json.JSONArray;


public class ComposeActivity extends Activity {

    private TwitterClient client;
    private EditText etTweet;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_compose);
        etTweet = (EditText)findViewById(R.id.etTweet);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.compose, menu);
        getActionBar().removeAllTabs();
        return true;
    }

    public void postTweet(final View v){
        client =  TwitterApplication.getRestClient();
        client.postTweet(new JsonHttpResponseHandler(){
            @Override
            public void onSuccess(JSONArray json) {
                super.onSuccess(json);
                Log.d("Debug", json.toString());
            }

            @Override
            public void onFailure(Throwable throwable, String s) {
                super.onFailure(throwable, s);
                throwable.printStackTrace();
                Log.d("Debug", s.toString());
            }

        },etTweet.getText().toString());

        Intent data = new Intent();
        setResult(RESULT_OK, data); // set result code and bundle data for response
        finish(); // closes the activity, pass data to parent
    }


    public void cancelTweet(final View v){
        Intent data = new Intent();
        setResult(RESULT_OK, data); // set result code and bundle data for response
        finish(); // closes the activity, pass data to parent
    }




    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}