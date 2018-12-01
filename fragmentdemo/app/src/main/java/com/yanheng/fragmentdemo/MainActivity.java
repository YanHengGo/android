package com.yanheng.fragmentdemo;

import android.app.Activity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import com.yanheng.fragmentdemo.fragment.FragmentA;
import com.yanheng.fragmentdemo.fragment.FragmentB;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        L.d();
        initView();
        listStringSort();
    }

    private void initView() {
        L.d();

        Button buttonA = (Button) findViewById(R.id.btna);
        buttonA.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                L.d();
                final FragmentManager fragmentManager = getSupportFragmentManager();
                final FragmentTransaction fragmentTransaction =  fragmentManager.beginTransaction();
                fragmentTransaction.replace(R.id.fragment_layout , new FragmentA());
                fragmentTransaction.commit();
                L.d();
            }
        });
        Button buttonB = (Button)findViewById(R.id.btnb);
        buttonB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final FragmentManager fragmentManager = getSupportFragmentManager();
                final FragmentTransaction fragmentTransaction =  fragmentManager.beginTransaction();
                fragmentTransaction.replace(R.id.fragment_layout , new FragmentB());
                fragmentTransaction.commit();
            }
        });
    }

    public void listStringSort() {
        List<String> stringList = new ArrayList<String>();
        stringList.add("eipJlcx");
        stringList.add("WvQRufC");
        stringList.add("J");
        stringList.add("HdaU2G");
        stringList.add("M0WswHD3");
        L.d("------------排序前-------------");
        for (String string : stringList) {
            L.d("元素：" + string);
        }
        Collections.sort(stringList);
        L.d("--------------排序后---------------");
        for (String string : stringList) {
            L.d("元素：" + string);
        }
    }

}
