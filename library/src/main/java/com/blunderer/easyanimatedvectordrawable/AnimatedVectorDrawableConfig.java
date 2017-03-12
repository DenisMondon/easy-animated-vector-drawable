package com.blunderer.easyanimatedvectordrawable;

import android.support.annotation.DrawableRes;
import android.support.annotation.NonNull;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class AnimatedVectorDrawableConfig {

    private List<Object[]> animatedVectorDrawables;
    private Map<Enum<?>, Integer> defaultDrawables;

    private AnimatedVectorDrawableConfig(Builder builder) {
        this.animatedVectorDrawables = builder.animatedVectorDrawables;
        this.defaultDrawables = builder.defaultDrawables;
    }

    @NonNull
    public List<Object[]> getAnimatedVectorDrawables() {
        return animatedVectorDrawables;
    }

    @NonNull
    public Map<Enum<?>, Integer> getDefaultDrawables() {
        return defaultDrawables;
    }

    public static class Builder {

        private List<Object[]> animatedVectorDrawables;
        private Map<Enum<?>, Integer> defaultDrawables;

        public Builder() {
            this.animatedVectorDrawables = new ArrayList<>();
            this.defaultDrawables = new HashMap<>();
        }

        @NonNull
        public Builder addAnimatedVectorDrawable(@NonNull DrawableType drawableTypeFrom, @NonNull DrawableType drawableTypeTo, @DrawableRes int animatedVectorDrawableResId) {
            animatedVectorDrawables.add(new Object[]{drawableTypeFrom.type, drawableTypeTo.type, animatedVectorDrawableResId});
            defaultDrawables.put(drawableTypeFrom.type, drawableTypeFrom.defaultDrawableResId);
            defaultDrawables.put(drawableTypeTo.type, drawableTypeTo.defaultDrawableResId);
            return this;
        }

        public AnimatedVectorDrawableConfig build() {
            return new AnimatedVectorDrawableConfig(this);
        }

    }

    public static class DrawableType {

        private Enum<?> type;
        @DrawableRes
        private int defaultDrawableResId;

        public DrawableType(Enum<?> type, @DrawableRes int defaultDrawableResId) {
            this.type = type;
            this.defaultDrawableResId = defaultDrawableResId;
        }

    }

}
