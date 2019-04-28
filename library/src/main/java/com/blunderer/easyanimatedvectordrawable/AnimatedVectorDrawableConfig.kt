package com.blunderer.easyanimatedvectordrawable

import androidx.annotation.DrawableRes

class AnimatedVectorDrawableConfig private constructor(builder: Builder) {

    val animatedVectorDrawables = builder.animatedVectorDrawables
    val defaultDrawables = builder.defaultDrawables

    class Builder {

        internal val animatedVectorDrawables = mutableListOf<Array<Any>>()
        internal val defaultDrawables = mutableMapOf<Enum<*>, Int>()

        fun addAnimatedVectorDrawable(drawableTypeFrom: DrawableType, drawableTypeTo: DrawableType, @DrawableRes animatedVectorDrawableResId: Int): Builder {
            animatedVectorDrawables.add(arrayOf(drawableTypeFrom.type, drawableTypeTo.type, animatedVectorDrawableResId))
            defaultDrawables[drawableTypeFrom.type] = drawableTypeFrom.defaultDrawableResId
            defaultDrawables[drawableTypeTo.type] = drawableTypeTo.defaultDrawableResId
            return this@Builder
        }

        fun build() = AnimatedVectorDrawableConfig(this)

    }

    class DrawableType(internal val type: Enum<*>, @param:DrawableRes @field:DrawableRes internal val defaultDrawableResId: Int)

}
