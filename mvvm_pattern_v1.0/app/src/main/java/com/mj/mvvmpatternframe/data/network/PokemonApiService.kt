package com.mj.mvvmpatternframe.data.network

import com.mj.mvvmpatternframe.data.response.PokemonListResponse
import retrofit2.Response
import retrofit2.http.GET

interface PokemonApiService {

    @GET("pokemon/")
    suspend fun getPokemons(): Response<PokemonListResponse>

}