package com.example.mvvmwithdaggerandapi.adapter

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide

class PeopleBindingAdapter {
    companion object {
        @BindingAdapter(value = ["loadImage", "adapterPosition"], requireAll = true)
        @JvmStatic
        fun loadPeopleImage(imageView: ImageView, imageUrl: String, adapterPosition:Int) {
            val finalImageUrl = "$imageUrl${(adapterPosition + 1)}/120/120"
            Glide.with(imageView.context)
                .asBitmap()
                .load(finalImageUrl)
                .into(imageView)
        }
    }
}