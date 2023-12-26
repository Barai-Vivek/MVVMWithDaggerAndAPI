package com.example.mvvmwithdaggerandapi.model

import com.google.gson.annotations.SerializedName

data class People (
    val imageUrl:String = "https://picsum.photos/id/",

    @field:SerializedName("name")
    var name: String = "",

    @field:SerializedName("website")
    val website: String? = null,

    @field:SerializedName("phone")
    val phone: String? = null,

    @field:SerializedName("id")
    val id: Int? = null,

    @field:SerializedName("email")
    val email: String? = null,

    @field:SerializedName("username")
    val username: String? = null
)