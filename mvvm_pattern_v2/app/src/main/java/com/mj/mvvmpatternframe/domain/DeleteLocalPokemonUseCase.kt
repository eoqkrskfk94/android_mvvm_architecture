package com.mj.mvvmpatternframe.domain

import com.mj.mvvmpatternframe.data.entity.PokemonEntity
import com.mj.mvvmpatternframe.data.repository.PokemonRepository
import javax.inject.Inject

class DeleteLocalPokemonUseCase @Inject constructor(
    private val pokemonRepository: PokemonRepository
) {

    suspend fun deleteAll() {
        pokemonRepository.deleteAllPokemon()
    }

    suspend fun delete(pokemonEntity: PokemonEntity) {
        pokemonRepository.deletePokemonEntity(pokemonEntity)
    }

}