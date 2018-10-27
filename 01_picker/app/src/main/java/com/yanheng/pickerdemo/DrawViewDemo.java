package com.yanheng.pickerdemo;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;

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

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        L.d();
        drawTopBottomLine(canvas);
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
    private void drawTopBottomLine(Canvas canvas) {
        canvas.drawLine(0, 10, 100, 10, getBaseLinePaint(context));
    }
}
