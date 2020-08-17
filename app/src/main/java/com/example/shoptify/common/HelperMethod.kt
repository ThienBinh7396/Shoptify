package com.example.shoptify.common

class HelperMethod {
  companion object {
    fun textToCapitalize(str: String): String {
      if (str.isNotEmpty()) {
        return "${str[0].toUpperCase()}${str.substring(1, str.length)}"
      }

      return ""
    }
  }
}