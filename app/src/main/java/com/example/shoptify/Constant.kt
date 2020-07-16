package com.example.shoptify

data class Navbar(val title: String, val subNav: MutableList<Navbar>)

class Constant {
    companion object{
        val NAV_BAR = mutableListOf(
            Navbar("Home", mutableListOf()),
            Navbar("Shop", mutableListOf())
        )

    }
}