package com.mirego.csmapapplication.service

import com.mirego.csmapapplication.model.Piece
import retrofit2.Call
import retrofit2.http.GET

interface MappingService {
    @GET("shared.ws.mirego.com/competition/mapping.json")
    fun listPieces(): Call<List<Piece>>
}