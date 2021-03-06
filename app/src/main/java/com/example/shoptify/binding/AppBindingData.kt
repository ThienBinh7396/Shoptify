package com.example.shoptify.binding

import android.annotation.SuppressLint
import android.content.res.Resources
import android.os.Handler
import android.os.Looper
import android.util.DisplayMetrics
import android.util.Log
import android.view.View
import android.view.ViewGroup
import android.webkit.JavascriptInterface
import android.webkit.WebSettings
import android.webkit.WebView
import android.webkit.WebViewClient
import android.widget.ImageView
import android.widget.TextView
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ItemAnimator
import androidx.recyclerview.widget.SimpleItemAnimator
import androidx.transition.AutoTransition
import androidx.transition.TransitionManager
import com.bumptech.glide.request.RequestOptions
import com.example.shoptify.GlideApp
import com.example.shoptify.R
import com.example.shoptify.adapter.recyclerView.*
import com.example.shoptify.common.*
import com.example.shoptify.javascriptInterface.NotifySizeJavascriptInterface
import com.example.shoptify.javascriptInterface.NotifyUrlJavascriptInterface
import com.example.shoptify.model.Product

class AppBindingData {
  companion object {
    @BindingAdapter("app:bindPriceText")
    @JvmStatic
    fun bindPriceText(textView: TextView, price: Float) {
      textView.text = "$$price"
    }

    @SuppressLint("SetJavaScriptEnabled")
    @BindingAdapter("app:bindTextHtml")
    @JvmStatic
    fun bindTextHtml(webView: WebView, str: Any) {
      Log.d("Binh", "Start loading webview...")

      webView.settings.apply {
        layoutAlgorithm = WebSettings.LayoutAlgorithm.TEXT_AUTOSIZING
        loadWithOverviewMode = true

        cacheMode = WebSettings.LOAD_CACHE_ELSE_NETWORK
        webView.setLayerType(View.LAYER_TYPE_HARDWARE, null)
        javaScriptEnabled = true
      }

      val html = "Hii cả nhà, \n" +
          "Team truyền thông đã nhận được khá nhiều ý kiến đóng góp qua khảo sát đầu tuần. Những ý kiến dù chỉ rất đơn giản thôi nhưng thật sự ý nghĩa đối với team \uD83D\uDE0D\n" +
          "Tuy nhiên, team TT mong muốn được nhận nhiều kết quả khảo sát nhất có thể, để thu thập ý kiến của đông đảo anh chị em trong công ty. Vì vậy anh chị em nào chưa làm khảo sát thì hãy bấm ngay vào link này nha ạ: <a href='https://forms.gle/R9TtKDuvj2AXq1cD6'>https://forms.gle/R9TtKDuvj2AXq1cD6</a>"

      webView.loadDataWithBaseURL(null, html, "text/html", "UTF-8", null)
      webView.webViewClient = object : WebViewClient() {
        override fun onPageFinished(view: WebView?, url: String?) {

          Log.d("Binh", "Loading finish: $url")
          webView.loadUrl(
            "javascript:(function(){setTimeout(() => {document.querySelectorAll('a').forEach(_a => {" +
                " _a.addEventListener('click', (e) => {e.preventDefault(); window.NotifyUrl.toUrl(_a.getAttribute('href'))});" +
                "});" +
                "}, 100)})()"
          )
          super.onPageFinished(view, url)
        }
      }
      webView.addJavascriptInterface(NotifyUrlJavascriptInterface(webView), "NotifyUrl")
    }

    @BindingAdapter(value = ["app:bindSrcImage", "app:centerScaleType"], requireAll = false)
    @JvmStatic
    fun bindSrcImage(imageView: ImageView, srcImage: Any?, centerScaleType: Boolean = false) {
      if (srcImage != null) {
        var glide = GlideApp.with(imageView.context)
          .load(srcImage)
          .placeholder(R.drawable.image_placeholder)

        if (centerScaleType) {
          glide = glide.fitCenter()
        }

        glide.into(imageView)
      }
    }

    @BindingAdapter("app:bindBannerHome")
    @JvmStatic
    fun bindBannerHome(rcv: RecyclerView, data: Any) {
      if (rcv.adapter == null) {

        val layoutManager = GridLayoutManager(rcv.context, 1, GridLayoutManager.VERTICAL, true)

        rcv.adapter = BannerHomeAdapter()

        rcv.layoutManager = layoutManager

      }
    }
    @BindingAdapter("app:bindBlockService")
    @JvmStatic
    fun bindBlockService(rcv: RecyclerView, data: Any) {
      if (rcv.adapter == null) {

        val layoutManager = GridLayoutManager(rcv.context, 1, GridLayoutManager.VERTICAL, false)

        rcv.adapter = BlockServicesAdapter()

        rcv.layoutManager = layoutManager

      }
    }

    @BindingAdapter("app:bindAccordionSingleData")
    @JvmStatic
    fun bindAccordionSingleData(rcv: RecyclerView, data: MutableList<AccordionDataModel>) {
      val animator: ItemAnimator? = rcv.itemAnimator

      if (animator is SimpleItemAnimator) {
        animator.supportsChangeAnimations = false
      }

      if (rcv.adapter == null) {
        rcv.adapter = AccordionSingleDataAdapter(data)

        val layoutManager = GridLayoutManager(rcv.context, 1)
        layoutManager.orientation = GridLayoutManager.VERTICAL

        rcv.layoutManager = layoutManager

      } else {
        (rcv.adapter as AccordionSingleDataAdapter).updateList(data)
      }
    }

    @BindingAdapter("app:bindAccordionProductData")
    @JvmStatic
    fun bindAccordionProductData(rcv: RecyclerView, data: MutableList<AccordionListDataModel>) {
      if (rcv.adapter == null) {
        rcv.adapter = AccordionProductDataAdapter(data, rcv.context)
        val layoutManager = GridLayoutManager(rcv.context, 1)
        layoutManager.orientation = GridLayoutManager.VERTICAL

        rcv.layoutManager = layoutManager
      } else {
        (rcv.adapter as AccordionProductDataAdapter).updateList(data)
      }
    }

    @BindingAdapter(value = ["app:bindShortProductBlock", "app:bindTypeDisplay"], requireAll = false)
    @JvmStatic
    fun bindShortProductBlock(
      rcv: RecyclerView,
      data: MutableList<Product>,
      type: Int = DISPLAY_GRID
    ) {
      var adapter = if (rcv.adapter == null) null else rcv.adapter as ShortProductBlockAdapter

      if (adapter == null) {
        adapter = ShortProductBlockAdapter(type)

        val layoutManager = GridLayoutManager(rcv.context, 1)
        layoutManager.orientation = GridLayoutManager.VERTICAL

        rcv.layoutManager = layoutManager
        rcv.adapter = adapter
      }

      if (adapter.checkIsNewTypeDisplay(type)) {
        adapter = ShortProductBlockAdapter(type)
        rcv.adapter = adapter
      }


      adapter.updateList(data, type)
    }

    @BindingAdapter("app:bindTextToCapitalize")
    @JvmStatic
    fun bindTextToCapitalize(textView: TextView, str: String) {
      textView.text = HelperMethod.textToCapitalize(str)
    }

    @BindingAdapter("app:bindCollapsedGroupView")
    @JvmStatic
    fun bindCollapsedGroupView(viewGroup: ViewGroup, isCollapsed: Boolean) {
      viewGroup.visibility = if (isCollapsed) View.VISIBLE else View.GONE

      TransitionManager.beginDelayedTransition(viewGroup, AutoTransition())

      viewGroup.requestLayout()
    }
  }
}