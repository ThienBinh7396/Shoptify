package com.example.shoptify.store.state

import com.example.shoptify.model.CategoryListResponse
import com.example.shoptify.model.ProductListResponse
import com.example.shoptify.model.ProductStatusListResponse
import com.example.shoptify.model.VendorListResponse
import com.example.shoptify.ui.dialog.LoadingDialog
import org.rekotlin.StateType

data class AppState(
  var isShowLoadingDialog: Boolean = false,
  var loadingDialog: LoadingDialog? = null,
  var isFirstFetchData: Boolean = false,
  var productListResponse: ProductListResponse? = null,
  var categoryListResponse: CategoryListResponse? = null,
  var productStatusListResponse: ProductStatusListResponse? = null,
  var vendorListResponse: VendorListResponse? = null
) : StateType