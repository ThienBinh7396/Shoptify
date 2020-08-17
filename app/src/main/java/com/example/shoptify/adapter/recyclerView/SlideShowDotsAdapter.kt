package com.example.shoptify.adapter.recyclerView

import android.content.Context
import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.shoptify.common.HelperData
import com.example.shoptify.R
import com.example.shoptify.common.SlideCaption

class SlideShowDotsAdapter(var context: Context) :
  RecyclerView.Adapter<SlideShowDotsAdapter.RecyclerViewShideShowDotsViewHolder>() {

  class RecyclerViewShideShowDotsViewHolder(view: View) : RecyclerView.ViewHolder(view) {
    private val ivDotBackground: ImageView = view.findViewById(R.id.ivDotBackground)
    private val tvDot: TextView = view.findViewById(R.id.tvDot)

    fun initView(data: SlideCaption) {
      tvDot.text = String(Character.toChars(0x2022))
      tvDot.setTextColor(if (data.isActive) Color.WHITE else Color.BLACK)

      ivDotBackground.setColorFilter(if (data.isActive) Color.BLACK else Color.WHITE)
    }

  }

  override fun onCreateViewHolder(
    parent: ViewGroup,
    viewType: Int
  ): RecyclerViewShideShowDotsViewHolder {
    val view = LayoutInflater.from(context)
      .inflate(R.layout.recyclerview_slide_show_dots, parent, false)
    return RecyclerViewShideShowDotsViewHolder(view)
  }

  override fun getItemCount() = HelperData.SLIDE_CAPTIONS.size

  override fun onBindViewHolder(holder: RecyclerViewShideShowDotsViewHolder, position: Int) {
    holder.initView(HelperData.SLIDE_CAPTIONS[position])
  }

}