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
    @SerializedName("distance")
    private val distance: String? = null

    fun getType() : String = type?: ""
    fun getName() : String = name?: ""
    fun getDescription() : String = component?: ""
    fun getLon(): Double = Math.round((lon?:0.0) * 10000.0) / 10000.0
    fun getLat(): Double = Math.round((lat?:0.0) * 10000.0) / 10000.0
    fun getDistance(): String = (Math.round((distance?:"0.0").toDouble() * 100.0) / 100.0).toString()
    fun getLocation(): String = "Lat: " + (getLat()).toString() + "  Lon: " + (getLon()).toString()

}