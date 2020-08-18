package com.example.shoptify.common

import android.animation.ValueAnimator
import android.view.ViewGroup

class HelperExpandAnim {
  companion object {
    fun collapsedViewGroup(viewGroup: ViewGroup) {
      val anim = ValueAnimator.ofInt(viewGroup.measuredHeightAndState, 0)
      anim.addUpdateListener {
        val value = it.animatedValue

        val layoutParams = viewGroup.layoutParams
        layoutParams.height = value as Int
        viewGroup.layoutParams = layoutParams
      }
      anim.start()
    }

    fun expandViewGroup(viewGroup: ViewGroup, height: Int){
      val anim = ValueAnimator.ofInt(viewGroup.measuredHeightAndState, height)
      anim.addUpdateListener {
        val value = it.animatedValue

        val layoutParams = viewGroup.layoutParams
        layoutParams.height = value as Int
        viewGroup.layoutParams = layoutParams
      }
      anim.start()
    }
  }
}