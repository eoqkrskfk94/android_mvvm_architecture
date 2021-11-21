package com.mj.mvvmpatternframe.data.repository

import com.mj.mvvmpatternframe.data.entity.PokemonEntity

interface PokemonRepository {

    suspend fun getPokemonList(): List<PokemonEntity>

    suspend fun getLocalPokemonList(): List<PokemonEntity>

    suspend fun insertPokemonList(pokemonList: List<PokemonEntity>)

    suspend fun insertPokemonEntity(pokemonEntity: PokemonEntity)

    suspend fun deleteAllPokemon()

    suspend fun deletePokemonEntity(pokemonEntity: PokemonEntity)

}