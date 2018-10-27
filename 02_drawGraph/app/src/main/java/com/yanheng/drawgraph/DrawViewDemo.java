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
    private HashMap<Integer,Float> graphMail = new HashMap<>();
    private HashMap<Integer,Float> graphFemail = new HashMap<>();
    {
        graphMail.put(10,138.9f);
        graphMail.put(11,145f);
        graphMail.put(12,152.4f);
        graphMail.put(13,159.5f);
        graphMail.put(14,165.1f);
        graphMail.put(15,168.4f);
        graphMail.put(16,169.8f);
        graphMail.put(17,170.7f);
    }
    //原点
    private final int ORIGIN_X = 50;
    private final int ORIGIN_Y = 150;
    //X軸　長さ
    private final int AXIS_X = 200;
    //X軸　点数
    private final int POINT_X = 10;

    //Y軸　長さ
    private final int AXIS_Y = 100;
    //Y軸　点数
    private final int POINT_Y = 4;

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

//        for (Map.Entry<Integer,Float> entry: graphMail.entrySet()) {
//
//        }
        int scaleY = AXIS_Y/POINT_Y;
        L.d("scaleY="+scaleY);
        for(int x = 1 ;x<=POINT_Y ;x++){
            canvas.drawLine(
                    ORIGIN_X*density,
                    (ORIGIN_Y-scaleY*x)*density,
                    (ORIGIN_X+5)*density,
                    (ORIGIN_Y-scaleY*x)*density,
                    getBaseLinePaint(context));
        }
        int scaleX = AXIS_X/POINT_X;
        L.d("scaleY="+scaleY);
        for(int x = 1 ;x<=POINT_X ;x++){
            canvas.drawLine(
                    (ORIGIN_X+scaleX*x)*density,
                    (ORIGIN_Y)*density,
                    (ORIGIN_X+scaleX*x)*density,
                    (ORIGIN_Y-5)*density,
                    getBaseLinePaint(context));
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
        return paint;
    }
}
