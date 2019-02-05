package com.belzsoftware.futspect

import android.os.Bundle
import android.support.design.widget.BottomNavigationView
import android.support.v7.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private val mOnNavigationItemSelectedListener = BottomNavigationView.OnNavigationItemSelectedListener { item ->
        when (item.itemId) {
            R.id.navigation_tables -> {
                message.setText(R.string.title_tables)
                return@OnNavigationItemSelectedListener true
            }
            R.id.navigation_fixtures -> {
                message.setText(R.string.title_fixtures)
                return@OnNavigationItemSelectedListener true
            }
            R.id.navigation_menu -> {
                message.setText(R.string.title_menu)
                return@OnNavigationItemSelectedListener true
            }
        }
        false
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener)
    }
}
