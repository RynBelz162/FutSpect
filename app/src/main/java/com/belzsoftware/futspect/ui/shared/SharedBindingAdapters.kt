package com.belzsoftware.futspect.ui.shared

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import coil.api.load

@BindingAdapter("setImage")
fun setImage(imageView: ImageView, url: String?) {
    val nonNullUrl = url ?: return

    imageView.load(nonNullUrl)
}