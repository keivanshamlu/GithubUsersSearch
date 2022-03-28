package com.shamlou.bases_android.bindingAdapters

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide

object ImageViewBindingAdapter {

    @BindingAdapter(
        "binding:imageUrl"
    )
    @JvmStatic
    fun loadUrl(
        imageView: ImageView,
        url: String?
    ) {
        Glide
            .with(imageView.context)
            .load(url)
            .centerCrop()
            .circleCrop()
            .into(imageView);
    }

}