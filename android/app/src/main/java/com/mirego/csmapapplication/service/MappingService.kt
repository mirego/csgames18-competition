package com.mirego.csmapapplication.service

import com.mirego.csmapapplication.model.VesselPart
import retrofit2.Call
import retrofit2.http.GET

interface MappingService {

    @GET("https://s3.amazonaws.com/shared.ws.mirego.com/competition/mapping.json")
    fun listVesselParts(): Call<List<VesselPart>>
}