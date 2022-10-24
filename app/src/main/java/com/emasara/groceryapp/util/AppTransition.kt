package com.emasara.groceryapp.util

import android.graphics.Color
import android.view.animation.DecelerateInterpolator
import androidx.transition.Transition
import com.google.android.material.transition.MaterialContainerTransform

class AppTransition{
    companion object{
        fun getTransition(): Transition {
            return MaterialContainerTransform().apply {
                scrimColor = Color.TRANSPARENT
                containerColor = Color.TRANSPARENT
                duration = 500L
                interpolator = DecelerateInterpolator()
            }

        }
    }
}