package com.android.testprojectkotlin.widgets

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.util.AttributeSet
import android.view.ViewGroup
import android.widget.FrameLayout
import android.widget.ImageView
import com.android.testprojectkotlin.R
import com.bumptech.glide.Glide

class CustomAvatar @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : FrameLayout(context, attrs, defStyleAttr) {

    private val paint = Paint().apply {
        color = Color.RED
        isAntiAlias = true
    }
    init {


    }

    fun addImage(url: String) {
        val image = ImageView(context)
        image.apply {
            layoutParams = ViewGroup.LayoutParams(
                ViewGroup.LayoutParams.WRAP_CONTENT,
                ViewGroup.LayoutParams.WRAP_CONTENT,
            )
            setPadding(0, 0, 0, 0)
        }
        addView(image)
//        image.setBackgroundColor(R.drawable.cicle_border)

        Glide.with(context)
            .load(url)
            .fitCenter()
            .circleCrop()
            .into(image)
    }

    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec)
        val size = Math.min(measuredWidth, measuredHeight)
        setMeasuredDimension(size, size)
    }

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)
        val radius = (width / 2).toFloat()
        canvas.drawCircle(radius, radius, radius, paint)
    }
}