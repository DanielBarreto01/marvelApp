package com.example.marvelapp.utils

import android.widget.ImageView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.example.marvelapp.R

fun ImageView.loadImage(url: String) {
    Glide
        .with(this)
        .load(url)
        .placeholder(R.drawable.placeholder_image)
        .error(R.drawable.error_image)
        .into(this)
}


