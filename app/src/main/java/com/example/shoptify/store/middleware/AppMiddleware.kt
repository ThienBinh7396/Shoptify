package com.example.shoptify.store.middleware

import android.util.Log
import com.example.shoptify.common.*
import com.example.shoptify.model.*
import com.example.shoptify.model.api.APIUtils
import com.example.shoptify.store
import com.example.shoptify.store.action.AppAction
import com.example.shoptify.store.state.AppState
import com.example.shoptify.store.state.RootState
import org.rekotlin.DispatchFunction
import org.rekotlin.Middleware
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

val appMiddleware: Middleware<RootState> = { dispatch, _ ->
  { next ->
    { action ->
      run {
        when (action) {
          is AppAction.FETCH_CATEGORIES_DATA -> {
            fetchDataFromServerByType("category", dispatch)
          }

          is AppAction.FETCH_PRODUCTS_DATA -> {

            fetchDataFromServerByType("product", dispatch)
          }

          is AppAction.FETCH_VENDORS_DATA -> {
            fetchDataFromServerByType("vendor", dispatch)
          }

          is AppAction.FETCH_PRODUCT_STATUS_DATA -> {
            fetchDataFromServerByType("product-status", dispatch)
          }

        }
        next(action)
      }
    }
  }
}

fun fetchDataFromServerByType(type: String, dispatch: DispatchFunction) {
  when (type) {
    "category" -> {
      fetchCategoryDataFromServe(dispatch)
    }
    "product" -> {
      fetchProductDataFromServe(dispatch)
    }

    "vendor" -> {
      fetchVendorDataFromServe(dispatch)
    }

    "product-status" -> {
      fetchProductStatusDataFromServe(dispatch)
    }
  }
}

fun fetchCategoryDataFromServe(dispatch: DispatchFunction) {
  if (!store.state.appState.isShowLoadingDialog) dispatch(
    AppAction.UPDATE_IS_SHOW_LOADING_DIALOG(
      true
    )
  )

  store.state.appState.apply {
    APIUtils.getAPIService().apply {

      if (categoryListResponse?.hasNextPage == false) return

      fetchCategories(if (categoryListResponse == null) 1 else categoryListResponse?.nextPage)
        .enqueue(object : Callback<CategoryListAPIResponse> {
          override fun onFailure(call: Call<CategoryListAPIResponse>, t: Throwable) {
            Log.d("Binh", "Faild category: ${t.message}")

          }

          override fun onResponse(
            call: Call<CategoryListAPIResponse>,
            response: Response<CategoryListAPIResponse>
          ) {
            if (response.body() != null) {
              val data = response.body()!!.data

              if (categoryListResponse != null) {
                data.docs.addAll(0, categoryListResponse!!.docs)
              }

              listAccordionProductData[0].data = data.docs.map {
                AccordionDataModel(
                  title = it.title,
                  amount = it.count_product
                )
              }.toMutableList()

              dispatch(AppAction.UPDATE_LIST_ACCORDION_PRODUCT_DATA(listAccordionProductData))

              dispatch(
                AppAction.UPDATE_BASE_STORE_DATA(
                  CATEGORY_RESPONSE,
                  data
                )
              )
            }
          }
        })
    }
  }
}

fun fetchProductDataFromServe(dispatch: DispatchFunction) {
  store.state.appState.apply {
    APIUtils.getAPIService().apply {

      if (productListResponse?.hasNextPage == false) return
      Log.d("Binh", "Fetching product")

      fetchProducts(if (productListResponse == null) 1 else productListResponse?.nextPage)
        .enqueue(object : Callback<ProductListAPIResponse> {
          override fun onFailure(call: Call<ProductListAPIResponse>, t: Throwable) {
          }

          override fun onResponse(
            call: Call<ProductListAPIResponse>,
            response: Response<ProductListAPIResponse>
          ) {
            if (response.body() != null) {
              val data = response.body()!!.data

              if (productListResponse != null) {
                data.docs.addAll(0, productListResponse!!.docs)
              }

              dispatch(
                AppAction.UPDATE_BASE_STORE_DATA(
                  PRODUCT_RESPONSE,
                  data
                )
              )
            }
          }
        })
    }
  }
}

fun fetchVendorDataFromServe(dispatch: DispatchFunction) {
  store.state.appState.apply {
    APIUtils.getAPIService().apply {

      if (vendorListResponse?.hasNextPage == false) return

      fetchVendors(if (vendorListResponse == null) 1 else vendorListResponse?.nextPage)
        .enqueue(object : Callback<VendorListAPIResponse> {
          override fun onFailure(call: Call<VendorListAPIResponse>, t: Throwable) {
          }

          override fun onResponse(
            call: Call<VendorListAPIResponse>,
            response: Response<VendorListAPIResponse>
          ) {
            if (response.body() != null) {
              val data = response.body()!!.data

              if (vendorListResponse != null) {
                data.docs.addAll(0, vendorListResponse!!.docs)
              }

              listAccordionProductData[1].data = data.docs.map {
                AccordionDataModel(
                  title = it.title,
                  amount = it.count_product
                )
              }.toMutableList()

              dispatch(AppAction.UPDATE_LIST_ACCORDION_PRODUCT_DATA(listAccordionProductData))

              dispatch(
                AppAction.UPDATE_BASE_STORE_DATA(
                  VENDOR_RESPONSE,
                  data
                )
              )
            }
          }
        })
    }
  }
}

fun fetchProductStatusDataFromServe(dispatch: DispatchFunction) {
  store.state.appState.apply {
    APIUtils.getAPIService().apply {

      if (productStatusListResponse?.hasNextPage == false) return

      fetchProductStatus(if (productStatusListResponse == null) 1 else productStatusListResponse?.nextPage)
        .enqueue(object : Callback<ProductStatusListAPIResponse> {
          override fun onFailure(call: Call<ProductStatusListAPIResponse>, t: Throwable) {
          }

          override fun onResponse(
            call: Call<ProductStatusListAPIResponse>,
            response: Response<ProductStatusListAPIResponse>
          ) {
            if (response.body() != null) {
              val data = response.body()!!.data

              if (productStatusListResponse != null) {
                data.docs.addAll(0, productStatusListResponse!!.docs)
              }

              dispatch(
                AppAction.UPDATE_BASE_STORE_DATA(
                  PRODUCT_STATUS_RESPONSE,
                  data
                )
              )
            }
          }
        })
    }
  }
}