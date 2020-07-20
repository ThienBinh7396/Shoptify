package com.example.shoptify.ui.fragment

import android.os.Bundle
import android.util.Log
import android.view.Gravity
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.TextView
import androidx.drawerlayout.widget.DrawerLayout
import com.example.shoptify.R
import com.example.shoptify.store.action.ProductAction
import com.example.shoptify.store

class AppbarFragment : Fragment() {
  lateinit var ibnToggleMenu: ImageButton
  lateinit var ibnCart: ImageButton
  lateinit var tvCountProduct: TextView
  var drawerLayout: DrawerLayout? = null

  override fun onCreateView(
    inflater: LayoutInflater, container: ViewGroup?,
    savedInstanceState: Bundle?
  ): View? {
    val view = inflater.inflate(R.layout.fragment_appbar, container, false)

    ibnToggleMenu = view.findViewById(R.id.ibnToggleMenu)
    ibnCart = view.findViewById(R.id.ibnCart)
    tvCountProduct = view.findViewById(R.id.tvCountProduct)

    initListener()

    return view
  }

  fun updateDrawerLayout(_drawerLayout: DrawerLayout) {
    drawerLayout = _drawerLayout

    Log.d("Binh", "Update drawer")
  }

  private fun initListener() {
    ibnToggleMenu.setOnClickListener {
      drawerLayout?.openDrawer(Gravity.LEFT)

    }

    ibnCart.setOnClickListener {
      store.dispatch(ProductAction.UPDATE_NUM(store.state.productState.num + 1))
    }
  }

}