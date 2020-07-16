package com.example.shoptify

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
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

    fun initView(){
        rcvNavbarAdapter = RecyclerViewNavbarAdapter()
        navbarLinearLayoutManager = LinearLayoutManager(this@MainActivity)

        rcvNavbar.layoutManager = navbarLinearLayoutManager
        rcvNavbar.adapter = rcvNavbarAdapter
    }
}