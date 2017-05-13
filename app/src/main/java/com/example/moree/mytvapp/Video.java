package com.example.moree.mytvapp;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.media.AudioManager;
import android.net.Uri;
import android.net.rtp.AudioCodec;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.SurfaceView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.MediaController;
import android.widget.Toast;
import android.widget.VideoView;

import com.devbrackets.android.exomedia.listener.OnErrorListener;
import com.devbrackets.android.exomedia.listener.OnPreparedListener;
import com.devbrackets.android.exomedia.ui.widget.EMVideoView;

import com.devbrackets.android.exomedia.listener.OnPreparedListener;
import com.devbrackets.android.exomedia.ui.widget.EMVideoView;

import static android.media.AudioManager.STREAM_MUSIC;

public class Video extends AppCompatActivity {
    EMVideoView video;
    Context context;
    AudioManager audio;

    // SimpleExoPlayer player;
    // ExoPlayerFactory exo;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_video);
        this.context = this;
        video = (EMVideoView) findViewById(R.id.video_view);
        uri();

    }


    public void uri() {
        try {
            Bundle b = getIntent().getExtras();
            video.getVideoControls().setVerticalFadingEdgeEnabled(true);
            video.getVideoControls().setHorizontalFadingEdgeEnabled(true);
            video.getVideoControls().setHorizontalGravity(100);
            video.setMeasureBasedOnAspectRatioEnabled(true);
            video.requestFocus();
            video.setVideoURI(Uri.parse(b.getString("link")));
            video.setOnPreparedListener(new OnPreparedListener() {
                @Override
                public void onPrepared() {


                    video.start();
                    video.getAvailableTracks();
                }
            });

        } catch (Exception e) {
            // TODO: handle exception
            e.printStackTrace();
        }
    }

}


