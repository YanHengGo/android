package com.animation4.yanheng;

import android.animation.ObjectAnimator;
import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

public class MainActivity extends Activity {

    private ImageView imageView;
    private Button btn_start;
    private Button btn_stop;
    private ObjectAnimator objectAnimator;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
    }

    private void initView() {
        imageView = (ImageView) findViewById(R.id.img_hert);
        boolean isHertTest = false;
        if(isHertTest){
            imageView.setImageResource(R.mipmap.hert);
            objectAnimator = AnimationUtil2.getHertAnimation(imageView);
        }else{
            imageView.setImageResource(R.mipmap.tellphone);
            objectAnimator = AnimationUtil2.getTellphoneAnimation(imageView);
        }

        btn_start = (Button) findViewById(R.id.btn_start);
        btn_start.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                objectAnimator.start();
            }
        });
        btn_stop = (Button) findViewById(R.id.btn_stop);
        btn_stop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                objectAnimator.cancel();
            }
        });
    }
}
