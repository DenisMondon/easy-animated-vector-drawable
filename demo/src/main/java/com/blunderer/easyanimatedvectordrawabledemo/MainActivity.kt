package com.blunderer.easyanimatedvectordrawabledemo

import android.graphics.Color
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.blunderer.easyanimatedvectordrawable.EasyAnimatedVectorDrawable
import com.blunderer.easyanimatedvectordrawable.setImageType
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        activity_main_play_button.setOnClickListener { activity_main_imageview.setImageType(EasyAnimatedVectorDrawable.Type.PLAY, Color.RED) }
        activity_main_pause_button.setOnClickListener { activity_main_imageview.setImageType(EasyAnimatedVectorDrawable.Type.PAUSE, Color.RED) }
        activity_main_stop_button.setOnClickListener { activity_main_imageview.setImageType(EasyAnimatedVectorDrawable.Type.STOP, Color.RED) }

        activity_main_left_arrow_button.setOnClickListener { activity_main_imageview.setImageType(EasyAnimatedVectorDrawable.Type.LEFT_ARROW, Color.BLUE) }
        activity_main_up_arrow_button.setOnClickListener { activity_main_imageview.setImageType(EasyAnimatedVectorDrawable.Type.UP_ARROW, Color.RED) }
        activity_main_right_arrow_button.setOnClickListener { activity_main_imageview.setImageType(EasyAnimatedVectorDrawable.Type.RIGHT_ARROW) }
        activity_main_down_arrow_button.setOnClickListener { activity_main_imageview.setImageType(EasyAnimatedVectorDrawable.Type.DOWN_ARROW, Color.RED) }
    }

}
