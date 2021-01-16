package com.belzsoftware.futspect.util.extensions

import androidx.annotation.StringRes
import androidx.appcompat.widget.Toolbar
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.NavigationUI

fun Fragment.setUpToolbar(toolbar: Toolbar, @StringRes titleId: Int) {

    NavigationUI.setupWithNavController(
        toolbar,
        NavHostFragment.findNavController(this)
    )
    toolbar.title = resources.getString(titleId)
}