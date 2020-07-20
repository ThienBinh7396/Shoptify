package com.example.shoptify.model

import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class Category(
  @SerializedName("_id")
  val id: String,
  @SerializedName("thumbnail")
  val thumbnail: String,
  @SerializedName("title")
  val title: String,
  @SerializedName("count_product")
  val count_product: Int
) : Serializable

data class CategoryListResponse(
  @SerializedName("docs")
  val docs: MutableList<Category>,
  @SerializedName("totalDocs")
  val total: Int,
  @SerializedName("hasNextPage")
  var hasNextPage: Boolean,
  @SerializedName("page")
  var page: Int,
  @SerializedName("nextPage")
  var nextPage: Int?
) : Serializable

data class CategoryListAPIResponse(
  @SerializedName("message")
  val message: String,
  @SerializedName("data")
  val data: CategoryListResponse
) : Serializable