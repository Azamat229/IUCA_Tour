package com.GDSC_IUCA.iuca_tour.api

import com.GDSC_IUCA.iuca_tour.models.Places
import com.GDSC_IUCA.iuca_tour.models.PlacesItem
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface PlaceApi {
    @GET("api/place/")
    suspend fun getPlaces(
        @Query("lang")
        language: String = "ENG"

    ): Response<List<PlacesItem>>


}