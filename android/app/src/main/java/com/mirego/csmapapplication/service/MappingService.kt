package com.mirego.csmapapplication.service

import com.mirego.csmapapplication.model.LocationDto
import retrofit2.Call
import retrofit2.http.GET

interface MappingService {
    @GET("shared.ws.mirego.com/competition/mapping.json")
    fun listData(): Call<List<LocationDto>>
}