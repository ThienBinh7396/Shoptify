package com.example.shoptify.RecyclerView

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.shoptify.Constant
import com.example.shoptify.Navbar
import com.example.shoptify.R

class RecyclerViewNavbarAdapter :
    RecyclerView.Adapter<RecyclerViewNavbarAdapter.NavbarAdapterViewHolder>() {
    class NavbarAdapterViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        private var tvNav: TextView = view.findViewById(R.id.tvNav)
        private var ibnCollapse: ImageButton = view.findViewById(R.id.ibnCollapse)

        fun initView(nav: Navbar) {
            tvNav.text = nav.title
            Log.d("Binh", "${nav.title}, ${Constant.NAV_BAR.size}")
//            ibnCollapse.visibility = if (nav.subNav.size > 0) View.VISIBLE else View.INVISIBLE
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NavbarAdapterViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.recyclerview_navbar, parent, false)
        return NavbarAdapterViewHolder(view)
    }

    override fun getItemCount(): Int = Constant.NAV_BAR.size

    override fun onBindViewHolder(holder: NavbarAdapterViewHolder, position: Int) {
        var nav = Constant.NAV_BAR[position]
        holder.initView(nav)
    }

}