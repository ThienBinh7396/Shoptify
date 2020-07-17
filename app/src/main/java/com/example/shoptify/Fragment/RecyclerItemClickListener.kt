package com.example.shoptify.Fragment

import android.content.Context
import android.view.GestureDetector
import android.view.GestureDetector.SimpleOnGestureListener
import android.view.MotionEvent
import android.view.View
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.OnItemTouchListener

class RecyclerItemClickListener(
  context: Context?,
  // listener
  private val mListener: OnItemClickListener?
) : OnItemTouchListener {

  interface OnItemClickListener {
    fun onItemClick(view: View?, position: Int)
  }

  var mGestureDetector: GestureDetector
  override fun onInterceptTouchEvent(view: RecyclerView, e: MotionEvent): Boolean {
    // get View child clicked by position X Y
    val childView = view.findChildViewUnder(e.x, e.y)
    // if listener is not null, up event to the listener
    if (childView != null && mListener != null && mGestureDetector.onTouchEvent(e)) {
      // call onItemClick
      mListener.onItemClick(childView, view.getChildPosition(childView))
    }
    return false
  }

  override fun onTouchEvent(view: RecyclerView, motionEvent: MotionEvent) {}
  override fun onRequestDisallowInterceptTouchEvent(disallowIntercept: Boolean) {}

  init {
    // create GestureDetector
    mGestureDetector = GestureDetector(context, object : SimpleOnGestureListener() {
      override fun onSingleTapUp(e: MotionEvent): Boolean {
        // detect a single tap up
        return true
      }
    })
  }
}