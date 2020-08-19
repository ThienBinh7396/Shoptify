package com.example.shoptify.adapter.recyclerView

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.shoptify.R
import com.example.shoptify.databinding.ShortProductBlockLayoutBinding
import com.example.shoptify.model.Product

class ShortProductBlockAdapter: RecyclerView.Adapter<ShortProductBlockAdapter.ShortProductBlockViewHolder>() {
  private val mProductList: MutableList<Product> = mutableListOf()

  class ShortProductBlockViewHolder(var binding: ShortProductBlockLayoutBinding): RecyclerView.ViewHolder(binding.root){
    fun bindingData(data: Product){
      binding.product = data
    }
  }

  override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ShortProductBlockViewHolder {
    Log.d("Binh", "Bind short product block")

    return ShortProductBlockViewHolder(
      DataBindingUtil.inflate(
        LayoutInflater.from(parent.context),
        R.layout.short_product_block_layout,
        null,
        false
      )
    )
  }

  override fun onBindViewHolder(holder: ShortProductBlockViewHolder, position: Int) {
    holder.bindingData(mProductList[position])
  }

  override fun getItemCount(): Int = mProductList.size

  fun updateList(newList: MutableList<Product>, typeDisplay: Int){
    val diffCallback = ShortProductBlockDiffCallback(mProductList, newList)

    val diffResult = DiffUtil.calculateDiff(diffCallback)

    mProductList.clear()
    mProductList.addAll(newList)

    diffResult.dispatchUpdatesTo(this)
  }

  class ShortProductBlockDiffCallback(
    var oldList: MutableList<Product>,
    var newList: MutableList<Product>
  ): DiffUtil.Callback(){
    override fun getOldListSize(): Int = oldList.size

    override fun getNewListSize(): Int = newList.size

    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean =
      oldList[oldItemPosition].id == newList[newItemPosition].id

    override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean =
      oldList[oldItemPosition].id == newList[newItemPosition].id
  }
}