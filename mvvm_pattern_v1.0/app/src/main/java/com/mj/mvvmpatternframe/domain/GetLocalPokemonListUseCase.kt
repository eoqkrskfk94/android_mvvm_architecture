package com.mj.mvvmpatternframe.domain

import com.mj.mvvmpatternframe.data.entity.PokemonEntity
import com.mj.mvvmpatternframe.data.repository.PokemonRepository

class GetLocalPokemonListUseCase(
    private val pokemonRepository: PokemonRepository
) {

    suspend operator fun invoke(): List<PokemonEntity> {
        return pokemonRepository.getLocalPokemonList()
    }

}