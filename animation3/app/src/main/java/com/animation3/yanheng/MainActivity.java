package com.animation3.yanheng;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationSet;
import android.view.animation.BounceInterpolator;
import android.view.animation.ScaleAnimation;
import android.view.animation.TranslateAnimation;
import android.widget.Button;
import android.widget.ImageView;

public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
    }

    private ImageView img_ball;
    private Button btn_start;
    private Button btn_stop;
    private AnimationSet animationSet = new AnimationSet(true);

    private void initView() {
        img_ball = (ImageView) findViewById(R.id.img_ball);
        btn_start = (Button) findViewById(R.id.btn_start);
        btn_start.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                img_ball.startAnimation(animationSet);
            }
        });
        btn_stop = (Button) findViewById(R.id.btn_stop);
        btn_stop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                scaleAnimation.cancel();
                translateAnimation.cancel();
            }
        });
        //ボール跳ね返す効果
        animationSet.setInterpolator(new BounceInterpolator());
        //アニメーション設定
        animationSet.addAnimation(scaleAnimation);
        animationSet.addAnimation(translateAnimation);
    }

    //時間
    private final int DURATION_TIME = 2000;
    //回数
    private final int REPEAT_COUNT = Animation.INFINITE;
    //２回以降の開始位置、
    //RESTART：初回の開始位置
    //REVERSE：終了位置
    private final int REPEAT_MODE = Animation.RESTART; //REVERSE RESTART
    //大きさアニメーション
    private ScaleAnimation scaleAnimation;

    {
        scaleAnimation = new ScaleAnimation(
                1f, 0.5f,                                 //Xの開始大きさ
                1f, 0.5f,                                 //Yの開始大きさ
                Animation.RELATIVE_TO_SELF, 0.5f,       //開始位置の位置X
                Animation.RELATIVE_TO_SELF, 0.5f        //開始位置の位置Y
        );
        scaleAnimation.setDuration(DURATION_TIME);
        scaleAnimation.setRepeatCount(REPEAT_COUNT);
        scaleAnimation.setRepeatMode(REPEAT_MODE);
    }

    //移動アニメーション初期化
    private TranslateAnimation translateAnimation;

    {
        //Animation.RELATIVE_TO_PARENT　　親のViewに対して
        //Animation.RELATIVE_TO_SELF      自分のViewに対して
        translateAnimation = new TranslateAnimation(
                Animation.RELATIVE_TO_PARENT, 0, //"X"開始位置のタイプとValue
                Animation.RELATIVE_TO_PARENT, 0f, //"X"終了位置のタイプとValue
                Animation.RELATIVE_TO_PARENT, 0, // "Y"開始位置のタイプとValue　
                Animation.RELATIVE_TO_PARENT, 0.9f //　"Y"終了位置のタイプとValue
        );
        //アニメーションの時間
        translateAnimation.setDuration(DURATION_TIME);
        //アニメーションの回数数字または　Animation.INFINITE（無限回）
        translateAnimation.setRepeatCount(REPEAT_COUNT);
        //アニメーションのMode
        translateAnimation.setRepeatMode(REPEAT_MODE);
    }

}
