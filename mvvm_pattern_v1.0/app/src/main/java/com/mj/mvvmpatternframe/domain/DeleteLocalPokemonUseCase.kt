package com.mj.mvvmpatternframe.domain

import com.mj.mvvmpatternframe.data.entity.PokemonEntity
import com.mj.mvvmpatternframe.data.repository.PokemonRepository

class DeleteLocalPokemonUseCase(
    private val pokemonRepository: PokemonRepository
) {

    suspend fun deleteAll() {
        pokemonRepository.deleteAllPokemon()
    }

    suspend fun delete(pokemonEntity: PokemonEntity) {
        pokemonRepository.deletePokemonEntity(pokemonEntity)
    }

}