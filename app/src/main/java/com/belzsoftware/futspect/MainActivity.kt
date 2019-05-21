package com.belzsoftware.futspect

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.belzsoftware.futspect.ui.fixtures.FixturesFragment
import com.belzsoftware.futspect.ui.more.MoreFragment
import com.belzsoftware.futspect.ui.tables.TableFragment
import com.google.android.material.bottomnavigation.BottomNavigationView
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private val mOnNavigationItemSelectedListener = BottomNavigationView.OnNavigationItemSelectedListener { item ->
        when (item.itemId) {
            R.id.navigation_tables -> {
                replaceFragment(TableFragment())
                return@OnNavigationItemSelectedListener true
            }
            R.id.navigation_fixtures -> {
                replaceFragment(FixturesFragment())
                return@OnNavigationItemSelectedListener true
            }
            R.id.navigation_menu -> {
                replaceFragment(MoreFragment())
                return@OnNavigationItemSelectedListener true
            }
        }
        false
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        replaceFragment(TableFragment())
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener)
    }

    private fun replaceFragment(fragment: Fragment) {
        supportFragmentManager.beginTransaction()
            .replace(R.id.main_content, fragment)
            .commit()
    }
}
