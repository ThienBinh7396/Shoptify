package com.example.shoptify.common

import com.example.shoptify.store
import com.example.shoptify.store.action.AppAction
import com.example.shoptify.store.state.AppState
import org.rekotlin.StoreSubscriber

class HelperInitializeStoreData: StoreSubscriber<AppState> {
  companion object{
    private fun checkIsInitializedStoreDataAndFetch(){
      store.state.appState.apply {
        when(true){
          this.categoryListResponse == null -> {
          }
        }
      }
    }
  }

  override fun newState(state: AppState) {
    state.apply {
      if(!isFirstFetchData && categoryListResponse != null && productListResponse != null && vendorListResponse != null && productStatusListResponse != null){
        store.dispatch(AppAction.UPDATE_IS_SHOW_LOADING_DIALOG(false))
        store.dispatch(AppAction.UPDATE_IS_FIRST_FETCH_DATA(true))
      }
    }
  }
}