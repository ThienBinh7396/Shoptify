package com.example.shoptify.viewModel

import androidx.databinding.BaseObservable
import com.example.shoptify.common.DISPLAY_GRID
import com.example.shoptify.common.DISPLAY_LIST
import com.example.shoptify.model.Product
import com.example.shoptify.store
import com.example.shoptify.store.state.AppState
import com.example.shoptify.ui.fragment.IShopFragmentEventListener
import org.rekotlin.StoreSubscriber

class FragmentShopViewModel(var eventListener: IShopFragmentEventListener) : BaseObservable(), StoreSubscriber<AppState> {

  private var mAccordionListDataModelInstance = store.state.appState.listAccordionProductData

  var typeList = DISPLAY_GRID

  private var mProduct = mutableListOf<Product>()

  fun checkIsListType() = typeList == DISPLAY_LIST

  fun toggleDisplayType() {
    typeList = if (typeList == DISPLAY_LIST) DISPLAY_GRID else DISPLAY_LIST
    notifyChange()
  }

  init {
    store.subscribe(this) {
      it.select {
        it.appState
      }
    }
  }

  val listAccordionData
    get() = this.mAccordionListDataModelInstance

  val listProduct
    get() = mProduct

  override fun newState(state: AppState) {

    state.apply {
      mAccordionListDataModelInstance = listAccordionProductData

      if (productListResponse != null) {
        mProduct = productListResponse!!.docs
      }

      notifyChange()
    }
  }
}