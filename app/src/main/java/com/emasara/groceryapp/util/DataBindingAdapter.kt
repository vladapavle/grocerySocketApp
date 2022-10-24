package com.emasara.groceryapp.util

import android.R
import android.graphics.Color
import android.graphics.drawable.GradientDrawable
import android.widget.ImageView
import androidx.core.content.ContextCompat
import androidx.databinding.BindingAdapter

@BindingAdapter("android:circleImage")
fun setImageDrawable(circleView: ImageView, color: String) {
    circleView.setImageDrawable(
        drawCircle(
            color,
            ContextCompat.getColor(circleView.context, R.color.black)
        )
    )
}

private fun drawCircle(backgroundColor: String, borderColor: Int): GradientDrawable? {
    val shape = GradientDrawable()
    shape.shape = GradientDrawable.OVAL
    shape.cornerRadii = floatArrayOf(0f, 0f, 0f, 0f, 0f, 0f, 0f, 0f)
    shape.setStroke(2, borderColor)
    shape.setColor(Color.parseColor(backgroundColor))
    return shape
}