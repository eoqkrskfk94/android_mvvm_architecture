package com.mj.mvvmpatternframe.domain

import com.mj.mvvmpatternframe.data.entity.PokemonEntity
import com.mj.mvvmpatternframe.data.repository.DefaultPokemonRepository
import com.mj.mvvmpatternframe.data.repository.PokemonRepository
import javax.inject.Inject

class SetLocalPokemonListUseCase @Inject constructor(
    private val pokemonRepository: PokemonRepository
) {

    suspend operator fun invoke(pokemonList: List<PokemonEntity>) {
        pokemonRepository.insertPokemonList(pokemonList)
    }

}