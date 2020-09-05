package com.belzsoftware.futspect.ui.fixtures.info

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.belzsoftware.futspect.R
import com.belzsoftware.futspect.model.fixture.Event
import com.belzsoftware.futspect.model.fixture.Venue
import com.belzsoftware.futspect.util.EVENT_GOAL
import com.belzsoftware.futspect.util.EVENT_RED_CARD
import com.belzsoftware.futspect.util.EVENT_SUBSTITUTION
import com.belzsoftware.futspect.util.EVENT_YELLOW_CARD
import com.belzsoftware.futspect.util.extensions.toFormattedString
import com.google.android.material.textview.MaterialTextView
import java.time.LocalDateTime

@BindingAdapter("setFixtureDatetime")
fun setFixtureDatetime(textView: MaterialTextView, date: LocalDateTime?) {
    if (date == null) {
        return
    }

    val formattedString = date.toFormattedString("EEE MMM dd, yyyy. hh:mm a")
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

@BindingAdapter("setEventIcon")
fun setEventIcon(imageView: ImageView, event: Event?) {
    if (event == null) {
        return
    }

    when (event.type) {
        EVENT_GOAL -> imageView.setImageResource(R.drawable.ic_soccer)
        EVENT_YELLOW_CARD, EVENT_RED_CARD -> imageView.setImageResource(R.drawable.ic_card)
        EVENT_SUBSTITUTION -> imageView.setImageResource(R.drawable.ic_swap)
    }
}