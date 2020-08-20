package com.example.shoptify.adapter.recyclerView

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.shoptify.R
import com.example.shoptify.common.DISPLAY_GRID
import com.example.shoptify.databinding.ShortProductBlockLayoutBinding
import com.example.shoptify.databinding.ShortProductBlockLayoutDisplayListBinding
import com.example.shoptify.model.Product

class ShortProductBlockAdapter(private var typeDisplay: Int) :
  RecyclerView.Adapter<RecyclerView.ViewHolder>() {
  private val mProductList: MutableList<Product> = mutableListOf()


  fun checkIsNewTypeDisplay(newType: Int) = newType != typeDisplay

  class ShortProductBlockViewHolder(var binding: ShortProductBlockLayoutBinding) :
    RecyclerView.ViewHolder(binding.root) {
    fun bindingData(data: Product) {
      binding.product = data
    }
  }

  class ShortProductBlockListDisplayViewHolder(var binding: ShortProductBlockLayoutDisplayListBinding) :
    RecyclerView.ViewHolder(binding.root) {
    fun bindingData(data: Product) {
      binding.product = data
    }
  }

  override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {

    return if (typeDisplay == DISPLAY_GRID) ShortProductBlockViewHolder(
      DataBindingUtil.inflate(
        LayoutInflater.from(parent.context), R.layout.short_product_block_layout,
        null,
        false
      )
    ) else
      ShortProductBlockListDisplayViewHolder(
        DataBindingUtil.inflate(
          LayoutInflater.from(parent.context), R.layout.short_product_block_layout_display_list,
          null,
          false
        )
      )
  }

  override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
    if (typeDisplay == DISPLAY_GRID) (holder as ShortProductBlockViewHolder).bindingData(
      mProductList[position]
    ) else (holder as ShortProductBlockListDisplayViewHolder).bindingData(mProductList[position])

  }

  override fun getItemCount(): Int = mProductList.size

  fun updateList(newList: MutableList<Product>, typeDisplay: Int) {
    this.typeDisplay = typeDisplay

    val diffCallback = ShortProductBlockDiffCallback(mProductList, newList)

    val diffResult = DiffUtil.calculateDiff(diffCallback)

    mProductList.clear()
    mProductList.addAll(newList)

    diffResult.dispatchUpdatesTo(this)
  }

  class ShortProductBlockDiffCallback(
    var oldList: MutableList<Product>,
    var newList: MutableList<Product>
  ) : DiffUtil.Callback() {
    override fun getOldListSize(): Int = oldList.size

    override fun getNewListSize(): Int = newList.size

    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean =
      oldList[oldItemPosition].id == newList[newItemPosition].id

    override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean =
      oldList[oldItemPosition].id == newList[newItemPosition].id
  }
}