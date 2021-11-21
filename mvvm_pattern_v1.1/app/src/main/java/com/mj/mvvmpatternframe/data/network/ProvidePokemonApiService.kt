package com.mj.mvvmpatternframe.data.network

import com.google.gson.GsonBuilder
import com.mj.mvvmpatternframe.BuildConfig
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit


fun providePokemonApiService(retrofit: Retrofit): PokemonApiService {
    return retrofit.create(PokemonApiService::class.java)
}


fun providePokemonRetrofit(
    okHttpClient: OkHttpClient,
    gsonConverterFactory: GsonConverterFactory
): Retrofit {
    return Retrofit.Builder()
        .baseUrl(Url.POKEMON_BASE_URL)
        .addConverterFactory(gsonConverterFactory)
        .client(okHttpClient)
        .build()
}

fun provideGsonConverterFactory(): GsonConverterFactory {
    return GsonConverterFactory.create(
        GsonBuilder().create()
    )
}

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
