package com.animation3.yanheng

import android.animation.AnimatorSet
import android.animation.ObjectAnimator
import android.content.Context
import android.util.AttributeSet
import android.util.Log
import android.view.GestureDetector
import android.view.GestureDetector.SimpleOnGestureListener
import android.view.LayoutInflater
import android.view.MotionEvent
import android.view.View
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import android.view.animation.ScaleAnimation
import android.view.animation.TranslateAnimation
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.Toast




class TopBrandingSearchConditionPickUpView2 : LinearLayout {
    constructor(context: Context) : this(context, null)
    constructor(context: Context, attributeSet: AttributeSet?) : this(context, attributeSet, 0)
    constructor(context: Context, attributeSet: AttributeSet?, defStyleAttr: Int) : super(
            context,
            attributeSet,
            defStyleAttr
    ) {
        LayoutInflater.from(context).inflate(R.layout.top_banding_search_condition_pickup_view, this)
    }

    init {

    }

    fun showPickUpViewWithAnimation() {
        var animation = AnimationUtils.loadAnimation(context, R.anim.alpha_anim)
        animation.setAnimationListener(object : Animation.AnimationListener {
            override fun onAnimationStart(animation: Animation?) {
            }

            override fun onAnimationEnd(animation: Animation?) {
            }

            override fun onAnimationRepeat(animation: Animation?) {
            }
        })
//        var textView = findViewById<TextView>(R.id.text_view)
//        textView.visibility = View.VISIBLE
//        textView.startAnimation(animation)
    }


    override fun dispatchTouchEvent(ev: MotionEvent?): Boolean {
//        val action = ev?.getAction()
//        Log.d("gentest", "start")
//
//        if (action == MotionEvent.ACTION_DOWN) {
//            Log.d("gentest", "ACTION_DOWN")
//            touchDownY = ev.y.toInt()
//        }
//        if (action == MotionEvent.ACTION_MOVE) {
//            Log.d("gentest", "ACTION_MOVE")
//            var margin = touchDownY - ev.y.toInt()
//            onTouchMove(margin)
//        }
//        if (action == MotionEvent.ACTION_UP || action == MotionEvent.ACTION_CANCEL) {
//            Log.d("gentest", "ACTION_UP")
//            //判別処理追加
////            showSubView();
////            listener.onTextViewGone()
//            var margin = touchDownY - ev.y.toInt()
//            if (margin < 0) {
//                return true
//            }
//            var paddingTop = firstPaddingTop - margin
//            var alpha = paddingTop.toFloat() / firstPaddingTop.toFloat()
//            goneAnimation()
//        }
//        return if(isScroll){
//            true
//        }else{
//            super.dispatchTouchEvent(ev)
//        }
        return super.dispatchTouchEvent(ev)
    }

    override fun onInterceptTouchEvent(ev: MotionEvent?): Boolean {
        val action = ev?.getAction()
        if (action == MotionEvent.ACTION_DOWN) {
            isScroll = false
            touchDownY = ev.y.toInt()
        }
        L.d()
        gestureDetector.onTouchEvent(ev)
        return super.onInterceptTouchEvent(ev)
    }

    var isScroll = false

    var gestureDetector = GestureDetector(context,object : SimpleOnGestureListener() {
        override fun onScroll(e1: MotionEvent?, e2: MotionEvent?, distanceX: Float, distanceY: Float): Boolean {
            L.d( "onScroll")
            if(e2==null){
                return true
            }
            val touchMoveY = e2.y.toInt()
            var margin = touchDownY.minus(touchMoveY)
            onTouchMove(margin)
            isScroll = true
            return true
        }

        override fun onFling(e1: MotionEvent, e2: MotionEvent, velocityX: Float, velocityY: Float): Boolean {

            L.d( "onFling")

//            Toast.makeText(context, "onFling", Toast.LENGTH_LONG).show()

            return true

        }
    })




    fun showTextView() {
        img_ball2.alpha = 1F
        currentPaddingTop = firstPaddingTop
        setPaddingTop()

        var animator1 = ObjectAnimator.ofFloat(img_ball2, "TranslationY", -20F, 0F)
        var animator2 = ObjectAnimator.ofFloat(img_ball2, "alpha", 0F, 1F)
        val animatorSet2 = AnimatorSet()
        animatorSet2.playTogether(animator1, animator2)
        animatorSet2.duration = 100
        animatorSet2.start()
        img_ball2.visibility = View.VISIBLE
    }

    var currentPaddingTop = 0

    private fun onTouchMove(margin: Int) {
        Log.d("gentest", "onTouchMov　${margin}")
        if (margin <= 0) {
            return
        }
        currentPaddingTop = firstPaddingTop - margin
        var alpha = currentPaddingTop.toFloat() / firstPaddingTop.toFloat()
        setPaddingTop()
        img_ball2.alpha = alpha
    }

    private fun setPaddingTop() {
        img_ball2.setPadding(img_ball2.paddingLeft, currentPaddingTop, img_ball2.paddingRight, img_ball2.paddingBottom)
    }

    lateinit var img_ball2: ImageView
    var firstPaddingTop = 0
    var touchDownY = -1
    lateinit var listener: PickupListener

    fun initView() {
        L.d("initview")
        img_ball2 = findViewById(R.id.img_ball2)
        firstPaddingTop = img_ball2.paddingTop
        initAnimationSet()
        img_ball2.setOnClickListener {
            L.d("img_ball2 onclick")
        }

    }

    fun goneAnimation() {
        var fromAlpha = currentPaddingTop / firstPaddingTop.toFloat()

        var fromTranslationY = currentPaddingTop.toFloat() - firstPaddingTop.toFloat()

        var animator1 = ObjectAnimator.ofFloat(img_ball2, "TranslationY", 0F, -20F)
        var animator2 = ObjectAnimator.ofFloat(img_ball2, "alpha", fromAlpha, 0F)
        val animatorSet2 = AnimatorSet()
        animatorSet2.playTogether(animator1, animator2)
        animatorSet2.duration = 200
        animatorSet2.start()
    }

    private fun initAnimationSet() {
        scaleAnimation.duration = 300

        translateAnimation.duration = 300
    }

    private var scaleAnimation: ScaleAnimation = ScaleAnimation(
            1f, 0.5f, //Xの開始大きさ
            1f, 0.5f, //Yの開始大きさ
            Animation.RELATIVE_TO_SELF, 0.5f, //開始位置の位置X
            Animation.RELATIVE_TO_SELF, 0.5f        //開始位置の位置Y
    )

    //移動アニメーション初期化
    private var translateAnimation = TranslateAnimation(
            Animation.RELATIVE_TO_PARENT, 0F, //"X"開始位置のタイプとValue
            Animation.RELATIVE_TO_PARENT, 0F, //"X"終了位置のタイプとValue
            Animation.RELATIVE_TO_PARENT, 0F, // "Y"開始位置のタイプとValue　
            Animation.RELATIVE_TO_PARENT, 0.9F //　"Y"終了位置のタイプとValue
    );


    fun goneTextView() {
        var animation = AnimationUtils.loadAnimation(context, R.anim.zoomtopout)
        animation.setAnimationListener(object : Animation.AnimationListener {
            override fun onAnimationEnd(animation: Animation?) {
                listener.onTextViewGone()
            }

            override fun onAnimationRepeat(animation: Animation?) {
            }

            override fun onAnimationStart(animation: Animation?) {
            }
        })
        img_ball2.startAnimation(animation)

    }

}



