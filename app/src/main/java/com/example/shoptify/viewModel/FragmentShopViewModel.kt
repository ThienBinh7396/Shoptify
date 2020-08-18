package com.example.shoptify.viewModel

import android.util.Log
import androidx.databinding.BaseObservable
import com.example.shoptify.BR
import com.example.shoptify.common.AccordionDataModel
import com.example.shoptify.common.HelperAccordionProductData
import com.example.shoptify.store
import com.example.shoptify.store.state.AppState
import org.rekotlin.StoreSubscriber

class FragmentShopViewModel: BaseObservable(), StoreSubscriber<AppState> {
  private var mAccordionListDataModelInstance = store.state.appState.listAccordionProductData

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
    mAccordionListDataModelInstance = state.listAccordionProductData
    notifyChange()
  }
}