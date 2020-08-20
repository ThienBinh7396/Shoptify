package com.example.shoptify.adapter.recyclerView

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.shoptify.R
import com.example.shoptify.databinding.BannerHomeSingleLayoutBinding

class BannerHomeAdapter: RecyclerView.Adapter<BannerHomeAdapter.BannerHomeViewHolder>() {
  companion object{
    val listBanner = mutableListOf(
      "https://cdn.shopify.com/s/files/1/1183/1630/t/2/assets/home_banner_2.jpg?v=9885473451025405610",
      "https://cdn.shopify.com/s/files/1/1183/1630/t/2/assets/home_banner_4.jpg?v=10356057864928740073",
      "https://cdn.shopify.com/s/files/1/1183/1630/t/2/assets/home_banner_1.jpg?v=608020184868000833",
      "https://cdn.shopify.com/s/files/1/1183/1630/t/2/assets/home_banner_3.jpg?v=8851963459335790571"
    )
  }

  class BannerHomeViewHolder(var binding: BannerHomeSingleLayoutBinding): RecyclerView.ViewHolder(binding.root){
    fun bindImage(data: String){
      binding.data = data
    }
  }

  override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BannerHomeViewHolder =
    BannerHomeViewHolder(
      DataBindingUtil.inflate(
        LayoutInflater.from(parent.context),
        R.layout.banner_home_single_layout,
        null,
        false
      )
    )

  override fun onBindViewHolder(holder: BannerHomeViewHolder, position: Int) {
    Log.d("Binh", "POSITION: $position")

    holder.bindImage(listBanner[position])
  }

  override fun getItemCount(): Int = listBanner.size
}