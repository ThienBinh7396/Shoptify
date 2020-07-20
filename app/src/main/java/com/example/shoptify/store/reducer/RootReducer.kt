package com.example.shoptify.store.reducer

import com.example.shoptify.store.state.RootState
import org.rekotlin.Action

fun rootReducer(action: Action, rootState: RootState?): RootState =
  RootState(
    appState = appReducer(action, rootState?.appState),
    productState = productReducer(action, rootState?.productState)
  )