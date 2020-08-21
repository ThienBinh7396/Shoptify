package com.example.shoptify.viewModel

import android.util.Log
import androidx.databinding.BaseObservable
import com.example.shoptify.common.AccordionListDataModel
import com.example.shoptify.common.DISPLAY_GRID
import com.example.shoptify.common.DISPLAY_LIST
import com.example.shoptify.model.Product
import com.example.shoptify.store
import com.example.shoptify.store.state.AppState
import com.example.shoptify.ui.fragment.IShopFragmentEventListener
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import org.rekotlin.StoreSubscriber

class FragmentShopViewModel(var eventListener: IShopFragmentEventListener) : BaseObservable(),
  StoreSubscriber<AppState> {

  private var mAccordionListDataModelInstance = store.state.appState.listAccordionProductData

  var typeList = DISPLAY_GRID

  private var mProduct = mutableListOf<Product>()

  fun checkIsListType() = typeList == DISPLAY_LIST

  fun toggleDisplayType() {
    mAccordionListDataModelInstance = store.state.appState.listAccordionProductData

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

  fun getListProduct(): MutableList<Product> {
    Log.d("Binh", "Update ttttttttttttt")
    return mProduct

  }

  override fun newState(state: AppState) {

    val gson = GsonBuilder().create()

    state.apply {
      mAccordionListDataModelInstance = gson.fromJson(
        gson.toJson(listAccordionProductData),
        Array<AccordionListDataModel>::class.java
      ).toMutableList()

      if (productListResponse != null) {
        mProduct = productListResponse!!.docs
      }

      notifyChange()
    }
  }
}