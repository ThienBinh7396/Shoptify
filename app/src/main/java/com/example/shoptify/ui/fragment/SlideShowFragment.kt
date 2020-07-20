package com.example.shoptify.ui.fragment

import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager.widget.ViewPager
import com.example.shoptify.common.HelperData
import com.example.shoptify.R
import com.example.shoptify.adapter.recyclerView.RecyclerItemClickListener
import com.example.shoptify.adapter.recyclerView.RecyclerViewSlideShowDotsAdapter
import com.example.shoptify.adapter.viewPager.SlideShowViewPagerAdapter

class SlideShowFragment : Fragment() {
  private var TIME_DELAY = 4500L

  private lateinit var vpSlideShow: ViewPager
  private lateinit var rcvDots: RecyclerView
  private lateinit var slideShowAdapter: SlideShowViewPagerAdapter
  private lateinit var slideShowDotAdapter: RecyclerViewSlideShowDotsAdapter
  private lateinit var linearLayoutManager: LinearLayoutManager

  private lateinit var intervalHandler: Handler
  private lateinit var intervalRunnable: Runnable

  private var index = 0

  private var isDestroyed = false

  private var isPaused = false

  override fun onCreateView(
    inflater: LayoutInflater, container: ViewGroup?,
    savedInstanceState: Bundle?
  ): View? {
    val view = inflater.inflate(R.layout.fragment_slide_show, container, false)

    vpSlideShow = view.findViewById(R.id.vpSlideShow)
    slideShowAdapter = SlideShowViewPagerAdapter(requireActivity().supportFragmentManager)
    vpSlideShow.adapter = slideShowAdapter

    rcvDots = view.findViewById(R.id.rcvDots)
    linearLayoutManager = LinearLayoutManager(requireContext())
    slideShowDotAdapter = RecyclerViewSlideShowDotsAdapter(requireContext())

    rcvDots.layoutManager = linearLayoutManager
    linearLayoutManager.orientation = LinearLayoutManager.HORIZONTAL
    rcvDots.adapter = slideShowDotAdapter

    initEventListener()

    return view
  }

  private fun initEventListener() {
    isDestroyed = false

    intervalHandler = Handler(Looper.getMainLooper())

    intervalRunnable = Runnable {
      if(isDestroyed || isPaused) return@Runnable

      index = vpSlideShow.currentItem + 1
      vpSlideShow.currentItem = if (index >= HelperData.SLIDE_CAPTIONS.size) 0 else index
      intervalHandler.postDelayed(intervalRunnable, TIME_DELAY)
    }

    intervalHandler.postDelayed(intervalRunnable, TIME_DELAY)

    vpSlideShow.addOnPageChangeListener(object : ViewPager.OnPageChangeListener {
      override fun onPageScrollStateChanged(state: Int) {
      }

      override fun onPageScrolled(position: Int, positionOffset: Float, positionOffsetPixels: Int) {
      }

      override fun onPageSelected(position: Int) {
        if(slideShowAdapter.instantiateItem(vpSlideShow, position) is SlideShowItemFragment){
          (slideShowAdapter.instantiateItem(vpSlideShow, position) as SlideShowItemFragment).makeViewAnimation()
        }

        HelperData.updateSlideCaptionActive(position)
        slideShowDotAdapter.notifyDataSetChanged()
      }
    })

    rcvDots.addOnItemTouchListener(
      RecyclerItemClickListener(
        context,
        object : RecyclerItemClickListener.OnItemClickListener {
          override fun onItemClick(view: View?, position: Int) {
            HelperData.updateSlideCaptionActive(position)
            slideShowDotAdapter.notifyDataSetChanged()
            vpSlideShow.currentItem = position
          }
        })
    )
  }

  override fun onResume() {
    super.onResume()

    isPaused = false
  }

  override fun onPause() {
    super.onPause()

    isPaused = true
    Log.d("Binh", "Destroyed slide show")
  }

  override fun onDestroy() {
    super.onDestroy()

    isDestroyed = true
    Log.d("Binh", "Destroyed slide show")
    intervalHandler.removeCallbacks(intervalRunnable)
  }
}