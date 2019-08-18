package com.animation3.yanheng

import android.content.Context
import android.util.AttributeSet

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
