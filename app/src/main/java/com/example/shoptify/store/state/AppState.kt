package com.example.shoptify.store.state

import com.example.shoptify.common.AccordionListDataModel
import com.example.shoptify.common.HelperAccordionProductData
import com.example.shoptify.model.*
import com.example.shoptify.ui.dialog.LoadingDialog
import org.rekotlin.StateType

data class AppState(
  var isShowLoadingDialog: Boolean = false,
  var loadingDialog: LoadingDialog? = null,
  var isFirstFetchData: Boolean = false,
  var productListResponse: ProductListResponse? = null,
  var productTopSaleResponse: MutableList<Product>? = null,
  var categoryListResponse: CategoryListResponse? = null,
  var productStatusListResponse: ProductStatusListResponse? = null,
  var vendorListResponse: VendorListResponse? = null,
  var listAccordionProductData: MutableList<AccordionListDataModel> = HelperAccordionProductData.getInstance()
) : StateType