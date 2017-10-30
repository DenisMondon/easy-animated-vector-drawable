package com.blunderer.easyanimatedvectordrawable;

import android.graphics.PorterDuff;
import android.graphics.drawable.Animatable;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.support.annotation.ColorInt;
import android.support.annotation.DrawableRes;
import android.support.annotation.NonNull;
import android.util.Log;
import android.widget.ImageView;

import com.wnafee.vector.compat.ResourcesCompat;

import java.util.List;
import java.util.Map;

public class EasyAnimatedVectorDrawable {

    private static final String TAG = EasyAnimatedVectorDrawable.class.getSimpleName();

    private static EasyAnimatedVectorDrawable instance;

    private AnimatedVectorDrawableConfig config;

    public static void setConfig(AnimatedVectorDrawableConfig config) {
        initialize();

        instance.config = config;
    }

    public static void setImageType(@NonNull ImageView imageView, @NonNull Enum<?> type) {
        setImageType(imageView, type, 0);
    }

    public static void setImageType(@NonNull ImageView imageView, @NonNull Enum<?> type, @ColorInt int tintColor) {
        initialize();

        if (imageView.getTag(R.id.eavd_current_type) == null || !(imageView.getTag(R.id.eavd_current_type) instanceof Enum<?>)) {
            setImageDrawable(imageView, getDefaultDrawableResId(type), type, tintColor);
            return;
        }

        final Enum<?> currentType = (Enum<?>) imageView.getTag(R.id.eavd_current_type);
        if (currentType == type) {
            // Both types are equals, do nothing.
            return;
        }

        try {
            int animatedVectorDrawable = getAnimatedVectorDrawable(currentType, type);
            if (animatedVectorDrawable != 0) {
                setImageDrawable(imageView, animatedVectorDrawable, type, tintColor);
                return;
            }
        } catch (IllegalArgumentException e) {
            e.printStackTrace();
        }

        setImageDrawable(imageView, getDefaultDrawableResId(type), type, tintColor);
    }

    private static void setImageDrawable(@NonNull ImageView imageView, @DrawableRes int drawableResId, @NonNull Enum<?> type, @ColorInt int tintColor) {
        if (drawableResId == 0) {
            Log.e(TAG, "The drawable for the " + type.name() + " type is not valid");
            return;
        }
        Drawable drawable = ResourcesCompat.getDrawable(imageView.getContext(), drawableResId);
        if (drawable == null) {
            Log.e(TAG, "The drawable with id " + drawableResId + " is null");
            return;
        }
        if (Build.VERSION.SDK_INT != Build.VERSION_CODES.M) {
            drawable = drawable.mutate();
        }
        if (tintColor != 0) {
            drawable.setColorFilter(tintColor, PorterDuff.Mode.SRC_IN);
        }

        imageView.setImageDrawable(drawable);
        imageView.setTag(R.id.eavd_current_type, type);

        if (drawable instanceof Animatable) {
            Animatable animatable = (Animatable) drawable;
            if (animatable.isRunning()) {
                animatable.stop();
            }
            animatable.start();
        }
    }

    @DrawableRes
    private static int getDefaultDrawableResId(Enum<?> type) {
        if (instance.config == null) {
            instance.config = generateDefaultConfig();
        }

        Map<Enum<?>, Integer> defaultDrawables = instance.config.getDefaultDrawables();
        if (defaultDrawables.containsKey(type)) {
            return defaultDrawables.get(type);
        }

        return 0;
    }

    @DrawableRes
    private static int getAnimatedVectorDrawable(Enum<?> currentType, Enum<?> newType) {
        List<Object[]> animatedVectorDrawables = getAnimatedVectorDrawables();
        for (Object[] animatedVectorDrawable : animatedVectorDrawables) {
            if (animatedVectorDrawable[0] == currentType && animatedVectorDrawable[1] == newType) {
                return (int) animatedVectorDrawable[2];
            }
        }

        throw new IllegalArgumentException("Unknown combination between " + currentType.name() + " and " + newType.name());
    }

    @NonNull
    private static List<Object[]> getAnimatedVectorDrawables() {
        if (instance.config == null) {
            instance.config = generateDefaultConfig();
        }

        return instance.config.getAnimatedVectorDrawables();
    }

    private static AnimatedVectorDrawableConfig generateDefaultConfig() {
        AnimatedVectorDrawableConfig.DrawableType playDrawableType = new AnimatedVectorDrawableConfig.DrawableType(Type.PLAY, R.drawable.eavd_vd_play);
        AnimatedVectorDrawableConfig.DrawableType pauseDrawableType = new AnimatedVectorDrawableConfig.DrawableType(Type.PAUSE, R.drawable.eavd_vd_pause);
        AnimatedVectorDrawableConfig.DrawableType stopDrawableType = new AnimatedVectorDrawableConfig.DrawableType(Type.STOP, R.drawable.eavd_vd_stop);

        return new AnimatedVectorDrawableConfig.Builder()
                .addAnimatedVectorDrawable(playDrawableType, pauseDrawableType, R.drawable.avd_play_to_pause)
                .addAnimatedVectorDrawable(playDrawableType, stopDrawableType, R.drawable.avd_play_to_stop)
                .addAnimatedVectorDrawable(pauseDrawableType, playDrawableType, R.drawable.avd_pause_to_play)
                .addAnimatedVectorDrawable(pauseDrawableType, stopDrawableType, R.drawable.avd_pause_to_stop)
                .addAnimatedVectorDrawable(stopDrawableType, playDrawableType, R.drawable.avd_stop_to_play)
                .addAnimatedVectorDrawable(stopDrawableType, pauseDrawableType, R.drawable.avd_stop_to_pause)
                .build();
    }

    private static void initialize() {
        if (instance == null) {
            synchronized (EasyAnimatedVectorDrawable.class) {
                if (instance == null) {
                    instance = new EasyAnimatedVectorDrawable();
                }
            }
        }
    }

    public enum Type {

        PLAY, PAUSE, STOP

    }

}
