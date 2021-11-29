package com.alsoftware.newsdemoapp.data.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class SimpleSource(
    @SerializedName("id")
    @Expose
    val id: String? = null,

    @SerializedName("name")
    @Expose
    val name: String? = null
)