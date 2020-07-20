package com.example.shoptify.store.state

import org.rekotlin.StateType

class RootState(
  val productState: ProductState,
  val appState: AppState
) : StateType