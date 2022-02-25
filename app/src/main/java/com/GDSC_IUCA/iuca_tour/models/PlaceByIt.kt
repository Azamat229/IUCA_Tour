package com.GDSC_IUCA.iuca_tour.models

data class PlaceByIt(
    val audio: String,
    val desc: String,
    val id: Int,
    val images: List<String>,
    val lang: String,
    val name: String
)