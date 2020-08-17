package com.example.shoptify.common

import androidx.navigation.NavController
import com.example.shoptify.R
import com.example.shoptify.adapter.recyclerView.NavbarAdapter

data class Navbar(
  val title: String,
  val subNav: MutableList<Navbar>,
  var isActive: Boolean = false
)

data class SlideCaption(
  val firstText: String,
  val secondText: String,
  val description: String,
  val backgroundId: Int,
  var isActive: Boolean = false
)

class HelperData {
  companion object {
    private var CURRENT_NAV_BAR_ACTIVE = 0
    private var CURRENT_SLIDE_CAPTION_ACTIVE = 0

    var NAV_BAR = mutableListOf(
      Navbar("Home", mutableListOf(), true),
      Navbar("Shop", mutableListOf())
    )

    private var currentNavTitle = "Home"
    var currentNavController: NavController? = null
    var rcvNavbarAdapter: NavbarAdapter? =null

    var mapNavTitleWithNavigationAction = mapOf(
      "Home" to mapOf(
        "Shop" to R.id.action_homeFragment_to_shopFragment
      ),
      "Shop" to mapOf(
        "Home" to R.id.action_shopFragment_to_homeFragment
      )
    )

    var SLIDE_CAPTIONS = mutableListOf(
      SlideCaption(
        "WOMEN SHOES", "COLLECTION", "NEW STREET STYLE 2016",
        R.drawable.slideshow_image_2,
        isActive = true
      ),
      SlideCaption(
        "GREAT", "PERFORMANCE", "NEW STREET STYLE 2018",
        R.drawable.slideshow_image_1
      ),
      SlideCaption(
        "MEN SHOES", "COMFORTABLE", "NEW STREET STYLE 2020",
        R.drawable.slideshow_image_3
      )
    )

    fun updateNavActive(navPosition: Int, updateNavAdapter: Boolean = true) {
      if (navPosition == CURRENT_NAV_BAR_ACTIVE || navPosition == -1) return

      NAV_BAR[navPosition].isActive = true
      NAV_BAR[CURRENT_NAV_BAR_ACTIVE].isActive = false

      CURRENT_NAV_BAR_ACTIVE = navPosition

      if(updateNavAdapter && rcvNavbarAdapter != null){
        rcvNavbarAdapter?.notifyDataSetChanged()
      }
    }

    fun findNavBarByTitle(title: String): Int {
      var findIndex = -1

      NAV_BAR.forEachIndexed { index, nav ->
        if (nav.title === title){
          findIndex = index
        }
      }

      return findIndex
    }

    fun updateSlideCaptionActive(slidePosition: Int) {
      if (slidePosition == CURRENT_SLIDE_CAPTION_ACTIVE) return

      SLIDE_CAPTIONS[slidePosition].isActive = true
      SLIDE_CAPTIONS[CURRENT_SLIDE_CAPTION_ACTIVE].isActive = false

      CURRENT_SLIDE_CAPTION_ACTIVE = slidePosition
    }

    fun navigateByTitle(toNavTitle: String) {
      val findNavigationActionId = mapNavTitleWithNavigationAction[currentNavTitle]?.get(toNavTitle)

      if (findNavigationActionId != null) {
        currentNavController?.navigate(findNavigationActionId)
        currentNavTitle = toNavTitle

        val findNavIndex = findNavBarByTitle(toNavTitle)

        if(findNavIndex > -1){
          updateNavActive(findNavIndex)
        }
      }
    }
  }
}