package com.belzsoftware.futspect.ui

import android.os.Bundle
import androidx.navigation.NavController
import androidx.navigation.findNavController
import androidx.navigation.ui.NavigationUI.onNavDestinationSelected
import androidx.navigation.ui.setupWithNavController
import com.belzsoftware.futspect.R
import com.belzsoftware.futspect.util.hideView
import com.belzsoftware.futspect.util.showView
import dagger.android.AndroidInjection
import dagger.android.support.DaggerAppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : DaggerAppCompatActivity() {

    private val navController: NavController by lazy {
        findNavController(R.id.nav_host_fragment)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        AndroidInjection.inject(this)

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        setUpNav()
    }

    override fun onSupportNavigateUp(): Boolean =
        navController.navigateUp()

    private fun setUpNav() {
        navigation.setupWithNavController(navController)
        navigation.setOnNavigationItemSelectedListener { item ->
            onNavDestinationSelected(item, navController)
        }

        navController.addOnDestinationChangedListener { _, destination, _ ->
            when (destination.id) {
                R.id.navigation_leagues,
                R.id.navigation_fixtures,
                R.id.navigation_more -> navigation.showView()
                else -> navigation.hideView()
            }
        }
    }
}
