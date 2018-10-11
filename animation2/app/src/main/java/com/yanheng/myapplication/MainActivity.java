package com.yanheng.myapplication;

import android.app.Activity;
import android.os.PersistableBundle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.RotateAnimation;
import android.view.animation.ScaleAnimation;
import android.view.animation.TranslateAnimation;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.QuickContactBadge;

public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
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
                //Animation.RELATIVE_TO_PARENT　　親のViewに対して
                //Animation.RELATIVE_TO_SELF      自分のViewに対して
                TranslateAnimation translateAnimation = new TranslateAnimation(
                        Animation.RELATIVE_TO_PARENT,0, //"X"開始位置のタイプとValue
                        Animation.RELATIVE_TO_PARENT,0.3f, //"X"終了位置のタイプとValue
                        Animation.RELATIVE_TO_PARENT,0, // "Y"開始位置のタイプとValue　
                        Animation.RELATIVE_TO_PARENT,0.3f //　"Y"終了位置のタイプとValue
                );
                //アニメーションの時間
                translateAnimation.setDuration(2000);
                //アニメーションの回数数字または　Animation.INFINITE（無限回）
//                translateAnimation.setRepeatCount(Animation.INFINITE);
                translateAnimation.setRepeatCount(4);
                //アニメーション停止したら最後の位置に停止する
                translateAnimation.setFillAfter(true);
                //アニメーションのMode
                translateAnimation.setRepeatMode(Animation.REVERSE);
                //imageViewにアニメーションの開始
                imageView.startAnimation(translateAnimation);
            }
        });

        //回転アニメーションテスト
        Button button_rotate = (Button)findViewById(R.id.button_rotate);
        button_rotate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                L.d();
                RotateAnimation rotateAnimation = new RotateAnimation(
                        0,360,                  //開始と終了度数
                        Animation.RELATIVE_TO_SELF,0.5f, //回転の中心作業X
                        Animation.RELATIVE_TO_SELF ,0.5f //回転の中心作業Y
                );
                //回転回数
                rotateAnimation.setRepeatCount(5);
                //回転時間
                rotateAnimation.setDuration(1000);
                //回転開始
                imageView.startAnimation(rotateAnimation);
            }
        });

        //回転アニメーションテスト
        Button button_alpha = (Button)findViewById(R.id.button_alpha);
        button_alpha.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                L.d();
                AlphaAnimation alphaAnimation = new AlphaAnimation(
                        1,
                        0
                );
                alphaAnimation.setDuration(2000);
                //消えた後表示しない
//                alphaAnimation.setFillAfter(true);
                //消えた後再表示
//                alphaAnimation.setFillAfter(false);
                alphaAnimation.setRepeatMode(Animation.REVERSE);
                alphaAnimation.setRepeatCount(5);
                imageView.startAnimation(alphaAnimation);
            }
        });

        //回転アニメーションテスト
        Button button_scale = (Button)findViewById(R.id.button_scale);
        button_scale.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                L.d();
                ScaleAnimation scaleAnimation = new ScaleAnimation(
                        1f,3.0f,                                 //Xの開始大きさ
                        1f,3.0f,                                 //Yの開始大きさ
                        Animation.RELATIVE_TO_SELF,0.5f,       //開始位置の位置X
                        Animation.RELATIVE_TO_SELF,0.5f        //開始位置の位置Y
                );
                scaleAnimation.setDuration(2000);
                scaleAnimation.setRepeatCount(5);
                scaleAnimation.setRepeatMode(Animation.REVERSE);
                imageView.startAnimation(scaleAnimation);

            }
        });

    }
}
