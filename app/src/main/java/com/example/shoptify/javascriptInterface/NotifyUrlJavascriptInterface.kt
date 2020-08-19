package com.example.shoptify.javascriptInterface

import android.content.Intent
import android.util.Log
import android.webkit.JavascriptInterface
import android.webkit.WebView
import com.example.shoptify.UrlActivity

class NotifyUrlJavascriptInterface(var webView: WebView) {
  @JavascriptInterface
  fun toUrl(url: String){
    val intent = Intent(webView.context, UrlActivity::class.java)

    webView.context.startActivity(intent)

    Log.d("Binh", "Url: $url")
  }
}