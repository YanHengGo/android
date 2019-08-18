package com.animation3.yanheng

import android.animation.Animator
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




class TopBrandingSearchConditionPickUpView @JvmOverloads constructor(
        context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0
) : BasePickUpView(context, attrs, defStyleAttr) {

    override fun getLayout(): Int {
        return R.layout.top_banding_search_condition_pickup_view
    }

    fun initView() {
        subView = findViewById(R.id.img_ball2)
        firstPaddingTop = subView.paddingTop
        subView.setOnClickListener {
            L.d("subView onclick")
        }
    }
}



