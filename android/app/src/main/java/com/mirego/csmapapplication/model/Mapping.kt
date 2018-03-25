package com.mirego.csmapapplication.model

import android.os.Parcel
import android.os.Parcelable

data class Mapping(
        val name: String,
        val component: String,
        val notes: String,
        val type: String,
        val address: String?,
        val lon: Double?,
        val lat: Double?,
        var distance: Float?
) : Parcelable {
    constructor(parcel: Parcel) : this(
            parcel.readString(),
            parcel.readString(),
            parcel.readString(),
            parcel.readString(),
            parcel.readString(),
            parcel.readValue(Double::class.java.classLoader) as? Double,
            parcel.readValue(Double::class.java.classLoader) as? Double,
            parcel.readValue(Double::class.java.classLoader) as? Float) {
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(name)
        parcel.writeString(component)
        parcel.writeString(notes)
        parcel.writeString(type)
        parcel.writeString(address)
        parcel.writeValue(lon)
        parcel.writeValue(lat)
        parcel.writeValue(distance)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<Mapping> {
        override fun createFromParcel(parcel: Parcel): Mapping {
            return Mapping(parcel)
        }

        override fun newArray(size: Int): Array<Mapping?> {
            return arrayOfNulls(size)
        }
    }
}
