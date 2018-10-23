package com.yanheng.activity.myapplication;

import android.app.Activity;
import android.app.ActivityManager;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.view.View;
import android.widget.Button;

import java.util.List;

public class SubActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sub);
//        outputActivityInfor();
        initView();
    }
    private void initView() {
        Button start_main = findViewById(R.id.start_main);
        start_main.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setClass(SubActivity.this,MainActivity.class);
                startActivity(intent);
            }
        });
        Button start_sub_2 = findViewById(R.id.start_sub_2);
        start_sub_2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setClass(SubActivity.this,SubActivity2.class);
                startActivity(intent);
            }
        });
    }

    private void outputActivityInfor() {
        ActivityManager activityManager = (ActivityManager)getSystemService(Context.ACTIVITY_SERVICE);
        List<ActivityManager.RunningTaskInfo> runningTaskInfos = activityManager.getRunningTasks(10);
        for (ActivityManager.RunningTaskInfo runningTaskInfo : runningTaskInfos) {
            L.d("id="+runningTaskInfo.id);
            L.d("description="+runningTaskInfo.description);
            L.d("numActivities="+runningTaskInfo.numActivities);
            L.d("topActivity="+runningTaskInfo.topActivity);
            L.d("baseActivity="+runningTaskInfo.baseActivity.toString());
        }
    }
    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        L.d();
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        L.d();
    }

    @Override
    protected void onStart() {
        super.onStart();
        L.d();
    }

    @Override
    protected void onResume() {
        super.onResume();
        outputActivityInfor();
        L.d();
    }

    @Override
    protected void onPause() {
        super.onPause();
        L.d();
    }

    @Override
    protected void onStop() {
        super.onStop();
        L.d();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        L.d();
    }

}
