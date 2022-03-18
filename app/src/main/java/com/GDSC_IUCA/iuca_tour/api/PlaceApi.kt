package com.GDSC_IUCA.iuca_tour.api

import com.GDSC_IUCA.iuca_tour.models.Places
import com.GDSC_IUCA.iuca_tour.models.PlacesItem
import com.GDSC_IUCA.iuca_tour.models.Preset
import com.GDSC_IUCA.iuca_tour.models.PresetItem
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface PlaceApi {
    @GET("api/place/")
    suspend fun getPlaces(
        @Query("lang")
        language: String = "ENG"

    ): Response<List<PlacesItem>>

    @GET("api/preset/1") // long preset
    suspend fun getPresetLong(
    ): Response<PresetItem>

    @GET("api/place/{id}")
    suspend fun getItemPlace(
        @Path("id") number: Int,
    @Query("lang")
    language: String = "ENG"

    ): Response<PlacesItem>


}