package com.example.shoptify.adapter.recyclerView

import android.content.Context
import android.transition.AutoTransition
import android.transition.TransitionManager
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.shoptify.R
import com.example.shoptify.common.AccordionListDataModel
import com.example.shoptify.common.HelperExpandAnim
import com.example.shoptify.databinding.AccordionProductItemLayoutBinding

class AccordionProductDataAdapter(
  accordionListDataModel: MutableList<AccordionListDataModel>,
  context: Context
) :
  RecyclerView.Adapter<AccordionProductDataAdapter.AccordionProductDataViewHolder>() {
  private val mAccordionListDataModel: MutableList<AccordionListDataModel> = accordionListDataModel

  private var expandAnim = AnimationUtils.loadAnimation(context, R.anim.expanded_view_anim)
  private var collapseAnim = AnimationUtils.loadAnimation(context, R.anim.collapsed_view_anim)

  class AccordionProductDataViewHolder(
    private val binding: AccordionProductItemLayoutBinding,
    private val expandAnim: Animation,
    private val collapseAnim: Animation
  ) :
    RecyclerView.ViewHolder(binding.container) {

    private var positionData: Int = -1

    init {
      binding.headerLayout.setOnClickListener {
        if (positionData != -1) {
          binding.expandView.apply {

            visibility = if (visibility == View.GONE) {
              View.VISIBLE
            } else {
              View.GONE
            }
          }

          binding.imvIcon.setImageResource(if (binding.expandView.visibility == View.GONE) R.drawable.ic_baseline_arrow_drop_down_24 else R.drawable.ic_baseline_arrow_drop_up_24)

        }
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
        parent,
        false
      ),
      expandAnim, collapseAnim
    )

  override fun getItemCount(): Int = mAccordionListDataModel.size

  override fun onBindViewHolder(holder: AccordionProductDataViewHolder, position: Int) {
    holder.bindingData(mAccordionListDataModel[position], position)
  }

  fun updateList(data: MutableList<AccordionListDataModel>) {
    val diffCallback = AccordionProductDataDiffCallback(mAccordionListDataModel, data)

    val diffResult = DiffUtil.calculateDiff(diffCallback)

    mAccordionListDataModel.clear()
    mAccordionListDataModel.addAll(data)

    diffResult.dispatchUpdatesTo(this)
  }


  class AccordionProductDataDiffCallback(
    private val oldList: MutableList<AccordionListDataModel>,
    private val newList: MutableList<AccordionListDataModel>
  ) : DiffUtil.Callback() {
    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean =
      oldList[oldItemPosition].title == newList[newItemPosition].title

    override fun getOldListSize(): Int = oldList.size

    override fun getNewListSize(): Int = newList.size

    override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean =
      oldList[oldItemPosition].title == newList[newItemPosition].title
  }
}
