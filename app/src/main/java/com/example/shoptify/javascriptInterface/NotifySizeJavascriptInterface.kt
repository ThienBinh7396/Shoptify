package com.example.shoptify.javascriptInterface

import android.util.Log
import android.webkit.JavascriptInterface
import android.webkit.WebView

class NotifySizeJavascriptInterface(var webView: WebView) {
  @JavascriptInterface
  fun notifySizeWindow(height: Float){
    Log.d("Binh", "Height: $height, $webView")
  }
}