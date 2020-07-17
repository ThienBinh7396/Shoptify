package com.example.shoptify

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.shoptify.Fragment.HomeFragment
import com.example.shoptify.Fragment.RecyclerItemClickListener
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
    rcvNavbarAdapter = RecyclerViewNavbarAdapter()
    navbarLinearLayoutManager = LinearLayoutManager(this@MainActivity)

    rcvNavbar.layoutManager = navbarLinearLayoutManager
    rcvNavbar.adapter = rcvNavbarAdapter

    rcvNavbar.addOnItemTouchListener(
      RecyclerItemClickListener(
        this,
        object : RecyclerItemClickListener.OnItemClickListener {
          override fun onItemClick(view: View?, position: Int) {
            Toast.makeText(this@MainActivity, "${position}", Toast.LENGTH_SHORT).show()
          }
        })
    )


    supportFragmentManager.beginTransaction()
      .replace(R.id.flMain, HomeFragment())
      .commit()
  }
}