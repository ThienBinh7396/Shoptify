package com.example.shoptify.model.api

class APIUtils {
  companion object{
    private val BASE_URL = "http://192.168.1.118:5000/api/"

    fun getAPIService(): IAPIService = APIClient.getClient(BASE_URL).create(IAPIService::class.java)
  }
}