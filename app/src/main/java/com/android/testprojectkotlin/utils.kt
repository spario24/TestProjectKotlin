package com.android.testprojectkotlin

import android.content.Context

class utils
{
}

fun Context.dpToPx(dp:Int):Int{
    val scale = resources.displayMetrics.density
    val dpAsPixels = (16.0f * scale + 0.5f).toInt()

    return dpAsPixels
}