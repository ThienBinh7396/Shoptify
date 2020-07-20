package com.example.shoptify.model

class Breadcrumb(var title: String) {
  fun isLastest(title: String, listBreadcums: MutableList<Breadcrumb>): Boolean {
    var findIndex = -1
    listBreadcums.forEachIndexed { index, breadcum ->
      if(breadcum.title == title) findIndex = index
    }
    return findIndex == listBreadcums.size - 1
  }
}