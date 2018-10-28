package com.yanheng.drawgraph;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.yanheng.drawgraph.graph.DrawViewDemo;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initView();
    }

    private void initView() {
        Button button = findViewById(R.id.button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setGraph();
            }
        });
    }

    private void setGraph() {
        DrawViewDemo drawViewDemo = (DrawViewDemo)findViewById(R.id.draw_view);
//        drawViewDemo.
    }
}
