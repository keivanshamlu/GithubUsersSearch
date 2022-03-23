package com.shamlou.bases_android.view

import android.view.View
import androidx.core.view.isVisible
import androidx.databinding.BindingAdapter

@BindingAdapter("binding:isVisible")
fun View.viewVisibility(status: Boolean) {
    isVisible = status
}