package com.mirego.csmapapplication.service

import com.mirego.csmapapplication.model.Part
import retrofit2.Call
import retrofit2.http.GET

interface PartService {
    @GET("mapping.json")
    fun listParts(): Call<List<Part>>
}