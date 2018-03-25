package com.mirego.csmapapplication.service

import com.mirego.csmapapplication.model.Item
import com.mirego.csmapapplication.model.LongLat
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.POST

interface ItemService {
    @POST("/mirego/api/v1/distance")
    fun listItems(@Body coordinates: LongLat): Call<List<Item>>
}