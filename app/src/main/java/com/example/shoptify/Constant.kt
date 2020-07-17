package com.example.shoptify

data class Navbar(val title: String, val subNav: MutableList<Navbar>, var isActive: Boolean = false)

class Constant {
  companion object {
    private var CURRENT_NAV_BAR_ACTIVE = 0

    var NAV_BAR = mutableListOf(
      Navbar("Home", mutableListOf(), true),
      Navbar("Shop", mutableListOf())
    )

    fun updateNavActive(navPosition: Int) {
      NAV_BAR[navPosition].isActive = true
      NAV_BAR[CURRENT_NAV_BAR_ACTIVE].isActive = false

      CURRENT_NAV_BAR_ACTIVE = navPosition
    }

  }
}