package com.mj.mvvmpatternframe.di

import android.content.Context
import androidx.room.Room
import com.google.gson.GsonBuilder
import com.mj.mvvmpatternframe.BuildConfig
import com.mj.mvvmpatternframe.data.database.PokemonDatabase
import com.mj.mvvmpatternframe.data.database.dao.PokemonDao
import com.mj.mvvmpatternframe.data.network.PokemonApiService
import com.mj.mvvmpatternframe.data.repository.DefaultPokemonRepository
import com.mj.mvvmpatternframe.data.repository.PokemonRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    private const val POKEMON_BASE_URL = "https://pokeapi.co/api/v2/"

    @Singleton
    @Provides
    fun provideIoDispatcher(): CoroutineDispatcher = Dispatchers.IO

    @Singleton
    @Provides
    fun providePokemonApiService(retrofit: Retrofit): PokemonApiService {
        return retrofit.create(PokemonApiService::class.java)
    }

    @Singleton
    @Provides
    fun providePokemonRetrofit(
        okHttpClient: OkHttpClient,
        gsonConverterFactory: GsonConverterFactory
    ): Retrofit {
        return Retrofit.Builder()
            .baseUrl(POKEMON_BASE_URL)
            .addConverterFactory(gsonConverterFactory)
            .client(okHttpClient)
            .build()
    }

    @Singleton
    @Provides
    fun provideGsonConverterFactory(): GsonConverterFactory {
        return GsonConverterFactory.create(
            GsonBuilder().create()
        )
    }

    @Singleton
    @Provides
    fun provideOkHttpClient(): OkHttpClient {
        val interceptor = HttpLoggingInterceptor()

        if(BuildConfig.DEBUG) {
            interceptor.level = HttpLoggingInterceptor.Level.BODY
        } else {
            interceptor.level = HttpLoggingInterceptor.Level.NONE
        }

        return OkHttpClient.Builder()
            .connectTimeout(5, TimeUnit.SECONDS)
            .addInterceptor(interceptor)
            .build()
    }

    @Singleton
    @Provides
    fun provideDb(@ApplicationContext context: Context): PokemonDatabase =
        Room.databaseBuilder(context, PokemonDatabase::class.java, PokemonDatabase.DB_NAME).build()

    @Singleton
    @Provides
    fun providePokemonRepository(
        pokemonApi: PokemonApiService,
        ioDispatcher: CoroutineDispatcher,
        pokemonDao: PokemonDao
    ): PokemonRepository = DefaultPokemonRepository(pokemonApi, ioDispatcher, pokemonDao)

    @Singleton
    @Provides
    fun provideDao(database: PokemonDatabase) = database.pokemonDao()

}