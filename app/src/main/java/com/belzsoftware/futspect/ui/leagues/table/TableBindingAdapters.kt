package com.belzsoftware.futspect.ui.leagues.table

import android.view.View
import androidx.databinding.BindingAdapter
import com.belzsoftware.futspect.R
import com.belzsoftware.futspect.util.extensions.getAttrColor

private const val PROMOTION = "Promotion"
private const val RELEGATION = "Relegation"

@BindingAdapter("rankColor")
fun rankColor(view: View, description: String?) {
    description ?: return view.setBackgroundColor(getAttrColor(view.context, R.attr.colorSurface))

    view.apply {
        when {
            description.contains(PROMOTION) -> this.setBackgroundColor(
                getAttrColor(
                    context,
                    R.attr.colorOkay
                )
            )

            description.contains(RELEGATION) -> this.setBackgroundColor(
                getAttrColor(
                    context,
                    R.attr.colorError
                )
            )

            else -> this.setBackgroundColor(getAttrColor(context, R.attr.colorSurface))
        }
    }
}