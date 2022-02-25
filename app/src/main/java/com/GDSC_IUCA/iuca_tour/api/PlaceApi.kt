package com.GDSC_IUCA.iuca_tour.api

import com.GDSC_IUCA.iuca_tour.models.PlaceByIt
import com.GDSC_IUCA.iuca_tour.models.Places
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface PlaceApi {
    @GET("api/place/1")
    suspend fun getPlaces(
        @Query("lang")
        language: String = "ENG"

    ): Response<PlaceByIt>


}