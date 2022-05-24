package com.example.starwars.api


import com.example.starwars.models.PlanetIcons
import com.example.starwars.models.Planets
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Url

interface TodoApi {
    @GET("/api/planets/")
    suspend fun getPlanets(): Response<Planets>

    @GET
    suspend fun getImages(@Url url: String): Response<PlanetIcons>
}