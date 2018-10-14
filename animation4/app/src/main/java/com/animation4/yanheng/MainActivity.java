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
    private Button btn_heart;
    private ObjectAnimator objectAnimator;
    private Button btn_tellphone;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
    }

    private void initView() {
        imageView = (ImageView) findViewById(R.id.imageView);
        imageView.setImageResource(R.mipmap.heart);
        objectAnimator = AnimationUtil2.getHertAnimation(imageView);

        btn_start = (Button) findViewById(R.id.btn_start);
        btn_start.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                objectAnimator.start();
            }
        });
        btn_heart = (Button) findViewById(R.id.btn_heart);
        btn_heart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                objectAnimator.cancel();
                imageView.setImageResource(R.mipmap.heart);
                objectAnimator = AnimationUtil2.getHertAnimation(imageView);
            }
        });
        btn_tellphone = (Button) findViewById(R.id.btn_tellphone);
        btn_tellphone.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                objectAnimator.cancel();
                imageView.setImageResource(R.mipmap.tellphone);
                objectAnimator = AnimationUtil2.getTellphoneAnimation(imageView);
            }
        });

    }

}
