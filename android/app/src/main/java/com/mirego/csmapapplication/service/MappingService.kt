package com.mirego.csmapapplication.service

import com.mirego.csmapapplication.model.Mapping
import io.reactivex.Single
import retrofit2.http.GET

interface MappingService {
    @GET("mapping.json")
    fun getAll(): Single<List<Mapping>>
}