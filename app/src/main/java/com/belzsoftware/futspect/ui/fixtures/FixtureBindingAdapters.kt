package com.belzsoftware.futspect.ui.fixtures

import android.view.View
import androidx.databinding.BindingAdapter
import com.belzsoftware.futspect.util.hideView
import com.belzsoftware.futspect.util.showView

private const val NOT_STARTED = "NS"

@BindingAdapter("infoVisibility")
fun infoVisibility(view: View, description: String?) {
    val nonNullDescription = description ?: return

    when (nonNullDescription) {
        NOT_STARTED -> view.showView()
        else -> view.hideView()
    }
}

@BindingAdapter("scoreVisibility")
fun scoreVisibility(view: View, description: String?) {
    val nonNullDescription = description ?: return

    when (nonNullDescription) {
        NOT_STARTED -> view.hideView()
        else -> view.showView()
    }
}