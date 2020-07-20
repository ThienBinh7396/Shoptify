package com.example.shoptify.model

import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class Vendor(
  @SerializedName("_id")
  val id : String,
  @SerializedName("logo")
  val logo: String,
  @SerializedName("title")
  val title: String,
  @SerializedName("address")
  val address: String,
  @SerializedName("phone")
  val phone: String,
  @SerializedName("count_product")
  val count_product: Int
): Serializable

data class VendorListResponse(
  @SerializedName("docs")
  val docs: MutableList<Vendor>,
  @SerializedName("totalDocs")
  val total: Int,
  @SerializedName("hasNextPage")
  var hasNextPage: Boolean,
  @SerializedName("page")
  var page: Int,
  @SerializedName("nextPage")
  var nextPage: Int?
) : Serializable

data class VendorListAPIResponse(
  @SerializedName("message")
  val message: String,
  @SerializedName("data")
  val data: VendorListResponse
) : Serializable