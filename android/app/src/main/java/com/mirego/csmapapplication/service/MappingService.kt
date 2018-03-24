package com.mirego.csmapapplication.service

import com.mirego.csmapapplication.model.Mapping
import retrofit2.Call
import retrofit2.http.GET

interface MappingService {
    @GET("shared.ws.mirego.com/competition/mapping.json")
    fun listMappings(): Call<List<Mapping>>
}