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

        val animator1 = ObjectAnimator.ofFloat(subView, "TranslationY", -20F, 0F)
        val animator2 = ObjectAnimator.ofFloat(subView, "alpha", 0F, 1F)
        val animatorSet = AnimatorSet()
        animatorSet.playTogether(animator1, animator2)
        animatorSet.duration = 100
        animatorSet.start()
        subView.visibility = View.VISIBLE
    }

    fun goneSubView() {
        val animation = AnimationUtils.loadAnimation(context, R.anim.zoomtopout)
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

    override fun onInterceptTouchEvent(ev: MotionEvent?): Boolean {
        val action = ev?.getAction()
        if (action == MotionEvent.ACTION_DOWN) {
            isScroll = false
            touchDownY = ev.y.toInt()
        }
        val isUp = ( action == MotionEvent.ACTION_UP || action == MotionEvent.ACTION_CANCEL)
        if (isUp && isScroll && ev != null) {
            //判別処理追加
            val margin = touchDownY - ev.y.toInt()
            if (margin > 0) {
                onTapupGoneSubView()
            }
        }
        gestureDetector.onTouchEvent(ev)
        return super.onInterceptTouchEvent(ev)
    }

    var isScroll = false

    private var gestureDetector = GestureDetector(context,object : GestureDetector.SimpleOnGestureListener() {
        override fun onScroll(e1: MotionEvent?, e2: MotionEvent?, distanceX: Float, distanceY: Float): Boolean {
            L.d( "onScroll")
            if(e2==null){
                return true
            }
            val touchMoveY = e2.y.toInt()
            val margin = touchDownY.minus(touchMoveY)
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
        val alpha = 1F - margin.toFloat()/(firstPaddingTop.toFloat() * 5F)
        setPaddingTop()
        subView.alpha = alpha
    }

    private fun setPaddingTop() {
        subView.setPadding(subView.paddingLeft, currentPaddingTop, subView.paddingRight, subView.paddingBottom)
    }

    lateinit var subView: View
    var firstPaddingTop = 0
    var touchDownY = -1

    private fun onTapupGoneSubView() {
        val fromAlpha = currentPaddingTop / firstPaddingTop.toFloat()
        val animator1 = ObjectAnimator.ofFloat(subView, "TranslationY", 0F, -20F)
        val animator2 = ObjectAnimator.ofFloat(subView, "alpha", fromAlpha, 0F)
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
}