package com.mj.mvvmpatternframe.data.network

import com.mj.mvvmpatternframe.data.response.PokemonListResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface PokemonApiService {

    @GET("pokemon/")
    suspend fun getPokemons(
        @Query("offset") offset: Int
    ): Response<PokemonListResponse>

}