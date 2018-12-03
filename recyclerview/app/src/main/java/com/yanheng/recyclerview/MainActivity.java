package com.yanheng.recyclerview;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;

import com.yanheng.recyclerview.demo.ActivityDemo;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private List<String> lists = new ArrayList<>();
    {
        lists.add("a");
        lists.add("b");
        lists.add("c");
    }
    private RecycleDemoAdapter recycleDemoAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initRecycleView();
        initButton();
    }
    private int count = 0;
    private void initButton() {
        Button button = findViewById(R.id.button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                lists.add("new item"+count++);
                recycleDemoAdapter.update(lists);

            }
        });
        Button button1 = findViewById(R.id.button1);
        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setClass(MainActivity.this,ActivityDemo.class);
                startActivity(intent);
            }
        });
    }

    private void initRecycleView() {
        recyclerView = findViewById(R.id.recycleview);
        recycleDemoAdapter = new RecycleDemoAdapter(this,lists);

        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(layoutManager);

        recyclerView.setAdapter(recycleDemoAdapter);

    }
}
