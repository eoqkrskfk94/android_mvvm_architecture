package com.mj.mvvmpatternframe.domain

import com.mj.mvvmpatternframe.data.entity.PokemonEntity
import com.mj.mvvmpatternframe.data.repository.PokemonRepository
import javax.inject.Inject

class SetLocalPokemonUseCase @Inject constructor(
    private val pokemonRepository: PokemonRepository
) {

    suspend operator fun invoke(pokemonEntity: PokemonEntity) {
        //pokemonRepository.insertPokemonEntity(pokemonEntity)
    }

}