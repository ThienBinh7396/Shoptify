package com.example.shoptify.ui.dialog

import android.app.Activity
import android.app.Dialog
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.Window
import com.example.shoptify.R

class LoadingDialog(context: Context) {
  private var dialog: Dialog? = null

  private lateinit var rootView: View

  init {
    dialog = Dialog(context)
    dialog?.requestWindowFeature(Window.FEATURE_NO_TITLE)
    dialog?.setCancelable(false)

    rootView = LayoutInflater.from(context)
      .inflate(R.layout.loading_dialog_layout, null, false)
    dialog?.setContentView(rootView)
  }

  fun showDialog() {
    dialog?.show()
  }

  fun hideDialog() {
    dialog?.dismiss()
  }
}