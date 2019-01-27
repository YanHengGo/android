package com.yanheng.rxjava;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.yanheng.rxjava.androidrx.RxUtils;
import com.yanheng.rxjava.javarx.Test;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

    }

    @Override
    protected void onResume() {
        super.onResume();
//        Test test = new Test();
//        test.test();
        RxUtils.createObserable();
    }
}
