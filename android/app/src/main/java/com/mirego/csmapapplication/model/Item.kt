package com.mirego.csmapapplication.model

import com.google.gson.annotations.SerializedName

class Item {
    @SerializedName("name")
    private val name: String? = null
    @SerializedName("component")
    private val component: String? = null
    @SerializedName("notes")
    private val notes: String? = null
    @SerializedName("type")
    private val type: String? = null
    @SerializedName("lat")
    private val lat: Double? = null
    @SerializedName("lon")
    private val lon: Double? = null
    @SerializedName("address")
    private val address: String? = null

    fun getType() : String = type?: ""
    fun getName() : String = name?: ""
    fun getDescription() : String = component?: ""
}