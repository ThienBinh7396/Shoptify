package com.example.shoptify

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.navigation.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.shoptify.common.HelperData
import com.example.shoptify.ui.fragment.AppbarFragment
import com.example.shoptify.adapter.recyclerView.RecyclerItemClickListener
import com.example.shoptify.adapter.recyclerView.RecyclerViewNavbarAdapter
import com.example.shoptify.databinding.ActivityMainBinding
import com.example.shoptify.store.action.AppAction
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity(){
  private lateinit var rcvNavbarAdapter: RecyclerViewNavbarAdapter
  private lateinit var navbarLinearLayoutManager: LinearLayoutManager

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)

    DataBindingUtil.setContentView<ActivityMainBinding>(this, R.layout.activity_main)

    initViewData()
    initViewListener()
  }

  override fun onDestroy() {
    super.onDestroy()

    HelperData.currentNavController = null
  }

  private fun initViewData() {
    HelperData.currentNavController = findNavController(R.id.nav_host)

    rcvNavbarAdapter = RecyclerViewNavbarAdapter()
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
}