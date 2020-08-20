package com.example.shoptify.ui.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import com.example.shoptify.R
import com.example.shoptify.databinding.FragmentHomeBinding

class HomeFragment : Fragment() {
  private lateinit var mFragmentHomeBinding: FragmentHomeBinding

  override fun onCreateView(
    inflater: LayoutInflater, container: ViewGroup?,
    savedInstanceState: Bundle?
  ): View? {
    mFragmentHomeBinding = DataBindingUtil.inflate(
      inflater,
      R.layout.fragment_home,
      container,
      false
    )

    return mFragmentHomeBinding.root
  }

}