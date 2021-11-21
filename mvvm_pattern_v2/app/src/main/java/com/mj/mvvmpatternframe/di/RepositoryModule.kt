package com.mj.mvvmpatternframe.di

import com.mj.mvvmpatternframe.data.repository.DefaultPokemonRepository
import com.mj.mvvmpatternframe.data.repository.PokemonRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RepositoryModule {

//    @Singleton
//    @Provides
//    fun providePokemonRepository() : PokemonRepository = DefaultPokemonRepository()

}