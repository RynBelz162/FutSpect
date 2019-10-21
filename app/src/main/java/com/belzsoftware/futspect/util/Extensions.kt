package com.belzsoftware.futspect.util

import androidx.lifecycle.ViewModel
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider

inline fun <reified VM : ViewModel> Fragment.viewModelProvider(
    provider: ViewModelProvider.Factory
) =
    ViewModelProvider(this, provider).get(VM::class.java)