package com.mj.mvvmpatternframe.data.database.dao

import androidx.room.*
import com.mj.mvvmpatternframe.data.entity.PokemonEntity

@Dao
interface PokemonDao {

    @Query("SELECT * FROM pokemon ORDER BY `index` ASC")
    suspend fun getAll(): List<PokemonEntity>

    @Query("SELECT * FROM pokemon WHERE name=:name")
    suspend fun getByName(name: String): PokemonEntity

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(pokemonList: List<PokemonEntity>)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(pokemonEntity: PokemonEntity)

    @Query("DELETE FROM pokemon")
    suspend fun deleteAll()

    @Delete
    suspend fun delete(pokemonEntity: PokemonEntity)



}