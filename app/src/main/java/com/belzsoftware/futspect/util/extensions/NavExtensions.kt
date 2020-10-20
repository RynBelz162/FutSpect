package com.belzsoftware.futspect.util.extensions

import androidx.annotation.StringRes
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.NavigationUI
import kotlinx.android.synthetic.main.fragment_fixture_info.*

fun Fragment.setUpToolbar(@StringRes titleId: Int) {
    NavigationUI.setupWithNavController(
        toolbarLayout_fixtureInfo,
        NavHostFragment.findNavController(this)
    )
    toolbarLayout_fixtureInfo.title = resources.getString(titleId)
}