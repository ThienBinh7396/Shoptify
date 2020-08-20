package com.example.shoptify.ui.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import com.example.shoptify.R
import com.example.shoptify.databinding.FragmentExclusiveBlockProductBinding

class ExclusiveBlockProduct : Fragment() {

  private lateinit var mFragmentExclusiveBlockProductBinding: FragmentExclusiveBlockProductBinding

  override fun onCreateView(
    inflater: LayoutInflater, container: ViewGroup?,
    savedInstanceState: Bundle?
  ): View? {
    mFragmentExclusiveBlockProductBinding = DataBindingUtil.inflate(
      inflater,
      R.layout.fragment_exclusive_block_product,
      container,
      false
    )
    return mFragmentExclusiveBlockProductBinding.root
  }

}