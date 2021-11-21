package com.mj.mvvmpatternframe.data.database

import android.content.Context
import androidx.room.Room

fun provideDb(context: Context): PokemonDatabase =
    Room.databaseBuilder(context, PokemonDatabase::class.java, PokemonDatabase.DB_NAME).build()

fun provideDao(database: PokemonDatabase) = database.pokemonDao()