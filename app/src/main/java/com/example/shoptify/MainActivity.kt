package com.example.shoptify

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.navigation.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.shoptify.common.HelperData
import com.example.shoptify.ui.fragment.AppbarFragment
import com.example.shoptify.adapter.recyclerView.RecyclerItemClickListener
import com.example.shoptify.adapter.recyclerView.NavbarAdapter
import com.example.shoptify.databinding.ActivityMainBinding
import com.example.shoptify.store.action.AppAction
import com.example.shoptify.store.state.AppState
import kotlinx.android.synthetic.main.activity_main.*
import org.rekotlin.StoreSubscriber

class MainActivity : AppCompatActivity(), StoreSubscriber<AppState> {
  private lateinit var rcvNavbarAdapter: NavbarAdapter
  private lateinit var navbarLinearLayoutManager: LinearLayoutManager

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)

    DataBindingUtil.setContentView<ActivityMainBinding>(this, R.layout.activity_main)

    initStoreData()
    initViewData()
    initViewListener()
  }

  private fun initStoreData() {
    checkIsInitializedStoreDataAndFetch()
  }

  private fun initViewData() {
    store.dispatch(AppAction.INITIALIZE_LOADING_DIALOG(this@MainActivity))

    HelperData.currentNavController = findNavController(R.id.nav_host)

    rcvNavbarAdapter = NavbarAdapter()
    HelperData.rcvNavbarAdapter = rcvNavbarAdapter

    navbarLinearLayoutManager = LinearLayoutManager(this@MainActivity)

    rcvNavbar.layoutManager = navbarLinearLayoutManager
    rcvNavbar.adapter = rcvNavbarAdapter

    (frAppbar as AppbarFragment).updateDrawerLayout(dlMenu)
  }

  private fun initViewListener() {
    rcvNavbar.addOnItemTouchListener(
      RecyclerItemClickListener(
        this,
        object :
          RecyclerItemClickListener.OnItemClickListener {
          override fun onItemClick(view: View?, position: Int) {
            HelperData.updateNavActive(position)
            rcvNavbarAdapter.notifyDataSetChanged()

            HelperData.navigateByTitle(HelperData.NAV_BAR[position].title)
          }
        })
    )
  }

  private fun checkIsInitializedStoreDataAndFetch() {
    store.state.appState.apply {
      if (productListResponse == null && !isFirstFetchData)
        store.dispatch(AppAction.FETCH_PRODUCTS_DATA())

      if (categoryListResponse == null && !isFirstFetchData)
        store.dispatch(AppAction.FETCH_CATEGORIES_DATA())

      if (vendorListResponse == null && !isFirstFetchData)
        store.dispatch(AppAction.FETCH_VENDORS_DATA())

      if (productStatusListResponse == null && !isFirstFetchData)
        store.dispatch(AppAction.FETCH_PRODUCT_STATUS_DATA())

      if (productTopSaleResponse == null && !isFirstFetchData)
        store.dispatch(AppAction.FETCH_TOP_SALE_PRODUCTS_DATA())
    }
  }

  override fun newState(state: AppState) {
    state.apply {
      if (state.isShowLoadingDialog && state.loadingDialog != null) {
        state.loadingDialog!!.showDialog()
      }

      if (!isFirstFetchData && categoryListResponse != null && productListResponse != null && productTopSaleResponse != null && vendorListResponse != null && productStatusListResponse != null) {
        store.dispatch(AppAction.UPDATE_IS_FIRST_FETCH_DATA(true))
        store.dispatch(AppAction.UPDATE_IS_SHOW_LOADING_DIALOG(false))
      }

      if (!state.isShowLoadingDialog && state.loadingDialog != null) {
        state.loadingDialog!!.hideDialog()
      }
    }
  }

  override fun onStart() {
    super.onStart()

    store.subscribe(this) {
      it.select {
        it.appState
      }
    }
  }

  override fun onDestroy() {
    super.onDestroy()

    HelperData.currentNavController = null

    store.unsubscribe(this)
  }
}