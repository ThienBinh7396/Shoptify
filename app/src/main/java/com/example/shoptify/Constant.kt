package com.example.shoptify

import androidx.fragment.app.Fragment
import com.example.shoptify.Fragment.HomeFragment
import com.example.shoptify.Fragment.ShopFragment

data class Navbar(val title: String, val subNav: MutableList<Navbar>, var isActive: Boolean = false)

class Constant {
  companion object {
    private var CURRENT_NAV_BAR_ACTIVE = 0

    var NAV_BAR = mutableListOf(
      Navbar("Home", mutableListOf(), true),
      Navbar("Shop", mutableListOf())
    )

    fun updateNavActive(navPosition: Int) {
      if(navPosition == CURRENT_NAV_BAR_ACTIVE) return

      NAV_BAR[navPosition].isActive = true
      NAV_BAR[CURRENT_NAV_BAR_ACTIVE].isActive = false

      CURRENT_NAV_BAR_ACTIVE = navPosition
    }

    fun switchFragmentByTitle(): Fragment{
      return when(NAV_BAR[CURRENT_NAV_BAR_ACTIVE].title){
        "Home" ->
          HomeFragment()
        "Shop" ->
          ShopFragment()
        else ->
          HomeFragment()
      }
    }

  }
}