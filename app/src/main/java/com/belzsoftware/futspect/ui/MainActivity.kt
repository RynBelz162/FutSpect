package com.belzsoftware.futspect.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.NavController
import androidx.navigation.findNavController
import androidx.navigation.ui.NavigationUI.onNavDestinationSelected
import androidx.navigation.ui.setupWithNavController
import com.belzsoftware.futspect.R
import com.belzsoftware.futspect.databinding.ActivityMainBinding
import com.belzsoftware.futspect.util.extensions.hideView
import com.belzsoftware.futspect.util.extensions.showView
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private var _binding: ActivityMainBinding? = null
    private val binding get() = _binding!!

    val coordinator get() = binding.coordinator
    val navigationMain get() = binding.navigationMain

    private val navController: NavController by lazy {
        findNavController(R.id.nav_host_fragment)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        setTheme(R.style.Theme_FutSpect)
        super.onCreate(savedInstanceState)

        _binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setUpNav()
    }


    override fun onSupportNavigateUp(): Boolean =
        navController.navigateUp()

    private fun setUpNav() {
        binding.navigationMain.setupWithNavController(navController)
        binding.navigationMain.setOnNavigationItemSelectedListener { item ->
            onNavDestinationSelected(item, navController)
        }

        navController.addOnDestinationChangedListener { _, destination, _ ->
            when (destination.id) {
                R.id.navigation_leagues,
                R.id.navigation_fixtures,
                R.id.navigation_more,
                R.id.navigation_league_filters -> binding.navigationMain.showView()
                else -> binding.navigationMain.hideView()
            }
        }
    }
}
