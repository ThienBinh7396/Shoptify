package com.example.shoptify.binding

import android.util.Log
import android.view.View
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.transition.AutoTransition
import androidx.transition.TransitionManager
import com.example.shoptify.adapter.recyclerView.AccordionProductDataAdapter
import com.example.shoptify.adapter.recyclerView.AccordionSingleDataAdapter
import com.example.shoptify.common.AccordionDataModel
import com.example.shoptify.common.AccordionListDataModel
import com.example.shoptify.common.HelperMethod

class AppBindingData {
  companion object{
    @BindingAdapter("app:bindAccordionSingleData")
    @JvmStatic
    fun bindAccordionSingleData(rcv: RecyclerView, data: MutableList<AccordionDataModel>){
      if(rcv.adapter == null){
        rcv.adapter = AccordionSingleDataAdapter(data)
        rcv.layoutManager = LinearLayoutManager(rcv.context, LinearLayoutManager.VERTICAL, false)

      }else{
        (rcv.adapter as AccordionSingleDataAdapter).updateList(data)
      }
    }

    @BindingAdapter("app:bindAccordionProductData")
    @JvmStatic
    fun bindAccordionProductData(rcv: RecyclerView, data: MutableList<AccordionListDataModel>){
      if (rcv.adapter == null){
        rcv.adapter = AccordionProductDataAdapter(data)
        rcv.layoutManager = LinearLayoutManager(rcv.context, LinearLayoutManager.VERTICAL, false)

      }else{
        (rcv.adapter as AccordionProductDataAdapter).updateList(data)
      }
    }

    @BindingAdapter("app:bindTextToCapitalize")
    @JvmStatic
    fun bindTextToCapitalize(textView: TextView, str: String){
      textView.text = HelperMethod.textToCapitalize(str)
    }

    @BindingAdapter("app:bindCollapsedCardView")
    @JvmStatic
    fun bindCollapsedCardView(cardView: CardView, isCollapsed: Boolean){
      cardView.visibility = if(isCollapsed) View.VISIBLE else View.GONE

      TransitionManager.beginDelayedTransition(cardView, AutoTransition())

    }
  }
}