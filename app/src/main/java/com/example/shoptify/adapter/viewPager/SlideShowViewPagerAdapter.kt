package com.example.shoptify.adapter.viewPager

import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentStatePagerAdapter
import com.example.shoptify.common.HelperData
import com.example.shoptify.ui.fragment.SlideShowItemFragment

class SlideShowViewPagerAdapter(fragmentManager: FragmentManager) :
  FragmentStatePagerAdapter(fragmentManager, BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT) {
  override fun getItem(position: Int) = SlideShowItemFragment.newInstance(position)

  override fun getCount() = HelperData.SLIDE_CAPTIONS.size

}