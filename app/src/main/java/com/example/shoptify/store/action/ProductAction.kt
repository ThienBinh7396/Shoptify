package com.example.shoptify.store.action

import org.rekotlin.Action

sealed class ProductAction: Action {
  class UPDATE_NUM(val num: Int) : ProductAction()
}