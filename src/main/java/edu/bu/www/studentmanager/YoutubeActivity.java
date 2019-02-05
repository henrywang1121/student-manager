package edu.bu.www.studentmanager;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.constraint.ConstraintLayout;
import android.view.View;
import android.widget.Button;

import com.google.android.youtube.player.YouTubeBaseActivity;
import com.google.android.youtube.player.YouTubeInitializationResult;
import com.google.android.youtube.player.YouTubePlayer;
import com.google.android.youtube.player.YouTubePlayerView;

import java.util.ArrayList;
import java.util.List;

public class YoutubeActivity extends YouTubeBaseActivity {

    private static final String youtubeAPIKey = "AIzaSyBHiDuNEHo_JJweeRDxb6xqtRhWOQ3DGtk";

    YouTubePlayerView youtubePlayerView;
    Button playButton;
    Button homeButton;
    YouTubePlayer.OnInitializedListener onInitializedListener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_youtube);

        //Set the color based on the preference chosen by the user
        ConstraintLayout layout = (ConstraintLayout) findViewById(R.id.youtubeLayout);
        SharedPreferences mySharedPreferences = getSharedPreferences("Background Color", MODE_PRIVATE);
        int selectedColor = mySharedPreferences.getInt("color", getResources().getColor(R.color.colorPrimary));
        if (selectedColor != getResources().getColor(R.color.colorPrimary)){
            layout.setBackgroundColor(selectedColor);
        }

        playButton = (Button) findViewById(R.id.playButton);
        youtubePlayerView = (YouTubePlayerView) findViewById(R.id.youtubeView);
        onInitializedListener = new YouTubePlayer.OnInitializedListener() {
            @Override
            public void onInitializationSuccess(YouTubePlayer.Provider provider, YouTubePlayer youTubePlayer, boolean b) {

                List<String> playlist = new ArrayList<>();
                playlist.add("1l18jnBYgWw");
                playlist.add("g1e11lsrSvw");
                playlist.add("-yMS-z--1zE");
                playlist.add("MgvbrBzOIps");
                playlist.add("XrWxzvP2Udg");
                playlist.add("j1I8d1QkYZ8");
                playlist.add("2nfYTyUnfM0");
                playlist.add("8jroFeT3pyM");
                youTubePlayer.loadVideos(playlist);
            }

            @Override
            public void onInitializationFailure(YouTubePlayer.Provider provider, YouTubeInitializationResult youTubeInitializationResult) {

            }
        };

        playButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                youtubePlayerView.initialize(youtubeAPIKey, onInitializedListener);
            }
        });

        homeButton = (Button) findViewById(R.id.youtubeHomeButton);
        homeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(YoutubeActivity.this, HomeActivity.class);
                startActivity(intent);
            }
        });
    }
}
