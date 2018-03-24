package com.mirego.csmapapplication.model

import com.google.gson.annotations.SerializedName

class Part {
    val name: String? = null

    val component: String? = null

    val notes: String? = null

    @SerializedName("lat")
    val latitude: Double? = null

    @SerializedName("lon")
    val longitude: Double? = null

    val address: String? = null
}