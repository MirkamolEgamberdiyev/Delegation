package com.mirkamol.delegation.utils

import android.widget.ImageView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.mirkamol.delegation.R


fun ImageView.load(url: String?) {
    Glide
        .with(this)
        .load(url)
        .diskCacheStrategy(DiskCacheStrategy.NONE)
        .skipMemoryCache(true)
        .centerCrop()
        .placeholder(R.drawable.ic_launcher_foreground)
        .into(this)
}