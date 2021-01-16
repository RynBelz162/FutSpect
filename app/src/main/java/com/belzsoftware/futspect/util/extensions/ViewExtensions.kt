package com.belzsoftware.futspect.util.extensions

import android.content.Context
import android.util.TypedValue
import android.view.View
import androidx.annotation.AttrRes
import androidx.annotation.ColorInt
import androidx.annotation.StringRes
import androidx.fragment.app.FragmentActivity
import com.belzsoftware.futspect.ui.MainActivity
import com.google.android.material.snackbar.Snackbar

fun View.showView() {
    visibility = View.VISIBLE
}

fun View.hideView() {
    visibility = View.GONE
}

fun FragmentActivity.createLongSnackbar(message: String) {
    createSnackBar(this, message)
}

fun FragmentActivity.createLongSnackbar(@StringRes stringId: Int) {
    val message: String = getString(stringId)

    createSnackBar(this, message)
}

private fun createSnackBar(activity: FragmentActivity, message: String) {
    if (activity !is MainActivity) {
        // don't show a toast if not in main activity lifecycle
        return
    }

    Snackbar.make(activity.coordinator, message, Snackbar.LENGTH_LONG).apply {
        anchorView = activity.navigationMain
        show()
    }
}

@ColorInt
fun getAttrColor(context: Context, @AttrRes id: Int): Int {
    val typeValue = TypedValue()
    context.theme.resolveAttribute(id, typeValue, true)
    return typeValue.data
}