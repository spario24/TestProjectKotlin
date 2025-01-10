package com.android.testprojectkotlin.widgets

import android.content.Context
import android.util.AttributeSet
import android.view.ViewGroup
import androidx.core.view.forEach
import com.android.testprojectkotlin.dpToPx
import com.google.android.material.internal.ViewUtils.dpToPx
import com.google.android.material.tabs.TabLayout
import kotlin.math.min

class ScalableTabLayout @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
): TabLayout(context, attrs, defStyleAttr) {

    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        val tabLayout = getChildAt(0) as ViewGroup
        val childCount = tabLayout.childCount
        if (childCount > 0) {
            var widthPixels = MeasureSpec.getSize(widthMeasureSpec)
            widthPixels -= 16 * childCount
            val tabMinWidth = widthPixels / childCount
            var remainderPixels = widthPixels % childCount
            tabLayout.forEach {
                if (remainderPixels > 0) {
                    it.minimumWidth = tabMinWidth + 1
                    remainderPixels--
                } else {
                    it.minimumWidth = tabMinWidth
                }
            }
        }
        super.onMeasure(widthMeasureSpec, heightMeasureSpec)
    }

}

class CustomTabLayout  @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
): TabLayout(context, attrs, defStyleAttr) {

    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        // If we have a MeasureSpec which allows us to decide our height, try and use the default
        // height
        var heightMeasureSpec = heightMeasureSpec
        val idealHeight = context.dpToPx(
            getDefaultSize(42, heightMeasureSpec)
        ).toInt()
        when (MeasureSpec.getMode(heightMeasureSpec)) {
            MeasureSpec.AT_MOST -> {
                if (childCount == 1 && MeasureSpec.getSize(
                        heightMeasureSpec
                    ) >= idealHeight
                ) {
                    getChildAt(0).minimumHeight = idealHeight
                }
                heightMeasureSpec = MeasureSpec.makeMeasureSpec(
                    min(
                        idealHeight,
                        MeasureSpec.getSize(heightMeasureSpec)
                    ),
                    MeasureSpec.EXACTLY
                )
            }
            MeasureSpec.UNSPECIFIED -> heightMeasureSpec = MeasureSpec.makeMeasureSpec(
                idealHeight + paddingTop + paddingBottom, MeasureSpec.EXACTLY
            )
            else -> {
            }
        }
        // Now super measure itself using the (possibly) modified height spec
        super.onMeasure(widthMeasureSpec, heightMeasureSpec)
    }
}

