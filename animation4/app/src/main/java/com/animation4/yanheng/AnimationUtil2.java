package com.animation4.yanheng;

import android.animation.Animator;
import android.animation.Keyframe;
import android.animation.ObjectAnimator;
import android.animation.PropertyValuesHolder;
import android.animation.ValueAnimator;
import android.view.animation.Animation;
import android.widget.ImageView;

public class AnimationUtil2 {
    public static ObjectAnimator getHertAnimation(ImageView img_hert) {
        PropertyValuesHolder alpha = PropertyValuesHolder.ofFloat("alpha" , 1.0f , 0.5f);
        PropertyValuesHolder scaleX = PropertyValuesHolder.ofFloat("scaleX" , 1.2f , 0.8f);
        PropertyValuesHolder scaleY = PropertyValuesHolder.ofFloat("scaleY" , 1.2f , 0.8f);

        ObjectAnimator objectAnimator = ObjectAnimator.ofPropertyValuesHolder(img_hert,alpha,scaleX,scaleY);
        objectAnimator.setDuration(2000);
        objectAnimator.setRepeatCount(Animation.INFINITE);
        objectAnimator.setRepeatMode(ValueAnimator.RESTART);
        return objectAnimator;
    }


    public static ObjectAnimator getTellphoneAnimation(ImageView imageView) {
        Keyframe frame0 = Keyframe.ofFloat(0f, 0);
        Keyframe frame1 = Keyframe.ofFloat(0.1f, -20f);
        Keyframe frame2 = Keyframe.ofFloat(0.2f, 20f);
        Keyframe frame3 = Keyframe.ofFloat(0.3f, -20f);
        Keyframe frame4 = Keyframe.ofFloat(0.4f, 20f);
        Keyframe frame5 = Keyframe.ofFloat(0.5f, -20f);
        Keyframe frame6 = Keyframe.ofFloat(0.6f, 20f);
        Keyframe frame7 = Keyframe.ofFloat(0.7f, -20f);
        Keyframe frame8 = Keyframe.ofFloat(0.8f, 20f);
        Keyframe frame9 = Keyframe.ofFloat(0.9f, -20f);
        Keyframe frame10 = Keyframe.ofFloat(1, 0);
        PropertyValuesHolder frameHolder = PropertyValuesHolder.ofKeyframe
                ("rotation",frame0,frame1,frame2,frame3,frame4,frame5,frame6,frame7,frame8,frame9,frame10);
        ObjectAnimator objectAnimator = ObjectAnimator.ofPropertyValuesHolder(imageView,frameHolder);
        objectAnimator.setDuration(1000);
        objectAnimator.setRepeatCount(2);
        objectAnimator.setRepeatMode(ValueAnimator.RESTART);
        return objectAnimator;
    }
}
