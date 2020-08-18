package com.example.shoptify.adapter.recyclerView

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.shoptify.R
import com.example.shoptify.common.AccordionListDataModel
import com.example.shoptify.databinding.AccordionProductItemLayoutBinding

class AccordionProductDataAdapter(accordionListDataModel: MutableList<AccordionListDataModel>) :
  RecyclerView.Adapter<AccordionProductDataAdapter.AccordionProductDataViewHolder>(),
  IAccordionProductDataEventListener {
  private val mAccordionListDataModel: MutableList<AccordionListDataModel> = accordionListDataModel

  class AccordionProductDataViewHolder(
    private val binding: AccordionProductItemLayoutBinding,
    private val eventListener: IAccordionProductDataEventListener
  ) :
    RecyclerView.ViewHolder(binding.container) {

    private var positionData: Int = -1

    init {
      binding.headerLayout.setOnClickListener {

        if (positionData != -1)
          eventListener.onHeaderClickListener(positionData)
      }
    }

    fun bindingData(data: AccordionListDataModel, position: Int) {
      positionData = position
      binding.mAccordionListDataModel = data
    }
  }

  override fun onCreateViewHolder(
    parent: ViewGroup,
    viewType: Int
  ): AccordionProductDataViewHolder =
    AccordionProductDataViewHolder(
      DataBindingUtil.inflate(
        LayoutInflater.from(parent.context),
        R.layout.accordion_product_item_layout,
        null,
        false
      ),
      this
    )

  override fun getItemCount(): Int = mAccordionListDataModel.size

  override fun onBindViewHolder(holder: AccordionProductDataViewHolder, position: Int) {
    holder.bindingData(mAccordionListDataModel[position], position)
  }

  fun updateList(data: MutableList<AccordionListDataModel>) {
    val diffCallback = AccordionProductDataDiffCallback(mAccordionListDataModel, data)

    val diffResult = DiffUtil.calculateDiff(diffCallback)

    mAccordionListDataModel.clear()

    diffResult.dispatchUpdatesTo(this)
  }

  override fun onHeaderClickListener(position: Int) {
    if (position < mAccordionListDataModel.size){
      Log.d("Binh", "Click: ${mAccordionListDataModel.size} $position ${mAccordionListDataModel[position].isCollapsed}")

      mAccordionListDataModel[position].isCollapsed = !mAccordionListDataModel[position].isCollapsed
      notifyItemChanged(position)

    }
  }

  class AccordionProductDataDiffCallback(
    private val oldList: MutableList<AccordionListDataModel>,
    private val newList: MutableList<AccordionListDataModel>
  ) : DiffUtil.Callback() {
    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean =
      oldList[oldItemPosition] == newList[newItemPosition]

    override fun getOldListSize(): Int = oldList.size

    override fun getNewListSize(): Int = newList.size

    override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean =
      oldList[oldItemPosition].title == newList[newItemPosition].title && oldList[oldItemPosition].isCollapsed == newList[newItemPosition].isCollapsed
  }
}

interface IAccordionProductDataEventListener {
  fun onHeaderClickListener(position: Int)
}