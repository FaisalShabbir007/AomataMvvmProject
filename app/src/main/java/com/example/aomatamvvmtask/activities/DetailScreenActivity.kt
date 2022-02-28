package com.example.aomatamvvmtask.activities

import android.app.ActionBar
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import com.bumptech.glide.Glide
import com.example.aomatamvvmtask.R
import com.example.aomatamvvmtask.databinding.ActivityDetailScreenBinding
import com.example.mvvmwithretrofit.model.movie.MoviesList
import com.example.mvvmwithretrofit.util.Constants
import com.squareup.picasso.Picasso

class DetailScreenActivity : AppCompatActivity() {
    lateinit var binding: ActivityDetailScreenBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_detail_screen)

        val path = intent.getStringExtra("path")
        Picasso.get().load(Constants.IMAGE_BASE_URL + path).placeholder(R.drawable.ic_search).into(binding.imageViewBg)

    }
}