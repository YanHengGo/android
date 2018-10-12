package com.yanheng.myapplication;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.AnimationSet;
import android.view.animation.RotateAnimation;
import android.view.animation.ScaleAnimation;
import android.view.animation.TranslateAnimation;
import android.widget.Button;
import android.widget.ImageView;

public class MainActivity extends Activity {

    //
    private TranslateAnimation translateAnimation;
    {
        //Animation.RELATIVE_TO_PARENT　　親のViewに対して
        //Animation.RELATIVE_TO_SELF      自分のViewに対して
        translateAnimation = new TranslateAnimation(
                Animation.RELATIVE_TO_PARENT,0, //"X"開始位置のタイプとValue
                Animation.RELATIVE_TO_PARENT,0.3f, //"X"終了位置のタイプとValue
                Animation.RELATIVE_TO_PARENT,0, // "Y"開始位置のタイプとValue　
                Animation.RELATIVE_TO_PARENT,0.3f //　"Y"終了位置のタイプとValue
        );
        //アニメーションの時間
        translateAnimation.setDuration(500);
        //アニメーションの回数数字または　Animation.INFINITE（無限回）
        //translateAnimation.setRepeatCount(Animation.INFINITE);
        translateAnimation.setRepeatCount(3);
        //アニメーション停止したら最後の位置に停止する
        translateAnimation.setFillAfter(true);
        //アニメーションのMode
        translateAnimation.setRepeatMode(Animation.REVERSE);
    }

    private RotateAnimation rotateAnimation;
    {
        rotateAnimation = new RotateAnimation(
                0,360,                  //開始と終了度数
                Animation.RELATIVE_TO_SELF,0.5f, //回転の中心作業X
                Animation.RELATIVE_TO_SELF ,0.5f //回転の中心作業Y
        );
        //回転回数
        rotateAnimation.setRepeatCount(3);
        //回転時間
        rotateAnimation.setDuration(500);
    }

    private AlphaAnimation alphaAnimation;
    {
        alphaAnimation = new AlphaAnimation(
                1,
                0
        );
        alphaAnimation.setDuration(500);
        //消えた後表示しない
        //alphaAnimation.setFillAfter(true);
        //消えた後再表示
        //alphaAnimation.setFillAfter(false);
        alphaAnimation.setRepeatMode(Animation.REVERSE);
        alphaAnimation.setRepeatCount(3);
    }
    private ScaleAnimation scaleAnimation;
    {
        scaleAnimation = new ScaleAnimation(
                1f,3.0f,                                 //Xの開始大きさ
                1f,3.0f,                                 //Yの開始大きさ
                Animation.RELATIVE_TO_SELF,0.5f,       //開始位置の位置X
                Animation.RELATIVE_TO_SELF,0.5f        //開始位置の位置Y
        );
        scaleAnimation.setDuration(500);
        scaleAnimation.setRepeatCount(3);
        scaleAnimation.setRepeatMode(Animation.REVERSE);
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


//        testSingleAnimation();
        testMultipleAnimation();
    }
    /**
     * ---------　複数のアニメーションテスト
     */
    private void testMultipleAnimation() {
        setContentView(R.layout.multiple_animation);
        initMultipleAnimation();
    }
    private ImageView imageView1;
    private ImageView imageView2;
    private ImageView imageView3;
    private void initMultipleAnimation() {
        imageView1 = (ImageView)findViewById(R.id.imageview1);
        imageView2 = (ImageView)findViewById(R.id.imageview2);
        imageView3 = (ImageView)findViewById(R.id.imageview3);
        initAnimation1();
        initAnimation2();
        initAnimation3();
    }

    private AnimationSet animationSet1;
    private void initAnimation1() {
        animationSet1 = new AnimationSet(false);
        animationSet1.addAnimation(alphaAnimation);

        Button button_start = (Button)findViewById(R.id.button_start);
        button_start.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                imageView1.startAnimation(animationSet1);
            }
        });
        animationSet1.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {

            }

            @Override
            public void onAnimationEnd(Animation animation) {
                L.d("animationSet1 完了");
                imageView2.startAnimation(animationSet2);
            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });
    }
    private AnimationSet animationSet2;
    private void initAnimation2() {
        imageView2 = (ImageView)findViewById(R.id.imageview2);
        animationSet2 = new AnimationSet(false);
        animationSet2.addAnimation(rotateAnimation);
        animationSet2.addAnimation(translateAnimation);
        animationSet2.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {

            }

            @Override
            public void onAnimationEnd(Animation animation) {
                L.d("animationSet2 完了");
                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                imageView3.startAnimation(animationSet3);
            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });
    }
    private AnimationSet animationSet3;
    private void initAnimation3() {
        imageView3 = (ImageView)findViewById(R.id.imageview3);
        animationSet3 = new AnimationSet(false);
        animationSet3.addAnimation(scaleAnimation);
        animationSet3.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {

            }

            @Override
            public void onAnimationEnd(Animation animation) {
                L.d("animationSet3 完了");
                imageView1.startAnimation(animationSet1);
            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });
    }



    /**
     * ---------　一つのアニメーションテスト
     */
    private void testSingleAnimation() {
        setContentView(R.layout.activity_main);
        initView();
    }

    private ImageView imageView;
    private void initView() {
        imageView = (ImageView)findViewById(R.id.imageview);

        //回転アニメーションのテスト
        Button button_translation = (Button)findViewById(R.id.button_translation);
        button_translation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //imageViewにアニメーションの開始
                imageView.startAnimation(translateAnimation);
            }
        });

        //回転アニメーションテスト
        Button button_rotate = (Button)findViewById(R.id.button_rotate);
        button_rotate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //回転開始
                imageView.startAnimation(rotateAnimation);
            }
        });

        //消えるアニメーションテスト
        Button button_alpha = (Button)findViewById(R.id.button_alpha);
        button_alpha.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                imageView.startAnimation(alphaAnimation);
            }
        });

        //大きさアニメーションテスト
        Button button_scale = (Button)findViewById(R.id.button_scale);
        button_scale.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                imageView.startAnimation(scaleAnimation);

            }
        });

    }
}
