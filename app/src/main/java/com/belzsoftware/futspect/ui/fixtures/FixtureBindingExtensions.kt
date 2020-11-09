package com.belzsoftware.futspect.ui.fixtures

import android.view.View
import com.belzsoftware.futspect.util.extensions.hideView
import com.belzsoftware.futspect.util.extensions.showView

private const val NOT_STARTED = "NS"

fun infoVisibility(view: View, description: String?) {
    val nonNullDescription = description ?: return

    when (nonNullDescription) {
        NOT_STARTED -> view.showView()
        else -> view.hideView()
    }
}

fun scoreVisibility(view: View, description: String?) {
    val nonNullDescription = description ?: return

    when (nonNullDescription) {
        NOT_STARTED -> view.hideView()
        else -> view.showView()
    }
}