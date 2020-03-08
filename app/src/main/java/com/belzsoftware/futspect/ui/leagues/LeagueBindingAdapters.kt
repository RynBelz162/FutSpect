package com.belzsoftware.futspect.ui.leagues

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import coil.api.load
import com.belzsoftware.futspect.R
import com.belzsoftware.futspect.model.league.LeagueResponse
import com.belzsoftware.futspect.util.getAttrColor

@BindingAdapter("setLeagueLogo")
fun setLeagueLogo(imageView: ImageView, leagueResponse: LeagueResponse?) {
    leagueResponse ?: return

    if (!leagueResponse.league.logo.isNullOrEmpty()) {
        imageView.colorFilter = null
        imageView.load(leagueResponse.league.logo)
        return
    }

    // if league does not have a logo load the flag instead
    if (!leagueResponse.country.flag.isNullOrEmpty()) {
        imageView.colorFilter = null
        imageView.load(leagueResponse.country.flag)
        return
    }

    val color = getAttrColor(imageView.context, R.attr.colorOnBackground)
    imageView.setColorFilter(color)
    imageView.load(R.drawable.ic_public)
}