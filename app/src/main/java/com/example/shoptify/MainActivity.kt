package com.example.shoptify

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.shoptify.RecyclerView.RecyclerItemClickListener
import com.example.shoptify.RecyclerView.RecyclerViewNavbarAdapter
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
  lateinit var rcvNavbarAdapter: RecyclerViewNavbarAdapter
  lateinit var navbarLinearLayoutManager: LinearLayoutManager
  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContentView(R.layout.activity_main)

    initView()
  }

  private fun initView() {
    setSupportActionBar(findViewById(R.id.tbApp))

    rcvNavbarAdapter = RecyclerViewNavbarAdapter()
    navbarLinearLayoutManager = LinearLayoutManager(this@MainActivity)

    rcvNavbar.layoutManager = navbarLinearLayoutManager
    rcvNavbar.adapter = rcvNavbarAdapter

    rcvNavbar.addOnItemTouchListener(
      RecyclerItemClickListener(
        this,
        object :
          RecyclerItemClickListener.OnItemClickListener {
          override fun onItemClick(view: View?, position: Int) {
            Constant.updateNavActive(position)
            rcvNavbarAdapter.notifyDataSetChanged()
            switchFragment()
          }
        })
    )
    switchFragment()
  }

  private fun switchFragment() {
    var fragmentTransaction = supportFragmentManager.beginTransaction()
    fragmentTransaction.setCustomAnimations(R.anim.enter_slide_fade_effect_from_half_size, R.anim.exit_slide_fade_effect_from_half_size)
    fragmentTransaction.replace(R.id.flMain, Constant.switchFragmentByTitle())
      .commit()
  }
}