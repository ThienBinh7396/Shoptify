package com.example.shoptify.adapter.recyclerView

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.shoptify.R
import com.example.shoptify.databinding.BlockServiceLayoutBinding
import com.example.shoptify.model.BlockServiceData

class BlockServicesAdapter: RecyclerView.Adapter<BlockServicesAdapter.BlockServicesViewHolder>() {

  companion object{
    val listServices = mutableListOf(
      BlockServiceData("WORLDWIDE DELIVERY", "On order over \$150.00", R.drawable.ic_delivery),
      BlockServiceData("MONEY BACK GUARANTEE", "Within 15 days", R.drawable.ic_guarantee),
      BlockServiceData("24/7 CUSTOMER SERVICE", "Call Us at 1800 - 123 - 456 78", R.drawable.ic_headphone)
    )
  }

  class BlockServicesViewHolder(var binding: BlockServiceLayoutBinding): RecyclerView.ViewHolder(binding.root){
    fun bindingData(data: BlockServiceData){
      binding.mBlockServiceData = data
    }
  }

  override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BlockServicesViewHolder =
    BlockServicesViewHolder(
      DataBindingUtil.inflate(
        LayoutInflater.from(parent.context),
        R.layout.block_service_layout,
        parent,
        false
      )
    )

  override fun onBindViewHolder(holder: BlockServicesViewHolder, position: Int) {
    holder.bindingData(listServices[position])
  }

  override fun getItemCount(): Int = listServices.size
}