package com.belzsoftware.futspect.ui.shared

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import coil.load
import coil.transform.CircleCropTransformation
import com.belzsoftware.futspect.R
import com.belzsoftware.futspect.util.extensions.getAttrColor

@BindingAdapter("setImage")
fun setImage(imageView: ImageView, url: String?) {
    url ?: return
    imageView.load(url)
}

@BindingAdapter("setCircleImage")
fun setCircleImage(imageView: ImageView, url: String?) {

    if (url.isNullOrEmpty()) {
        imageView.setImageResource(R.drawable.ic_public)

        val color = getAttrColor(imageView.context, R.attr.colorOnBackground)
        imageView.setColorFilter(color)
        return
    }

    imageView.load(url) {
        transformations(CircleCropTransformation())
    }
}