package com.bulentoral.movieapp.util

import android.graphics.drawable.Drawable
import android.util.Log
import android.widget.ImageView
import com.bulentoral.movieapp.R
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.GlideException
import com.bumptech.glide.request.RequestListener
import com.bumptech.glide.request.RequestOptions
import com.bumptech.glide.request.RequestOptions.centerCropTransform
import javax.sql.DataSource


fun ImageView.loadCircleImage(path: String?){
   Glide.with(this.context).load(Constant.IMAGE_BASE_URL+path).apply(centerCropTransform().error(R.drawable.ic_error).circleCrop()).into(this)


}

fun ImageView.loadImage(path: String?){
   Glide.with(this.context).load(Constant.IMAGE_BASE_URL+path).apply(centerCropTransform().error(R.drawable.ic_error)).into(this)


}