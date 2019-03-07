package com.blunderer.easyanimatedvectordrawable

import android.graphics.PorterDuff
import android.graphics.drawable.Animatable
import android.os.Build
import android.support.annotation.ColorInt
import android.support.annotation.DrawableRes
import android.util.Log
import android.widget.ImageView
import com.wnafee.vector.compat.ResourcesCompat

object EasyAnimatedVectorDrawable {

    private val TAG = EasyAnimatedVectorDrawable::class.java.simpleName

    var config: AnimatedVectorDrawableConfig? = null

    @JvmOverloads
    fun setImageType(imageView: ImageView, type: Enum<*>, @ColorInt tintColor: Int = 0) {
        val currentTypeTag = imageView.getTag(R.id.eavd_current_type)
        if (currentTypeTag !is Enum<*>) {
            setImageDrawable(imageView, getDefaultDrawableResId(type), type, tintColor)
            return
        }

        val currentTintColorObject = imageView.getTag(R.id.eavd_current_tint_color)
        val currentTintColor = if (currentTintColorObject !is Int) 0 else currentTintColorObject
        if (currentTypeTag == type && currentTintColor == tintColor) {
            // Both types are equals, do nothing.
            return
        }

        val animatedVectorDrawable = getAnimatedVectorDrawable(currentTypeTag, type) ?: return
        if (animatedVectorDrawable != 0) {
            setImageDrawable(imageView, animatedVectorDrawable, type, tintColor)
            return
        }

        setImageDrawable(imageView, getDefaultDrawableResId(type), type, tintColor)
    }

    private fun setImageDrawable(imageView: ImageView, @DrawableRes drawableResId: Int, type: Enum<*>, @ColorInt tintColor: Int) {
        if (drawableResId == 0) {
            Log.e(TAG, "The drawable for the " + type.name + " type is not valid")
            return
        }
        var drawable = ResourcesCompat.getDrawable(imageView.context, drawableResId) ?: return
        if (Build.VERSION.SDK_INT != Build.VERSION_CODES.M) {
            drawable = drawable.mutate() ?: return
        }
        if (tintColor != 0) {
            drawable.setColorFilter(tintColor, PorterDuff.Mode.SRC_IN)
        }

        imageView.setImageDrawable(drawable)
        imageView.setTag(R.id.eavd_current_type, type)
        imageView.setTag(R.id.eavd_current_tint_color, tintColor)

        if (drawable is Animatable) {
            with(drawable as Animatable) {
                if (isRunning) {
                    stop()
                }
                start()
            }
        }
    }

    @DrawableRes
    private fun getDefaultDrawableResId(type: Enum<*>): Int {
        val safeConfig = config ?: generateDefaultConfig().also { config = it }
        return safeConfig.defaultDrawables[type] ?: 0
    }

    @DrawableRes
    private fun getAnimatedVectorDrawable(currentType: Enum<*>, newType: Enum<*>): Int? {
        for (animatedVectorDrawable in getAnimatedVectorDrawables()) {
            if (animatedVectorDrawable[0] == currentType && animatedVectorDrawable[1] == newType) {
                return animatedVectorDrawable[2] as Int
            }
        }
        return null
    }

    private fun getAnimatedVectorDrawables(): List<Array<Any>> {
        val safeConfig = config ?: generateDefaultConfig().also { config = it }
        return safeConfig.animatedVectorDrawables
    }

    private fun generateDefaultConfig(): AnimatedVectorDrawableConfig {
        val playDrawableType = AnimatedVectorDrawableConfig.DrawableType(Type.PLAY, R.drawable.eavd_vd_play)
        val pauseDrawableType = AnimatedVectorDrawableConfig.DrawableType(Type.PAUSE, R.drawable.eavd_vd_pause)
        val stopDrawableType = AnimatedVectorDrawableConfig.DrawableType(Type.STOP, R.drawable.eavd_vd_stop)

        return AnimatedVectorDrawableConfig.Builder()
                .addAnimatedVectorDrawable(playDrawableType, pauseDrawableType, R.drawable.avd_play_to_pause)
                .addAnimatedVectorDrawable(playDrawableType, stopDrawableType, R.drawable.avd_play_to_stop)
                .addAnimatedVectorDrawable(pauseDrawableType, playDrawableType, R.drawable.avd_pause_to_play)
                .addAnimatedVectorDrawable(pauseDrawableType, stopDrawableType, R.drawable.avd_pause_to_stop)
                .addAnimatedVectorDrawable(stopDrawableType, playDrawableType, R.drawable.avd_stop_to_play)
                .addAnimatedVectorDrawable(stopDrawableType, pauseDrawableType, R.drawable.avd_stop_to_pause)
                .build()
    }

    enum class Type {

        PLAY, PAUSE, STOP

    }

}
