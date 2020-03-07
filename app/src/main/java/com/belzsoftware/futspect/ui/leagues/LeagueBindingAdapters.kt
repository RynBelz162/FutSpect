package com.belzsoftware.futspect.ui.leagues

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import coil.api.load
import com.belzsoftware.futspect.model.league.League

@BindingAdapter("setLeagueLogo")
fun setLeagueLogo(imageView: ImageView, league: League?) {
    league ?: return

    if (!league.logo.isNullOrEmpty()) {
        imageView.load(league.logo)
        return
    }

    // if league does not have a logo load the flag instead
    if (!league.flag.isNullOrEmpty()) {
        imageView.load(league.flag)
    }
}