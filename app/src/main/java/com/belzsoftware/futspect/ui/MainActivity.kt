package com.belzsoftware.futspect.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.NavController
import androidx.navigation.findNavController
import androidx.navigation.ui.NavigationUI.onNavDestinationSelected
import androidx.navigation.ui.setupWithNavController
import com.belzsoftware.futspect.R
import com.belzsoftware.futspect.util.extensions.hideView
import com.belzsoftware.futspect.util.extensions.showView
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.activity_main.*

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private val navController: NavController by lazy {
        findNavController(R.id.nav_host_fragment)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        setTheme(R.style.Theme_FutSpect)
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        setUpNav()
    }

    override fun onSupportNavigateUp(): Boolean =
        navController.navigateUp()

    private fun setUpNav() {
        navigation_main.setupWithNavController(navController)
        navigation_main.setOnNavigationItemSelectedListener { item ->
            onNavDestinationSelected(item, navController)
        }

        navController.addOnDestinationChangedListener { _, destination, _ ->
            when (destination.id) {
                R.id.navigation_leagues,
                R.id.navigation_fixtures,
                R.id.navigation_more,
                R.id.navigation_league_filters -> navigation_main.showView()
                else -> navigation_main.hideView()
            }
        }
    }
}
