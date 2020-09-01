package com.belzsoftware.futspect.ui.fixtures.info

import androidx.databinding.BindingAdapter
import com.belzsoftware.futspect.model.fixture.Venue
import com.belzsoftware.futspect.util.toFormattedString
import com.google.android.material.textview.MaterialTextView
import java.util.*

@BindingAdapter("setFixtureDatetime")
fun setFixtureDatetime(textView: MaterialTextView, date: Date?) {
    if (date == null) {
        return
    }

    val formattedString = date.toFormattedString("EEE MMM dd, yyyy. hh:mm aaa")
    textView.text = formattedString
}

@BindingAdapter("setVenue")
fun setVenue(textView: MaterialTextView, venue: Venue?) {
    if (venue == null) {
        return
    }

    var formattedVenue = venue.name
    if (venue.city.isNotEmpty()) {
        formattedVenue += ", ${venue.city}"
    }

    textView.text = formattedVenue
}