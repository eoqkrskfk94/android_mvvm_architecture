package com.mj.mvvmpatternframe.data.repository

import com.mj.mvvmpatternframe.data.database.dao.PokemonDao
import com.mj.mvvmpatternframe.data.entity.PokemonEntity
import com.mj.mvvmpatternframe.data.network.PokemonApiService
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext
import javax.inject.Inject

class DefaultPokemonRepository @Inject constructor(
    private val pokemonApi: PokemonApiService,
    private val ioDispatcher: CoroutineDispatcher,
    private val pokemonDao: PokemonDao
): PokemonRepository {


    override suspend fun getPokemonList(): List<PokemonEntity> = withContext(ioDispatcher) {
        val response = pokemonApi.getPokemons(0)

        return@withContext if(response.isSuccessful) {
            response.body()?.results ?: arrayListOf()
        } else {
            arrayListOf()
        }

    }

    override suspend fun getLocalPokemonList(): List<PokemonEntity> = withContext(ioDispatcher) {
        return@withContext pokemonDao.getAll()
    }

    override suspend fun insertPokemonList(pokemonList: List<PokemonEntity>) = withContext(ioDispatcher) {
        pokemonDao.insertAll(pokemonList)
    }

    override suspend fun insertPokemonEntity(pokemonEntity: PokemonEntity) = withContext(ioDispatcher) {
        pokemonDao.insert(pokemonEntity)
    }

    override suspend fun deleteAllPokemon() = withContext(ioDispatcher) {
        pokemonDao.deleteAll()
    }

    override suspend fun deletePokemonEntity(pokemonEntity: PokemonEntity) = withContext(ioDispatcher) {
        pokemonDao.delete(pokemonEntity)
    }
}