package com.animation4.yanheng;

import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.RotateAnimation;
import android.view.animation.ScaleAnimation;
import android.view.animation.TranslateAnimation;

public class AnimationUtil {
    /**
     * 以下のクラスはすべて　android.view.animation.Animation　を継承している
     * <p>
     * android.view.animation.AlphaAnimation : 消えるアニメーション初期化
     * android.view.animation.RotateAnimation : 回転アニメーション初期化
     * android.view.animation.ScaleAnimation : 大きさアニメーション
     * android.view.animation.TranslateAnimation : 移動アニメーション初期化
     */


    //時間
    private static final int DURATION_TIME = 2000;
    //回数
    private static final int REPEAT_COUNT = Animation.INFINITE;
    //２回以降の開始位置、
    //RESTART：初回の開始位置
    //REVERSE：終了位置
    private static final int REPEAT_MODE = Animation.RESTART; //REVERSE RESTART

    //大きさアニメーション
    public static ScaleAnimation getScalAnimation() {
        return getScaleAnimation(DURATION_TIME, REPEAT_COUNT, REPEAT_MODE);
    }

    //大きさアニメーション
    private static ScaleAnimation getScaleAnimation(int time, int count, int mode) {
        ScaleAnimation scaleAnimation = new ScaleAnimation(
                1f, 3.0f,                                 //Xの開始大きさ
                1f, 3.0f,                                 //Yの開始大きさ
                Animation.RELATIVE_TO_SELF, 0.5f,       //開始位置の位置X
                Animation.RELATIVE_TO_SELF, 0.5f        //開始位置の位置Y
        );
        scaleAnimation.setDuration(time);
        scaleAnimation.setRepeatCount(count);
        scaleAnimation.setRepeatMode(mode);
        return scaleAnimation;
    }

    //移動アニメーション初期化
    public static TranslateAnimation getTranslateAnimation() {
        return getTranslateAnimation(DURATION_TIME, REPEAT_COUNT, REPEAT_MODE);
    }

    private static TranslateAnimation getTranslateAnimation(int time, int count, int mode) {
        //Animation.RELATIVE_TO_PARENT　　親のViewに対して
        //Animation.RELATIVE_TO_SELF      自分のViewに対して
        TranslateAnimation translateAnimation = new TranslateAnimation(
                Animation.RELATIVE_TO_PARENT, 0, //"X"開始位置のタイプとValue
                Animation.RELATIVE_TO_PARENT, 0.3f, //"X"終了位置のタイプとValue
                Animation.RELATIVE_TO_PARENT, 0, // "Y"開始位置のタイプとValue　
                Animation.RELATIVE_TO_PARENT, 0.3f //　"Y"終了位置のタイプとValue
        );
        //アニメーションの時間
        translateAnimation.setDuration(time);
        //アニメーションの回数数字または　Animation.INFINITE（無限回）
        //translateAnimation.setRepeatCount(Animation.INFINITE);
        translateAnimation.setRepeatCount(count);
        //アニメーションのMode
        translateAnimation.setRepeatMode(mode);
        return translateAnimation;
    }

    //回転アニメーション初期化
    public RotateAnimation getRotateAnimation() {
        return getRotateAnimation(DURATION_TIME, REPEAT_COUNT, REPEAT_MODE);
    }

    private RotateAnimation getRotateAnimation(int time, int count, int mode) {
        RotateAnimation rotateAnimation = new RotateAnimation(
                0, 360,                  //開始と終了度数
                Animation.RELATIVE_TO_SELF, 0.5f, //回転の中心作業X
                Animation.RELATIVE_TO_SELF, 0.5f //回転の中心作業Y
        );
        //回転時間
        rotateAnimation.setDuration(time);
        //回転回数
        rotateAnimation.setRepeatCount(count);
        //アニメーションのMode
        rotateAnimation.setRepeatMode(mode);
        return rotateAnimation;
    }

    //消えるアニメーション初期化
    public static AlphaAnimation getAlphaAnimation() {
        return getAlphaAnimation(DURATION_TIME, REPEAT_COUNT, REPEAT_MODE);
    }

    private static AlphaAnimation getAlphaAnimation(int time, int count, int mode) {
        AlphaAnimation alphaAnimation = new AlphaAnimation(
                1,
                0
        );
        alphaAnimation.setDuration(time);
        alphaAnimation.setRepeatCount(count);
        alphaAnimation.setRepeatMode(mode);
        return alphaAnimation;
    }


}
