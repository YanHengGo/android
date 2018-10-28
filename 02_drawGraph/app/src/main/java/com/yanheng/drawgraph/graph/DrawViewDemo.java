package com.yanheng.drawgraph.graph;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Typeface;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.TextureView;
import android.view.View;

import com.yanheng.drawgraph.graph.data.GraphSettingData;
import com.yanheng.drawgraph.util.L;

import java.util.HashMap;
import java.util.Map;

public class DrawViewDemo extends View {
    public DrawViewDemo(Context context) {
        super(context);
        L.d();
        init(context);
    }

    public DrawViewDemo(Context context, AttributeSet attrs) {
        super(context, attrs);
        L.d();
        init(context);
    }

    public DrawViewDemo(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        L.d();
        init(context);
    }

    public DrawViewDemo(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        L.d();
        init(context);
    }

    private Context context;

    private void init(Context context) {
        this.context = context;
    }


    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        L.d();
    }

    @Override
    protected void onLayout(boolean changed, int left, int top, int right, int bottom) {
        super.onLayout(changed, left, top, right, bottom);
        L.d();
    }

    private float density;
    //原点
    private final float ORIGIN_X = 50f;
    private final float ORIGIN_Y = 150f;
    //X軸　長さ
    private final float AXIS_X = 200f;
    //Y軸　長さ
    private final float AXIS_Y = 100f;
    //X軸、Y軸など設定用のデータ
    private GraphSettingData graphSettingData = null;

    public void setGraphSettingData(GraphSettingData graphSettingData) {
        this.graphSettingData = graphSettingData;
    }

    //グラフ１データ
    private HashMap<Float, Float> graph1 = null;

    public void setGraph1(HashMap<Float, Float> graph1) {
        this.graph1 = graph1;
    }

    //グラフ2データ
    private HashMap<Float, Float> graph2 = null;

    public void setGraph2(HashMap<Float, Float> graph2) {
        this.graph2 = graph2;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        if (graphSettingData == null || graph1 == null) {
            return;
        }
        this.density = context.getResources().getDisplayMetrics().density;
        //scale の数
        float scaleCountX = (graphSettingData.axisDataX.maxScale - graphSettingData.axisDataX.minScale) / graphSettingData.axisDataX.scaleValue;
        //いちscaleはグラフ上のサイズ
        float scaleGraphValueX = AXIS_X / scaleCountX;
        //scale の数
        float scaleCountY = (graphSettingData.axisDataY.maxScale - graphSettingData.axisDataY.minScale) / graphSettingData.axisDataY.scaleValue;
        //いちscaleはグラフ上のサイズ
        float scaleGraphValueY = AXIS_Y / scaleCountY;

        //タイトル
        drawTitle(canvas);
        //縦軸
        drawAxisY(canvas, scaleCountY, scaleGraphValueY);
        //横軸
        drawAxisX(canvas, scaleCountX, scaleGraphValueX);
        //グラフ
        drawGraph(canvas, scaleGraphValueX, scaleGraphValueY, graph1,graphSettingData.graphNmae1);
        drawGraph(canvas, scaleGraphValueX, scaleGraphValueY, graph2,graphSettingData.graphNmae2);
        L.d();
    }

    private void drawTitle(Canvas canvas) {
        canvas.drawText(graphSettingData.title, (ORIGIN_X + 100) * density, (ORIGIN_Y - 120) * density, getLabelPaint(context));
    }

    private void drawAxisY(Canvas canvas, float scaleCountY, float scaleGraphValueY) {
        //X軸
        canvas.drawLine(
                ORIGIN_X * density,
                ORIGIN_Y * density,
                ORIGIN_X * density,
                (ORIGIN_Y - AXIS_Y) * density,
                getLinePaint(context));
        //Y軸目盛と下に表示する数字
        for (int x = 1; x <= scaleCountY; x++) {
            float startX = ORIGIN_X * density;
            float startY = (ORIGIN_Y - scaleGraphValueY * x) * density;
            canvas.drawLine(startX, startY,
//                    (startX+5*density),startY,
                    (startX + AXIS_X * density), startY,
                    getLinePaint(context));
            canvas.drawText(String.valueOf((int) graphSettingData.axisDataY.minScale + x * (int) graphSettingData.axisDataY.scaleValue), startX - 30 * density, startY + 8 * density, getLabelPaint(context));
        }
        //Y軸名
        drawText(canvas, graphSettingData.axisDataY.scaleTitle, (ORIGIN_X - 35) * density, (ORIGIN_Y - 50) * density, getLabelPaint(context), -90);
    }

    private void drawAxisX(Canvas canvas, float scaleCountX, float scaleGraphValueX) {
        //Y軸
        canvas.drawLine(
                ORIGIN_X * density,
                ORIGIN_Y * density,
                (ORIGIN_X + AXIS_X) * density,
                ORIGIN_Y * density,
                getLinePaint(context));
        //X軸メモリと下に表示する数字
        for (int x = 0; x <= scaleCountX; x++) {
            float startX = (ORIGIN_X + scaleGraphValueX * x) * density;
            float startY = (ORIGIN_Y) * density;
            canvas.drawLine(
                    startX,
                    startY,
                    startX,
                    (startY - 5 * density),
                    getLinePaint(context));
            canvas.drawText(String.valueOf((int) graphSettingData.axisDataX.minScale + x * (int) graphSettingData.axisDataX.scaleValue), startX - 8 * density, startY + 14 * density, getLabelPaint(context));
        }
        //X軸名
        canvas.drawText(graphSettingData.axisDataX.scaleTitle, (ORIGIN_X + 50) * density, (ORIGIN_Y + 30) * density, getLabelPaint(context));
    }

    /**
     * グラフ表がする
     *
     * @param canvas
     * @param scaleGraphValueX
     * @param scaleGraphValueY
     * @param graph
     */
    private void drawGraph(Canvas canvas, float scaleGraphValueX, float scaleGraphValueY, HashMap<Float, Float> graph,String graphName) {
        //グラフ1
        if (graph == null) return;
        float previousX = -1, previousY = -1, currentX = 0, currentY = 0;
        for (Map.Entry<Float, Float> entry : graph.entrySet()) {
            L.d(String.valueOf((entry.getValue() - graphSettingData.axisDataY.minScale) / graphSettingData.axisDataY.scaleValue));
            currentX = (ORIGIN_X + (entry.getKey() - graphSettingData.axisDataX.minScale) / graphSettingData.axisDataX.scaleValue * scaleGraphValueX) * density;
            currentY = (ORIGIN_Y - (entry.getValue() - graphSettingData.axisDataY.minScale) / graphSettingData.axisDataY.scaleValue * scaleGraphValueY) * density;
            canvas.drawPoint(currentX, currentY, getCirclePaint());
            if (!(previousX == -1 && previousY == -1)) {
                canvas.drawLine(previousX, previousY, currentX, currentY, getGraphPaint(context));
            }
            previousX = currentX;
            previousY = currentY;
        }
        if(!TextUtils.isEmpty(graphName)){
            canvas.drawText(graphName, currentX+30*density, currentY, getLabelPaint(context));
        }
    }


    private int convDp2Px(Context context, int dp) {
        float density = context.getResources().getDisplayMetrics().density;
        return (int) (dp * density);
    }

    private Paint getLinePaint(Context context) {
        Paint paint = new Paint();
        paint.setColor(0xFF000000);
        paint.setStrokeWidth(convDp2Px(context, 1));
        paint.setStyle(Paint.Style.STROKE);
        paint.setAntiAlias(true);
        paint.setTextSize(50);
        return paint;
    }
    private Paint getGraphPaint(Context context) {
        Paint paint = new Paint();
        paint.setColor(0xFF60D7FF);
        paint.setStrokeWidth(convDp2Px(context, 1));
        paint.setStyle(Paint.Style.STROKE);
        paint.setAntiAlias(true);
        paint.setTextSize(50);
        return paint;
    }


    private Paint getCirclePaint() {
        Paint paint = new Paint();
        paint.setColor(0xFFb56f7f);
        paint.setStyle(Paint.Style.FILL);
        paint.setAntiAlias(true);
        paint.setStrokeWidth(20);
        return paint;
    }

    private Paint getLabelPaint(Context context) {
        Paint paint = new Paint();
        paint.setColor(0xFF400030);
        paint.setTextSize(convDp2Px(context, 12));
        paint.setStyle(Paint.Style.FILL);
        paint.setTextAlign(Paint.Align.CENTER);
        paint.setTypeface(Typeface.DEFAULT);
        paint.setAntiAlias(true);
        return paint;
    }

    private void drawText(Canvas canvas, String text, float x, float y, Paint paint, float angle) {
        if (angle != 0) {
            canvas.rotate(angle, x, y);
        }
        canvas.drawText(text, x, y, paint);
        if (angle != 0) {
            canvas.rotate(-angle, x, y);
        }
    }
}
