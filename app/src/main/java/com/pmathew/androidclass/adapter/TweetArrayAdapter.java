package com.pmathew.androidclass.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.nostra13.universalimageloader.core.ImageLoader;
import com.pmathew.androidclass.ProfileActivity;
import com.pmathew.androidclass.R;
import com.pmathew.androidclass.models.Tweet;

import java.util.List;

/**
 * Created by pmathew on 9/22/14.
 */
public class TweetArrayAdapter extends ArrayAdapter<Tweet> {
    public TweetArrayAdapter(Context context, List<Tweet> objects) {
        super(context, 0, objects);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        final Tweet tweet = getItem(position);
        View v = convertView;
        if(v == null){
            LayoutInflater inflater = LayoutInflater.from(getContext());
            v = inflater.inflate(R.layout.tweet_item, parent, false);
        }

        ImageView imageView = (ImageView) v.findViewById(R.id.ivProfile);
        TextView name = (TextView) v.findViewById(R.id.tvUserName);
        TextView body = (TextView) v.findViewById(R.id.tvBody);
        TextView time = (TextView) v.findViewById(R.id.tvTime);

        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i  = new Intent(view.getContext(),ProfileActivity.class);
                i.putExtra("user", tweet.getUser());
                i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                view.getContext().startActivity(i);
            }
        });



        imageView.setImageResource(android.R.color.transparent);

        ImageLoader imageLoader = ImageLoader.getInstance();
        imageLoader.displayImage(tweet.getUser().getProfileImgUrl(), imageView);
        name.setText("@" + tweet.getUser().getName());
        body.setText(tweet.getBody());
        time.setText(tweet.getCreatedAt());
        return v;
    }
}
