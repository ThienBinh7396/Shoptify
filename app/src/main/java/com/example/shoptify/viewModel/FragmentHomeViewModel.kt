package com.example.shoptify.viewModel

import android.util.Log
import androidx.databinding.BaseObservable
import androidx.databinding.ObservableList
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.shoptify.common.HelperMethod
import com.example.shoptify.model.Product
import com.example.shoptify.store
import com.example.shoptify.store.state.AppState
import org.rekotlin.StoreSubscriber

class FragmentHomeViewModel : BaseObservable(), StoreSubscriber<AppState> {
  private var mTopSaleProducts = mutableListOf<Product>()

  val topSaleProducts
    get() = mTopSaleProducts

  init {
    store.subscribe(this){
      it.select {
        it.appState
      }
    }
  }

  override fun newState(state: AppState) {
    state.apply {
      if (productTopSaleResponse != null) {
        mTopSaleProducts = (HelperMethod.diffCloneObject(
          productTopSaleResponse!!,
          Array<Product>::class.java
        ) as Array<Product>).toMutableList()

        notifyChange()
      }
    }
  }
}