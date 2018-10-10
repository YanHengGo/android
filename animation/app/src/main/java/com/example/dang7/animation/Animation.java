package com.example.dang7.animation;

import android.app.Activity;
import android.graphics.drawable.AnimationDrawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

public class Animation extends Activity {
    private ImageView animationImage;
    AnimationDrawable animationDrawable;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_animation);

        //ImageView　初期化
        animationImage = (ImageView)findViewById(R.id.image);
        //AnimationDrawable　初期化
        animationDrawable = (AnimationDrawable)getResources().getDrawable(R.drawable.animation,null);
        //animationImage　背景
        animationImage.setBackground(animationDrawable);
    }

    public void startAnimation(View view) {
        animationDrawable.start();
    }

    public void stopAnimation(View view) {
        if(animationDrawable.isRunning()){
            animationDrawable.stop();
        }
    }




}
