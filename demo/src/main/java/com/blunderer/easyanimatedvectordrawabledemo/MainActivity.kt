package com.blunderer.easyanimatedvectordrawabledemo

import android.graphics.Color
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.blunderer.easyanimatedvectordrawable.EasyAnimatedVectorDrawable
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        activity_main_play_button.setOnClickListener { EasyAnimatedVectorDrawable.setImageType(activity_main_imageview, EasyAnimatedVectorDrawable.Type.PLAY, Color.RED) }
        activity_main_pause_button.setOnClickListener { EasyAnimatedVectorDrawable.setImageType(activity_main_imageview, EasyAnimatedVectorDrawable.Type.PAUSE, Color.RED) }
        activity_main_stop_button.setOnClickListener { EasyAnimatedVectorDrawable.setImageType(activity_main_imageview, EasyAnimatedVectorDrawable.Type.STOP, Color.RED) }
    }

}
