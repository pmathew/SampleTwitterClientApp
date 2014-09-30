package com.pmathew.androidclass;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.util.AttributeSet;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.loopj.android.http.JsonHttpResponseHandler;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.pmathew.androidclass.R;
import com.pmathew.androidclass.fragments.UserTimelineFragment;
import com.pmathew.androidclass.models.User;

import org.json.JSONObject;

public class ProfileActivity extends FragmentActivity {
    private TwitterClient twitterClient;
    private Long uid;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        User user = (User) getIntent().getSerializableExtra("user");
        if(user != null){
            uid = user.getUid();
        }

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
        twitterClient = TwitterApplication.getRestClient();
        if(user == null){
            twitterClient.getMyInfo(new JsonHttpResponseHandler(){
                @Override
                public void onSuccess(JSONObject jsonObject) {
                    User user = User.fromJSON(jsonObject);
                    loadDetails(user);
                }
            });
        } else {
            loadDetails(user);
        }
    }



    private void loadDetails(User user){
        getActionBar().setTitle("@"+user.getScreenName());
        TextView tvName = (TextView) findViewById(R.id.tvName);
        TextView tvTagline = (TextView) findViewById(R.id.tvTagline);
        TextView tvFollowers = (TextView) findViewById(R.id.tvFollowers);
        TextView tvFollowing = (TextView) findViewById(R.id.tvFollowing);
        ImageView imageView = (ImageView) findViewById(R.id.ivProfileImage);
        tvName.setText(user.getName());
        tvTagline.setText(user.getTagline());
        tvFollowers.setText(user.getFollowersCount() + " Followers");
        tvFollowing.setText(user.getFollowingCount() + " Following");
        ImageLoader imageLoader = ImageLoader.getInstance();
        imageLoader.displayImage(user.getProfileImgUrl(), imageView);

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.profile, menu);
        return true;
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

    public Long getUid() {
        return uid;
    }
}
