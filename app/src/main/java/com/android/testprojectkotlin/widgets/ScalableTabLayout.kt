package com.android.testprojectkotlin.widgets

import android.content.Context
import android.util.AttributeSet
import android.view.ViewGroup
import androidx.core.view.forEach
import com.google.android.material.tabs.TabLayout

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