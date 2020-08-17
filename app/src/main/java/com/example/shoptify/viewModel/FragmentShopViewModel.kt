package com.example.shoptify.viewModel

import androidx.databinding.BaseObservable
import com.example.shoptify.common.AccordionDataModel
import com.example.shoptify.common.HelperAccordionProductData
import com.example.shoptify.store
import com.example.shoptify.store.state.AppState
import org.rekotlin.StoreSubscriber

class FragmentShopViewModel: BaseObservable(), StoreSubscriber<AppState> {
  private val mAccordionListDataModelInstance = HelperAccordionProductData.getInstance()

  init {
    store.subscribe(this){
      it.select {
        it.appState
      }
    }
  }

  val listAccordionData
    get() = this.mAccordionListDataModelInstance

  override fun newState(state: AppState) {
    state.categoryListResponse?.apply {
      mAccordionListDataModelInstance[0].data = docs.map {
        AccordionDataModel(
          title = it.title,
          amount =  it.count_product
        )
      }.toMutableList()

      notifyChange()
    }

    state.vendorListResponse?.apply {
      mAccordionListDataModelInstance[1].data = docs.map {
        AccordionDataModel(
          title = it.title,
          amount =  it.count_product
        )
      }.toMutableList()
      notifyChange()
    }
  }
}