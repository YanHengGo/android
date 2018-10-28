package com.yanheng.drawgraph;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.yanheng.drawgraph.graph.DrawViewDemo;
import com.yanheng.drawgraph.graph.data.GraphSettingData;

import java.util.HashMap;

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
        createSettingData();
        createGraphData();
        DrawViewDemo drawViewDemo = (DrawViewDemo) findViewById(R.id.draw_view);
        drawViewDemo.setGraphSettingData(graphSettingData);
        drawViewDemo.setGraph1(graphMail);
        drawViewDemo.setGraph2(graphFemail);
        drawViewDemo.invalidate();
    }

    private GraphSettingData graphSettingData = null;

    private void createSettingData() {
        graphSettingData = new GraphSettingData();
        graphSettingData.title = "身長グラフ";
        graphSettingData.graphNmae1="男の子";
        graphSettingData.graphNmae2="女の子";

        graphSettingData.axisDataX.maxScale = 18f;
        graphSettingData.axisDataX.minScale = 10f;
        graphSettingData.axisDataX.scaleValue = 1f;
        graphSettingData.axisDataX.scaleTitle = "年齢(歳)";

        graphSettingData.axisDataY.maxScale = 180f;
        graphSettingData.axisDataY.minScale = 120f;
        graphSettingData.axisDataY.scaleValue = 10f;
        graphSettingData.axisDataY.scaleTitle = "身長(cm)";
    }

    //男の子、身長年齢
    private HashMap<Float, Float> graphMail = new HashMap<>();
    //女の子、身長年齢
    private HashMap<Float, Float> graphFemail = new HashMap<>();

    private void createGraphData(){
        graphMail.put(10f, 138.9f);
        graphMail.put(11f, 145f);
        graphMail.put(12f, 152.4f);
        graphMail.put(13f, 159.5f);
        graphMail.put(14f, 165.1f);
        graphMail.put(15f, 168.4f);
        graphMail.put(16f, 169.8f);
        graphMail.put(17f, 170.7f);

        graphFemail.put(10f, 140.1f);
        graphFemail.put(11f, 146.7f);
        graphFemail.put(12f, 151.9f);
        graphFemail.put(13f, 155f);
        graphFemail.put(14f, 156.5f);
        graphFemail.put(15f, 157.2f);
        graphFemail.put(16f, 157.6f);
        graphFemail.put(17f, 158f);
    }

}
