package com.animation3.yanheng

import android.animation.Animator
import android.animation.AnimatorSet
import android.animation.ObjectAnimator
import android.content.Context
import android.util.AttributeSet
import android.util.Log
import android.view.GestureDetector
import android.view.LayoutInflater
import android.view.MotionEvent
import android.view.View
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import android.view.animation.ScaleAnimation
import android.view.animation.TranslateAnimation
import android.widget.LinearLayout

abstract class BasePickUpView :LinearLayout{
    constructor(context: Context) : this(context, null)
    constructor(context: Context, attributeSet: AttributeSet?) : this(context, attributeSet, 0)
    constructor(context: Context, attributeSet: AttributeSet?, defStyleAttr: Int) : super(
            context,
            attributeSet,
            defStyleAttr
    ) {
        LayoutInflater.from(context).inflate(getLayout(), this)
    }

    abstract fun getLayout() : Int


    lateinit var listener: PickupListener
    fun showSubView() {
        subView.alpha = 1F
        currentPaddingTop = firstPaddingTop
        setPaddingTop()

        var animator1 = ObjectAnimator.ofFloat(subView, "TranslationY", -20F, 0F)
        var animator2 = ObjectAnimator.ofFloat(subView, "alpha", 0F, 1F)
        val animatorSet2 = AnimatorSet()
        animatorSet2.playTogether(animator1, animator2)
        animatorSet2.duration = 100
        animatorSet2.start()
        subView.visibility = View.VISIBLE
    }

    fun goneSubView() {
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
        subView.startAnimation(animation)
    }

    fun initView() {
        subView = findViewById(R.id.img_ball2)
        firstPaddingTop = subView.paddingTop
        initAnimationSet()
        subView.setOnClickListener {
            L.d("subView onclick")
        }
    }


    override fun onInterceptTouchEvent(ev: MotionEvent?): Boolean {
        val action = ev?.getAction()
        if (action == MotionEvent.ACTION_DOWN) {
            isScroll = false
            touchDownY = ev.y.toInt()
        }
        var isUp = ( action == MotionEvent.ACTION_UP || action == MotionEvent.ACTION_CANCEL)
        if (isUp && isScroll && ev != null) {
            //判別処理追加
            var margin = touchDownY - ev.y.toInt()
            if (margin < 0) {
                return true
            }
            var paddingTop = firstPaddingTop - margin
            var alpha = paddingTop.toFloat() / firstPaddingTop.toFloat()
            goneAnimation()
        }
        gestureDetector.onTouchEvent(ev)
        return super.onInterceptTouchEvent(ev)
    }

    var isScroll = false

    var gestureDetector = GestureDetector(context,object : GestureDetector.SimpleOnGestureListener() {
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
    })
    var currentPaddingTop = 0

    private fun onTouchMove(margin: Int) {
        Log.d("gentest", "onTouchMov　${margin}")
        if (margin <= 0) {
            return
        }
        currentPaddingTop = firstPaddingTop - margin
        var alpha = 1F - margin.toFloat()/(firstPaddingTop.toFloat() * 5F)
        setPaddingTop()
        subView.alpha = alpha
    }

    private fun setPaddingTop() {
        subView.setPadding(subView.paddingLeft, currentPaddingTop, subView.paddingRight, subView.paddingBottom)
    }

    lateinit var subView: View
    var firstPaddingTop = 0
    var touchDownY = -1

    private fun goneAnimation() {
        var fromAlpha = currentPaddingTop / firstPaddingTop.toFloat()

        var fromTranslationY = currentPaddingTop.toFloat() - firstPaddingTop.toFloat()

        var animator1 = ObjectAnimator.ofFloat(subView, "TranslationY", 0F, -20F)
        var animator2 = ObjectAnimator.ofFloat(subView, "alpha", fromAlpha, 0F)
        val animatorSet2 = AnimatorSet()
        animatorSet2.playTogether(animator1, animator2)
        animatorSet2.duration = 200
        animatorSet2.addListener(object : Animator.AnimatorListener {
            override fun onAnimationEnd(animation: Animator?) {
                listener.onTextViewGone()
            }

            override fun onAnimationCancel(animation: Animator?) {
            }

            override fun onAnimationRepeat(animation: Animator?) {
            }

            override fun onAnimationStart(animation: Animator?) {
            }
        })
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


}