package com.example.shoptify.adapter.recyclerView

import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.LinearLayout
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.shoptify.common.HelperData
import com.example.shoptify.common.Navbar
import com.example.shoptify.R

class NavbarAdapter :
  RecyclerView.Adapter<NavbarAdapter.NavbarAdapterViewHolder>() {
  class NavbarAdapterViewHolder(private val view: View) : RecyclerView.ViewHolder(view){
    private var tvNav: TextView = view.findViewById(R.id.tvNav)
    private var ibnCollapse: ImageButton = view.findViewById(R.id.ibnCollapse)
    private var llNavbarContainer = view.findViewById<LinearLayout>(R.id.llNavbarContainer)

    fun initView(nav: Navbar) {
      tvNav.text = nav.title
      tvNav.setTextColor(if (nav.isActive) Color.WHITE else Color.BLACK)
      ibnCollapse.visibility = if (nav.subNav.size > 0) View.VISIBLE else View.INVISIBLE

      llNavbarContainer.setBackgroundResource(
        if (nav.isActive) R.color.colorBackgroundActive
        else R.color.colorBackground
      )

    }
  }

  override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NavbarAdapterViewHolder {
    val view = LayoutInflater.from(parent.context)
      .inflate(R.layout.recyclerview_navbar, parent, false)
    return NavbarAdapterViewHolder(view)
  }

  override fun getItemCount(): Int = HelperData.NAV_BAR.size

  override fun onBindViewHolder(holder: NavbarAdapterViewHolder, position: Int) {
    var nav = HelperData.NAV_BAR[position]
    holder.initView(nav)
  }

}