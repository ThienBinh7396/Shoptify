package com.example.shoptify.common

import com.google.gson.Gson
import com.google.gson.GsonBuilder
import java.lang.reflect.Type

class HelperMethod {
  companion object {
    private val gson = GsonBuilder().create()

    fun textToCapitalize(str: String): String {
      if (str.isNotEmpty()) {
        return "${str[0].toUpperCase()}${str.substring(1, str.length)}"
      }

      return ""
    }

    fun <T> diffCloneObject(obj: Any, typeClass: Type): T {
      return gson.fromJson(gson.toJson(obj), typeClass)
    }
  }
}