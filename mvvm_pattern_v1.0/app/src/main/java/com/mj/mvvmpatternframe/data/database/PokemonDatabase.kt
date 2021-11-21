package com.mj.mvvmpatternframe.data.database

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.mj.mvvmpatternframe.data.database.dao.PokemonDao
import com.mj.mvvmpatternframe.data.entity.PokemonEntity


@Database(
    entities = [PokemonEntity::class],
    version = 1,
    exportSchema = false
)

abstract class PokemonDatabase: RoomDatabase() {

    companion object {
        const val DB_NAME = "PokemonDatabase.db"
    }

    abstract fun pokemonDao(): PokemonDao

}