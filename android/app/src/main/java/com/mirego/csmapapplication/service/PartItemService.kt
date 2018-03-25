package com.mirego.csmapapplication.service

import com.mirego.csmapapplication.model.PartItem
import com.mirego.csmapapplication.model.Repo
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

/**
 * Created by Felix on 2018-03-24.
 */
interface PartItemService {
    @GET("https://s3.amazonaws.com/shared.ws.mirego.com/competition/mapping.json")
    fun getPartItemsMappings(): Call<ArrayList<PartItem>>
}