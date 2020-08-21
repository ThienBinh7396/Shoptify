package com.example.shoptify.ui.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.shoptify.databinding.FragmentHomeBinding
import com.example.shoptify.viewModel.FragmentHomeViewModel

class HomeFragment : Fragment() {
  private lateinit var mFragmentHomeBinding: FragmentHomeBinding

  override fun onCreateView(
    inflater: LayoutInflater, container: ViewGroup?,
    savedInstanceState: Bundle?
  ): View? {
    mFragmentHomeBinding = FragmentHomeBinding.inflate(
      inflater,
      container,
      false
    ).apply {
      mFragmentHomeViewModel = FragmentHomeViewModel()
    }

    return mFragmentHomeBinding.root
  }

}