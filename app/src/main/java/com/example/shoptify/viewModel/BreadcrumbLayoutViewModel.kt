package com.example.shoptify.viewModel

import androidx.databinding.BaseObservable
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.shoptify.adapter.viewPager.recyclerView.RecycleViewBreadcrumbItemAdapter
import com.example.shoptify.model.Breadcrumb

class BreadcrumbLayoutViewModel(listBreadcrumb: MutableList<Breadcrumb>, title: String?) : BaseObservable(){
  private var mListBreadcrumb = listBreadcrumb
  private var mTitle = title

  val listBreadcrumb
    get() = this.mListBreadcrumb

  val title
    get() = this.mTitle

  companion object{
    @BindingAdapter("app:bindListBreadcrumbRecycleView")
    @JvmStatic
    fun bindListBreadcrumbRecycleView(rcvBreadcum: RecyclerView, listBreadcum: MutableList<Breadcrumb>){
      if(rcvBreadcum.adapter == null){
        val adapter = RecycleViewBreadcrumbItemAdapter(listBreadcum)

        val linearLayoutManager = LinearLayoutManager(rcvBreadcum.context)
        linearLayoutManager.orientation = LinearLayoutManager.HORIZONTAL

        rcvBreadcum.adapter = adapter
        rcvBreadcum.layoutManager = linearLayoutManager
      }else{
        (rcvBreadcum.adapter as RecycleViewBreadcrumbItemAdapter).updateListBreadcum(listBreadcum)
      }
    }
  }
}