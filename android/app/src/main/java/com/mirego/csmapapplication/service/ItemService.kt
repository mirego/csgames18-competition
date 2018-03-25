package com.mirego.csmapapplication.service

import com.mirego.csmapapplication.model.Item
import retrofit2.Call
import retrofit2.http.GET

interface ItemService {
    @GET("/mapping.json")
    fun listItems(): Call<List<Item>>
}