package com.blunderer.easyanimatedvectordrawable

import android.widget.ImageView
import androidx.annotation.ColorInt

fun ImageView.setImageType(type: Enum<*>, @ColorInt tintColor: Int = 0) {
    EasyAnimatedVectorDrawable.setImageType(this, type, tintColor)
}