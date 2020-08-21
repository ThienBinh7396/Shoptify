package com.example.shoptify.ui.custom

import android.content.Context
import android.content.res.Resources
import android.util.AttributeSet
import android.util.Log
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.RelativeLayout
import com.example.shoptify.R
import com.example.shoptify.common.SCALE_TYPE

class ParallaxView @JvmOverloads constructor(
  context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0
) : RelativeLayout(context, attrs, defStyleAttr) {
  init {
    val displayMetrics = Resources.getSystem().displayMetrics
    val imvBackground = ImageView(context)

    val imvBackgroundLayoutParams = ViewGroup.LayoutParams(displayMetrics.widthPixels, displayMetrics.heightPixels)
    imvBackground.layoutParams = imvBackgroundLayoutParams

    imvBackground.scaleType = ImageView.ScaleType.CENTER_CROP

    imvBackground.setImageResource(R.drawable.exclusive_block)

    addView(imvBackground)

    Log.d("Binh", "Layout: $layoutParams")

    addOnLayoutChangeListener { v, l, t, r, b, lW, tW, rW, bW ->
      run {
        val widthW = rW - lW
        val heightW = bW - tW

        requestLayout()

//        layoutParams.height = findViewById<View>(R.id.parallax_children).height

        Log.d("Binh", "LayoutXXX: $layoutParams")

        Log.d("Binh", "${findViewById<View>(R.id.parallax_children).height/ SCALE_TYPE}")

        Log.d("Binh", "Children: $childCount ${height/SCALE_TYPE}")
      }
    }
  }
}