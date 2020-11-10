package com.belzsoftware.futspect.ui.leagues.table

import android.view.View
import com.belzsoftware.futspect.R
import com.belzsoftware.futspect.util.extensions.getAttrColor

private const val PROMOTION = "Promotion"
private const val RELEGATION = "Relegation"

fun rankColor(view: View, description: String?) {
    var color = getAttrColor(view.context, R.attr.colorSurface)

    description ?: return view.setBackgroundColor(color)

    when {
        description.contains(PROMOTION) -> color = getAttrColor(view.context, R.attr.colorOkay)
        description.contains(RELEGATION) -> color = getAttrColor(view.context, R.attr.colorError)
    }

    view.setBackgroundColor(color)
}