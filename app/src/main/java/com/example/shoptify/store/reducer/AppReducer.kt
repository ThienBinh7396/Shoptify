package com.example.shoptify.store.reducer

import android.util.Log
import com.example.shoptify.common.*
import com.example.shoptify.model.CategoryListResponse
import com.example.shoptify.model.ProductListResponse
import com.example.shoptify.model.ProductStatusListResponse
import com.example.shoptify.model.VendorListResponse
import com.example.shoptify.store.action.AppAction
import com.example.shoptify.store.state.AppState
import com.example.shoptify.ui.dialog.LoadingDialog
import org.rekotlin.Action

fun appReducer(action: Action, appState: AppState?): AppState{
  var _appState = appState ?: AppState()

  when(action){
    is AppAction.INITIALIZE_LOADING_DIALOG ->
      _appState = _appState.copy(
        loadingDialog =  LoadingDialog(context =  action.context)
      )

    is AppAction.UPDATE_IS_SHOW_LOADING_DIALOG -> {
      _appState = _appState.copy(
        isShowLoadingDialog = action.isLoading
      )

      Log.d("Binh", "Update loading ${action.isLoading}")
    }

    is AppAction.UPDATE_IS_FIRST_FETCH_DATA ->
      _appState = _appState.copy(
        isFirstFetchData =  action.isFirstFetch
      )

    is AppAction.UPDATE_LIST_ACCORDION_PRODUCT_DATA ->
      _appState = _appState.copy(
        listAccordionProductData = action.listAccordionProductData
      )

    is AppAction.UPDATE_BASE_STORE_DATA ->
      when(action.typeResponse){
        PRODUCT_RESPONSE ->
          _appState = _appState.copy(
            productListResponse = action.data as ProductListResponse
          )

        CATEGORY_RESPONSE ->
          _appState = _appState.copy(
            categoryListResponse = action.data as CategoryListResponse
          )

        VENDOR_RESPONSE ->
          _appState = _appState.copy(
            vendorListResponse = action.data as VendorListResponse
          )

        PRODUCT_STATUS_RESPONSE ->
          _appState = _appState.copy(
            productStatusListResponse = action.data as ProductStatusListResponse
          )
      }
  }

  return _appState
}
