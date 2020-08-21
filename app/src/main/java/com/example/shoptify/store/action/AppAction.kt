package com.example.shoptify.store.action

import android.content.Context
import com.example.shoptify.common.AccordionListDataModel
import org.rekotlin.Action

sealed class AppAction : Action {
  class INITIALIZE_LOADING_DIALOG(val context: Context): Action
  class UPDATE_IS_SHOW_LOADING_DIALOG(val isLoading: Boolean) : Action
  class UPDATE_IS_FIRST_FETCH_DATA(val isFirstFetch: Boolean) : Action

  class UPDATE_LIST_ACCORDION_PRODUCT_DATA(val listAccordionProductData: MutableList<AccordionListDataModel>): Action

  class FETCH_PRODUCTS_DATA: Action
  class FETCH_TOP_SALE_PRODUCTS_DATA: Action
  class FETCH_CATEGORIES_DATA: Action
  class FETCH_PRODUCT_STATUS_DATA: Action
  class FETCH_VENDORS_DATA: Action

  class UPDATE_BASE_STORE_DATA(val typeResponse: String, val data: Any): Action
}