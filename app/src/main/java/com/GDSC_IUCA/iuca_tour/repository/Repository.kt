package com.GDSC_IUCA.iuca_tour.repository

import com.GDSC_IUCA.iuca_tour.api.RetrofitInstance
import com.GDSC_IUCA.iuca_tour.models.PlacesItem
import retrofit2.Response

class Repository {
    suspend fun getPlaces(): Response<List<PlacesItem>> {
        return  RetrofitInstance.api.getPlaces()

    }
}