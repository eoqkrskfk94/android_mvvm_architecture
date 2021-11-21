package com.mj.mvvmpatternframe.domain

import com.mj.mvvmpatternframe.data.entity.PokemonEntity
import com.mj.mvvmpatternframe.data.repository.PokemonRepository

class SetLocalPokemonListUseCase(
    private val pokemonRepository: PokemonRepository
) {

    suspend operator fun invoke(pokemonList: List<PokemonEntity>) {
        pokemonRepository.insertPokemonList(pokemonList)
    }

}