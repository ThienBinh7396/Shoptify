package com.example.shoptify

import android.app.Application
import com.example.shoptify.store.action.AppAction
import com.example.shoptify.store.reducer.rootReducer
import org.rekotlin.Store

val store = Store(
  reducer = ::rootReducer,
  state = null
)

class MainApplication : Application(){
  override fun onCreate() {
    super.onCreate()

    store.dispatch(AppAction.INITIALIZE_LOADING_DIALOG(applicationContext))
  }
}