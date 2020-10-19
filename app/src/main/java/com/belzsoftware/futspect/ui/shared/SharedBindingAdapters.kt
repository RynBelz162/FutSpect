package com.belzsoftware.futspect.ui.shared

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import coil.load

@BindingAdapter("setImage")
fun setImage(imageView: ImageView, url: String?) {
    url ?: return
    imageView.load(url)
}