package com.example.shoptify

import android.app.Application
import com.example.shoptify.store.action.AppAction
import com.example.shoptify.store.middleware.appMiddleware
import com.example.shoptify.store.reducer.rootReducer
import org.rekotlin.Store

val store = Store(
  reducer = ::rootReducer,
  state = null,
  middleware = listOf(appMiddleware),
  automaticallySkipRepeats = false
)

class MainApplication : Application()