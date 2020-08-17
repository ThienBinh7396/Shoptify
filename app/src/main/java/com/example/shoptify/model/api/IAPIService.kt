package com.example.shoptify.model.api

import com.example.shoptify.model.CategoryListAPIResponse
import com.example.shoptify.model.ProductListAPIResponse
import com.example.shoptify.model.ProductStatusListAPIResponse
import com.example.shoptify.model.VendorListAPIResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface IAPIService {
  @GET("categories")
  fun fetchCategories(
    @Query("page") page: Int?
  ): Call<CategoryListAPIResponse>

  @GET("products")
  fun fetchProducts(
    @Query("page") page: Int?
  ): Call<ProductListAPIResponse>

  @GET("vendors")
  fun fetchVendors(
    @Query("page") page: Int?
  ): Call<VendorListAPIResponse>

  @GET("product-status")
  fun fetchProductStatus(
    @Query("page") page: Int?
  ): Call<ProductStatusListAPIResponse>
}