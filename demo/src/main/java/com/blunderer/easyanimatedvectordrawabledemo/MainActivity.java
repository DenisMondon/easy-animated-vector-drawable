package com.blunderer.easyanimatedvectordrawabledemo;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.AppCompatImageView;
import android.view.View;
import android.widget.Button;

import com.blunderer.easyanimatedvectordrawable.EasyAnimatedVectorDrawable;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button playButton = findViewById(R.id.activity_main_play_button);
        Button pauseButton = findViewById(R.id.activity_main_pause_button);
        Button stopButton = findViewById(R.id.activity_main_stop_button);

        final AppCompatImageView imageView = findViewById(R.id.activity_main_imageview);

        playButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                EasyAnimatedVectorDrawable.setImageType(imageView, EasyAnimatedVectorDrawable.Type.PLAY, Color.RED);
            }

        });
        pauseButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                EasyAnimatedVectorDrawable.setImageType(imageView, EasyAnimatedVectorDrawable.Type.PAUSE, Color.RED);
            }

        });
        stopButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                EasyAnimatedVectorDrawable.setImageType(imageView, EasyAnimatedVectorDrawable.Type.STOP, Color.RED);
            }

        });
    }

}
