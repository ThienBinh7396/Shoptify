package com.example.shoptify.model

import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class ProductStatus(
  @SerializedName("_id")
  val id: String,
  @SerializedName("title")
  val title: String
) : Serializable

data class ProductStatusListResponse(
  @SerializedName("docs")
  val docs: MutableList<ProductStatus>,
  @SerializedName("totalDocs")
  val total: Int,
  @SerializedName("hasNextPage")
  var hasNextPage: Boolean,
  @SerializedName("page")
  var page: Int,
  @SerializedName("nextPage")
  var nextPage: Int?
) : Serializable

data class ProductStatusListAPIResponse(
  @SerializedName("message")
  val message: String,
  @SerializedName("data")
  val data: ProductStatusListResponse
) : Serializable