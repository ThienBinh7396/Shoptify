package com.example.shoptify.store.reducer

import com.example.shoptify.store.action.ProductAction
import com.example.shoptify.store.state.ProductState
import org.rekotlin.Action

fun productReducer(action: Action, productState: ProductState?): ProductState {
  var productState = productState ?: ProductState()

  when (action) {
    is ProductAction.UPDATE_NUM -> {
      productState = productState.copy(num = action.num)
    }
  }
  return productState
}