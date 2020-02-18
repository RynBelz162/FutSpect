package com.belzsoftware.futspect.util

import android.view.View
import androidx.annotation.StringRes
import androidx.fragment.app.FragmentActivity
import com.google.android.material.snackbar.Snackbar
import kotlinx.android.synthetic.main.activity_main.*

fun View.showView() {
    visibility = View.VISIBLE
}

fun View.hideView() {
    visibility = View.GONE
}

fun FragmentActivity.createLongSnackbar(message: String) {
    createSnackBar(this, message, Snackbar.LENGTH_LONG)
}

fun FragmentActivity.createLongSnackbar(@StringRes stringId: Int) {
    val message: String = getString(stringId)

    createSnackBar(this, message, Snackbar.LENGTH_LONG)
}

private fun createSnackBar(activity: FragmentActivity, message: String, length: Int) {
    Snackbar.make(activity.coordinator, message, length).apply {
        anchorView = activity.navigation_main
        show()
    }
}