package com.example.shoptify.adapter.recyclerView

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.shoptify.R
import com.example.shoptify.common.AccordionDataModel
import com.example.shoptify.databinding.AccordionSingleDataItemLayoutBinding

class AccordionSingleDataAdapter(accordionDataModel: MutableList<AccordionDataModel>) :
  RecyclerView.Adapter<AccordionSingleDataAdapter.AccordionSingleDataViewHolder>() {
  private val mAccordionDataList = accordionDataModel

  class AccordionSingleDataViewHolder(private val binding: AccordionSingleDataItemLayoutBinding) :
    RecyclerView.ViewHolder(binding.root) {
    fun bindData(data: AccordionDataModel) {
      binding.mAccordionDataModel = data
    }
  }

  override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AccordionSingleDataViewHolder =
    AccordionSingleDataViewHolder(
      DataBindingUtil.inflate(
        LayoutInflater.from(parent.context),
        R.layout.accordion_single_data_item_layout,
        null,
        false
      )
    )

  override fun getItemCount(): Int = mAccordionDataList.size

  override fun onBindViewHolder(holder: AccordionSingleDataViewHolder, position: Int) {

    Log.d("Binh", "Binding hilder.............")
    holder.bindData(mAccordionDataList[position])
  }

  fun updateList(newList: MutableList<AccordionDataModel>) {

    if (newList.size == 0) return
    Log.d("Binh", "Sizeeeeeeee: ${newList.size}")

    val diffCallback = AccordionSingleDataDiffCallback(mAccordionDataList, newList)

    val diffResult = DiffUtil.calculateDiff(diffCallback)

    mAccordionDataList.clear()
    mAccordionDataList.addAll(newList)

    diffResult.dispatchUpdatesTo(this)
  }

  class AccordionSingleDataDiffCallback(
    var oldList: MutableList<AccordionDataModel>,
    var newList: MutableList<AccordionDataModel>
  ) : DiffUtil.Callback() {
    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean =
      oldList[oldItemPosition].title == newList[newItemPosition].title

    override fun getOldListSize(): Int = oldList.size

    override fun getNewListSize(): Int = newList.size

    override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean =
      oldList[oldItemPosition].title == newList[newItemPosition].title
  }
}