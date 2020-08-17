package com.example.shoptify.adapter.viewPager.recyclerView

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.shoptify.model.Breadcrumb
import com.example.shoptify.R
import com.example.shoptify.common.HelperData

class RecycleViewBreadcrumbItemAdapter(var listBreadcum: MutableList<Breadcrumb>):
  RecyclerView.Adapter<RecycleViewBreadcrumbItemAdapter.BreadcrumbItemViewHolder>() {

  private var mListBreadcum = listBreadcum

  class BreadcrumbItemViewHolder(
    var view: View,
    private var totalSize: Int
  ): RecyclerView.ViewHolder(view), View.OnClickListener{
    private var tvTitle = view.findViewById<TextView>(R.id.tvTitle)
    private var tvDivide = view.findViewById<TextView>(R.id.tvDivide)

    private var positionItem = -1
    private var itemData: Breadcrumb? = null

    private var scaleAnim: Animation? = null

    init {
      scaleAnim = AnimationUtils.loadAnimation(view.context, R.anim.scale_view_with_reverse_anim)

      tvTitle.setOnClickListener(this)
    }

    fun bindData(data: Breadcrumb, position: Int){
      tvTitle.text = data.title
      positionItem = position
      itemData = data

      tvDivide.visibility = if(position == totalSize - 1) View.GONE else View.VISIBLE
    }

    override fun onClick(p0: View?) {
      if (positionItem > -1 && positionItem < totalSize && itemData != null){
        if(scaleAnim != null){
          tvTitle.startAnimation(scaleAnim)
        }
        HelperData.navigateByTitle(itemData!!.title)
      }
    }
  }

  override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BreadcrumbItemViewHolder {
    val view = LayoutInflater.from(parent.context)
      .inflate(R.layout.recyclerview_breadcum_item, parent, false)

    return BreadcrumbItemViewHolder(view, mListBreadcum.size)
  }

  override fun getItemCount() = listBreadcum.size

  override fun onBindViewHolder(holder: BreadcrumbItemViewHolder, position: Int) {
    holder.bindData(listBreadcum[position], position)
  }

  fun updateListBreadcum(listBreadcum: MutableList<Breadcrumb>){
    val diffCallback = BreadcumDiffCallback(mListBreadcum, listBreadcum)
    val diffResult = DiffUtil.calculateDiff(diffCallback)

    mListBreadcum.clear()

    diffResult.dispatchUpdatesTo(this)
  }

  class BreadcumDiffCallback(
    var oldListBreadcum: MutableList<Breadcrumb>,
    var newListBreadcum: MutableList<Breadcrumb>
  ): DiffUtil.Callback(){
    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean =
      oldListBreadcum[oldItemPosition].title == newListBreadcum[newItemPosition].title

    override fun getOldListSize(): Int = oldListBreadcum.size

    override fun getNewListSize(): Int = newListBreadcum.size

    override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean =
      oldListBreadcum[oldItemPosition].title == newListBreadcum[newItemPosition].title
  }
}