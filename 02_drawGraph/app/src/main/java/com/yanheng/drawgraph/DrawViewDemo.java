package com.yanheng.drawgraph;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;

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
        this.context =context;
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
    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        this.density = context.getResources().getDisplayMetrics().density;
        //タイトル
        String title = "タイトル";
        //縦軸名
        //横軸名
        //縦軸（線とスケール）
        drawTopBottomLine(canvas);

        //横軸（線とスケール）
        //グラフ


        L.d();
    }
    //男の子、身長年齢
    private HashMap<Float,Float> graphMail = new HashMap<>();
    private HashMap<Float,Float> graphFemail = new HashMap<>();
    {
        graphMail.put(10f,138.9f);
        graphMail.put(11f,145f);
        graphMail.put(12f,152.4f);
        graphMail.put(13f,159.5f);
        graphMail.put(14f,165.1f);
        graphMail.put(15f,168.4f);
        graphMail.put(16f,169.8f);
        graphMail.put(17f,170.7f);
    }
    //原点
    private final float ORIGIN_X = 50f;
    private final float ORIGIN_Y = 150f;
    //X軸　長さ
    private final float AXIS_X = 200f;
    //Y軸　長さ
    private final float AXIS_Y = 100f;


    private float scaleValueY = 10f;
    private float minScaleY = 120f;
    private float maxScaleY = 180f;

    private float scaleValueX = 1f;
    private float minScaleX = 10f;
    private float maxScaleX = 18f;

    private void drawTopBottomLine(Canvas canvas) {

        canvas.drawLine(
                ORIGIN_X*density,
                ORIGIN_Y*density,
                ORIGIN_X*density,
                (ORIGIN_Y-AXIS_Y)*density,
                getBaseLinePaint(context));
        canvas.drawLine(
                ORIGIN_X*density,
                ORIGIN_Y*density,
                (ORIGIN_X+AXIS_X)*density,
                ORIGIN_Y*density,
                getBaseLinePaint(context));

        //scale の数
        float scaleCountY = (maxScaleY-minScaleY)/scaleValueY;
        //いちscaleはグラフ上のサイズ
        float scaleGraphValueY = AXIS_Y/scaleCountY;
        for(int x = 1 ;x<=scaleCountY ;x++){
            float startX = ORIGIN_X*density;
            float startY = (ORIGIN_Y-scaleGraphValueY*x)*density;
            canvas.drawLine(startX,startY,
                    (startX+5*density),startY,
                    getBaseLinePaint(context));
            canvas.drawText(String.valueOf((int)minScaleY+x*(int)scaleValueY),startX-30*density,startY+8*density,getBaseLinePaint(context));
        }
        //scale の数
        float scaleCountX = (maxScaleX-minScaleX)/scaleValueX;
        //いちscaleはグラフ上のサイズ
        float scaleGraphValueX = AXIS_X/scaleCountX;
        for(int x = 0 ;x<=scaleCountX ;x++){
            float startX = (ORIGIN_X+scaleGraphValueX*x)*density;
            float startY = (ORIGIN_Y)*density;
            canvas.drawLine(
                    startX,
                    startY,
                    startX,
                    (startY-5*density),
                    getBaseLinePaint(context));
            canvas.drawText(String.valueOf((int)minScaleX+x*(int)scaleValueX),startX-8*density,startY+14*density,getBaseLinePaint(context));
        }

        L.d("ORIGIN_X = "+ORIGIN_X);

        //グラフ
        for (Map.Entry<Float,Float> entry: graphMail.entrySet()) {
            L.d("-------------"+"start");
            L.d(String.valueOf((entry.getValue()-minScaleY)/scaleValueY));
            float x = (ORIGIN_X + (entry.getKey()-minScaleX)/scaleValueX*scaleGraphValueX)*density;
            float y =  (ORIGIN_Y - (entry.getValue()-minScaleY)/scaleValueY*scaleGraphValueY)*density;
            L.d("x="+x+"   y="+y);
            L.d("-------------"+"end");
            canvas.drawPoint(x,y,getCirclePaint());
        }
    }

    /**
     * Dpサイズをpxサイズに変換する
     * @param context コンテキスト
     * @param dp DP単位でのサイズ
     * @return px単位でのサイズ
     */
    public static int convDp2Px(Context context, int dp) {
        float density = context.getResources().getDisplayMetrics().density;
        return (int) (dp * density);
    }
    public static final int BASE_LINE_COLOR = 0xFFFE85A0;
    private static final int LINE_WIDTH = 1;
    public static Paint getBaseLinePaint(Context context) {
        Paint paint = new Paint();
        paint.setColor(BASE_LINE_COLOR);
        paint.setStrokeWidth(convDp2Px(context, LINE_WIDTH));
        paint.setStyle(Paint.Style.STROKE);
        paint.setAntiAlias(true);
        paint.setTextSize(50);
        return paint;
    }

    private static final int CIRCLE_COLOR = 0xFFb56f7f;
    /**
     * プロットサークル描画用のPaint取得
     * @return Paint
     */
    public static Paint getCirclePaint() {
        Paint paint = new Paint();
        paint.setColor(CIRCLE_COLOR);
        paint.setStyle(Paint.Style.FILL);
        paint.setAntiAlias(true);
        paint.setStrokeWidth(20);
        return paint;
    }

}
