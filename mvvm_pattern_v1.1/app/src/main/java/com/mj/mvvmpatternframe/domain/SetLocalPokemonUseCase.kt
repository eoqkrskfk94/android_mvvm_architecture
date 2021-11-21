package com.mj.mvvmpatternframe.domain

import com.mj.mvvmpatternframe.data.entity.PokemonEntity
import com.mj.mvvmpatternframe.data.repository.PokemonRepository

class SetLocalPokemonUseCase(
    private val pokemonRepository: PokemonRepository
) {

    suspend operator fun invoke(pokemonEntity: PokemonEntity) {
        pokemonRepository.insertPokemonEntity(pokemonEntity)
    }

}