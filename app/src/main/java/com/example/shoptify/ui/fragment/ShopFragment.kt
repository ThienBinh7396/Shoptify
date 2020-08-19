package com.example.shoptify.ui.fragment

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import com.example.shoptify.model.Breadcrumb
import com.example.shoptify.R
import com.example.shoptify.databinding.FragmentShopBinding
import com.example.shoptify.viewModel.BreadcrumbLayoutViewModel
import com.example.shoptify.viewModel.FragmentShopViewModel

class ShopFragment : Fragment(),
  IShopFragmentEventListener {
  private lateinit var listBreadcrumb: MutableList<Breadcrumb>

  private lateinit var mBreadcrumbLayoutViewModel: BreadcrumbLayoutViewModel

  private lateinit var mFragmentShopBinding: FragmentShopBinding

  private lateinit var mFragmentShopViewModel: FragmentShopViewModel

  override fun onCreateView(
    inflater: LayoutInflater, container: ViewGroup?,
    savedInstanceState: Bundle?
  ): View? {
    mFragmentShopBinding = DataBindingUtil.inflate(
      inflater,
      R.layout.fragment_shop,
      container,
      false
    )

    mFragmentShopViewModel = FragmentShopViewModel(this)
    mFragmentShopBinding.mFragmentShopViewModel = mFragmentShopViewModel

    initView()

    return mFragmentShopBinding.root
  }

  private fun initView() {
    listBreadcrumb = mutableListOf(
      Breadcrumb("Home"),
      Breadcrumb("Products")
    )

    mBreadcrumbLayoutViewModel = BreadcrumbLayoutViewModel(listBreadcrumb, "Products")

    mFragmentShopBinding.layoutBreadcrumb.mBreadcrumbLayoutViewModel = mBreadcrumbLayoutViewModel
  }

  override fun onToggleDisplayTypeClickListener() {

    Log.d("Binh", "Toggle")
    mFragmentShopViewModel.toggleDisplayType()
  }
}

interface IShopFragmentEventListener {
  fun onToggleDisplayTypeClickListener()
}