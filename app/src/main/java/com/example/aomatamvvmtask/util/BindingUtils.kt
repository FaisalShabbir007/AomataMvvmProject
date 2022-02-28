package com.example.mvvmwithretrofit.util

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.example.aomatamvvmtask.R
import com.example.mvvmwithretrofit.util.Constants.Companion.IMAGE_BASE_URL
import com.squareup.picasso.Picasso

@BindingAdapter("image")
fun loadImage(view: ImageView, url: String) {
    Picasso.get().load(IMAGE_BASE_URL + url).placeholder(R.drawable.ic_search).into(view)
}
