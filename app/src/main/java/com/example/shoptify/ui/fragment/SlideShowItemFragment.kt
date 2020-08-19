package com.example.shoptify.ui.fragment

import android.graphics.Paint
import android.os.Build
import android.os.Bundle
import android.text.Html
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import com.example.shoptify.common.HelperData
import com.example.shoptify.GlideApp
import com.example.shoptify.R
import com.example.shoptify.common.SlideCaption

class SlideShowItemFragment(private val positionSlide: Int) : Fragment() {
  private var ivBackground: ImageView? = null
  private var tvFirstText: TextView? = null
  private var tvSecondText: TextView? = null
  private var tvDescriptionText: TextView? = null

  private var viewSlideView: View? = null

  private var isDestroy = false

  override fun onCreateView(
    inflater: LayoutInflater, container: ViewGroup?,
    savedInstanceState: Bundle?
  ): View? {
    viewSlideView = inflater.inflate(R.layout.fragment_slide_show_item, container, false)

    ivBackground = viewSlideView?.findViewById(R.id.ivBackground)
    tvFirstText = viewSlideView?.findViewById(R.id.tvFirstText)
    tvSecondText = viewSlideView?.findViewById(R.id.tvSecondText)
    tvDescriptionText = viewSlideView?.findViewById(R.id.tvDescriptionText)

    bindData(HelperData.SLIDE_CAPTIONS[positionSlide])

    hideContentView()

    if (positionSlide == 0) makeViewAnimation()

    return viewSlideView
  }

  private fun hideContentView() {
    tvFirstText?.visibility = View.GONE
    tvSecondText?.visibility = View.GONE
    tvDescriptionText?.visibility = View.GONE
  }

  fun makeViewAnimation() {
    if (isDestroy) return

    tvFirstText?.apply {
      visibility = View.GONE
      alpha = 0f
      visibility = View.VISIBLE
      animate().alpha(1f).setDuration(1000L).setListener(null)
    }

    tvSecondText?.apply {
      visibility = View.GONE
      alpha = 0f
      visibility = View.VISIBLE
      animate().alpha(1f).setStartDelay(500L).setDuration(1000L).setListener(null)
    }

    tvDescriptionText?.apply {
      visibility = View.GONE
      alpha = 0f
      translationY = 100f
      visibility = View.VISIBLE
      animate().alpha(1f).translationY(0f).setStartDelay(700L).setDuration(1000L).setListener(null)
    }
  }

  private fun bindData(data: SlideCaption) {
    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
      tvFirstText?.text = Html.fromHtml("<u>${data.firstText}</u>", Html.FROM_HTML_MODE_LEGACY)
    } else {
      tvFirstText?.text = Html.fromHtml("<u>${data.firstText}</u>")
    }
    tvSecondText?.text = data.secondText
    tvDescriptionText?.text = data.description

    if(viewSlideView != null && ivBackground != null){
      GlideApp.with(viewSlideView!!)
        .load(data.backgroundId)
        .centerCrop()
        .into(ivBackground!!)
    }
  }

  override fun onDestroyView() {
    super.onDestroyView()

    isDestroy = true
  }
}