package com.blunderer.easyanimatedvectordrawable

import android.graphics.PorterDuff
import android.graphics.drawable.Animatable
import android.os.Build
import android.util.Log
import android.widget.ImageView
import androidx.annotation.ColorInt
import androidx.annotation.DrawableRes
import com.wnafee.vector.compat.ResourcesCompat

object EasyAnimatedVectorDrawable {

    private val TAG = EasyAnimatedVectorDrawable::class.java.simpleName

    var config: AnimatedVectorDrawableConfig? = null

    @JvmStatic
    @JvmOverloads
    fun setImageType(imageView: ImageView, type: Enum<*>, @ColorInt tintColor: Int = 0) {
        val currentTypeTag = imageView.getTag(R.id.eavd_current_type)
        if (currentTypeTag !is Enum<*>) {
            // If the image has no types, we set it.
            setImageDrawable(imageView, getDefaultDrawableResId(type), type, tintColor)
            return
        }

        val currentTintColor = imageView.getTag(R.id.eavd_current_tint_color)?.takeIf { it is Int } as? Int ?: 0
        if (currentTypeTag == type && currentTintColor == tintColor) {
            // Both types are equals, we do nothing.
            return
        }

        val newTintColor = tintColor.takeIf { it != 0 } ?: currentTintColor
        val animatedVectorDrawable = getAnimatedVectorDrawable(currentTypeTag, type) ?: 0
        if (animatedVectorDrawable != 0) {
            // We animate the new one from the one already set.
            setImageDrawable(imageView, animatedVectorDrawable, type, newTintColor)
            return
        }

        // Else, we just set the type without animation.
        setImageDrawable(imageView, getDefaultDrawableResId(type), type, newTintColor)
    }

    private fun setImageDrawable(imageView: ImageView, @DrawableRes drawableResId: Int, type: Enum<*>, @ColorInt tintColor: Int) {
        if (drawableResId == 0) {
            Log.e(TAG, "The drawable for the type \"" + type.name + "\" is not valid")
            return
        }
        var drawable = ResourcesCompat.getDrawable(imageView.context, drawableResId) ?: return
        if (Build.VERSION.SDK_INT != Build.VERSION_CODES.M) {
            drawable = drawable.mutate() ?: return
        }
        if (tintColor != 0) {
            drawable.setColorFilter(tintColor, PorterDuff.Mode.SRC_IN)
            imageView.setTag(R.id.eavd_current_tint_color, tintColor)
        }

        imageView.setImageDrawable(drawable)
        imageView.setTag(R.id.eavd_current_type, type)

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

        val leftArrowDrawableType = AnimatedVectorDrawableConfig.DrawableType(Type.LEFT_ARROW, R.drawable.eavd_vd_left_arrow)
        val upArrowDrawableType = AnimatedVectorDrawableConfig.DrawableType(Type.UP_ARROW, R.drawable.eavd_vd_up_arrow)
        val rightDrawableType = AnimatedVectorDrawableConfig.DrawableType(Type.RIGHT_ARROW, R.drawable.eavd_vd_right_arrow)
        val downDrawableType = AnimatedVectorDrawableConfig.DrawableType(Type.DOWN_ARROW, R.drawable.eavd_vd_down_arrow)

        return AnimatedVectorDrawableConfig.Builder()
                // Play, Pause, Stop
                .addAnimatedVectorDrawable(playDrawableType, pauseDrawableType, R.drawable.avd_play_to_pause)
                .addAnimatedVectorDrawable(playDrawableType, stopDrawableType, R.drawable.avd_play_to_stop)
                .addAnimatedVectorDrawable(pauseDrawableType, playDrawableType, R.drawable.avd_pause_to_play)
                .addAnimatedVectorDrawable(pauseDrawableType, stopDrawableType, R.drawable.avd_pause_to_stop)
                .addAnimatedVectorDrawable(stopDrawableType, playDrawableType, R.drawable.avd_stop_to_play)
                .addAnimatedVectorDrawable(stopDrawableType, pauseDrawableType, R.drawable.avd_stop_to_pause)

                // Left arrow, Top arrow, Right arrow, Bottom arrow
                .addAnimatedVectorDrawable(leftArrowDrawableType, upArrowDrawableType, R.drawable.avd_left_arrow_to_up_arrow)
                .addAnimatedVectorDrawable(leftArrowDrawableType, rightDrawableType, R.drawable.avd_left_arrow_to_right_arrow)
                .addAnimatedVectorDrawable(leftArrowDrawableType, downDrawableType, R.drawable.avd_left_arrow_to_down_arrow)
                .addAnimatedVectorDrawable(upArrowDrawableType, rightDrawableType, R.drawable.avd_up_arrow_to_right_arrow)
                .addAnimatedVectorDrawable(upArrowDrawableType, downDrawableType, R.drawable.avd_up_arrow_to_down_arrow)
                .addAnimatedVectorDrawable(upArrowDrawableType, leftArrowDrawableType, R.drawable.avd_up_arrow_to_left_arrow)
                .addAnimatedVectorDrawable(rightDrawableType, downDrawableType, R.drawable.avd_right_arrow_to_down_arrow)
                .addAnimatedVectorDrawable(rightDrawableType, leftArrowDrawableType, R.drawable.avd_right_arrow_to_left_arrow)
                .addAnimatedVectorDrawable(rightDrawableType, upArrowDrawableType, R.drawable.avd_right_arrow_to_up_arrow)
                .addAnimatedVectorDrawable(downDrawableType, leftArrowDrawableType, R.drawable.avd_down_arrow_to_left_arrow)
                .addAnimatedVectorDrawable(downDrawableType, upArrowDrawableType, R.drawable.avd_down_arrow_to_up_arrow)
                .addAnimatedVectorDrawable(downDrawableType, rightDrawableType, R.drawable.avd_down_arrow_to_right_arrow)
                .build()
    }

    enum class Type {

        PLAY, PAUSE, STOP,
        LEFT_ARROW, UP_ARROW, RIGHT_ARROW, DOWN_ARROW

    }

}
