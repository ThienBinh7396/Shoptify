package com.example.shoptify.model

import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class Product(
  @SerializedName("_id")
  val id: String,
  @SerializedName("category_id")
  val category: MutableList<Category>,
  @SerializedName("more_information")
  val moreInformation: String,
  @SerializedName("galleries")
  val galleries: MutableList<String>,
  @SerializedName("colors")
  val colors: MutableList<String>,
  @SerializedName("sizes")
  val sizes: MutableList<String>,
  @SerializedName("status")
  val status: ProductStatus,
  @SerializedName("title")
  val title: String,
  @SerializedName("price")
  val price: Float,
  @SerializedName("description")
  val description: String,
  @SerializedName("discount")
  val discount: Float,
  @SerializedName("thumbnail")
  val thumbnail: String,
  @SerializedName("product_code")
  val productCode: String,
  @SerializedName("vendor_id")
  val vendor: Vendor

) : Serializable

data class ProductListResponse(
  @SerializedName("docs")
  val docs: MutableList<Product>,
  @SerializedName("totalDocs")
  val total: Int,
  @SerializedName("hasNextPage")
  var hasNextPage: Boolean,
  @SerializedName("page")
  var page: Int,
  @SerializedName("nextPage")
  var nextPage: Int?
) : Serializable

data class ProductListAPIResponse(
  @SerializedName("message")
  val message: String,
  @SerializedName("data")
  val data: ProductListResponse
) : Serializable

data class TopSaleProductAPIResponse(
  @SerializedName("message")
  val message: String,
  @SerializedName("data")
  val data: MutableList<Product>
): Serializable