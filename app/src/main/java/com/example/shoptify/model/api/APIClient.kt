package com.example.shoptify.model.api

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class APIClient {
  companion object{
    private var retrofit: Retrofit? = null
    fun getClient(baseUrl: String): Retrofit {
      if (retrofit == null) {
        retrofit = Retrofit.Builder()
          .baseUrl(baseUrl)
          .addConverterFactory(GsonConverterFactory.create())
          .build()
      }
      return retrofit!!
    }
  }
}