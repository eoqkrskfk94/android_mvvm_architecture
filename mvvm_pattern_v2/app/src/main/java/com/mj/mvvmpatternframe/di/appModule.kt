package com.mj.mvvmpatternframe.di

import com.mj.mvvmpatternframe.data.database.provideDao
import com.mj.mvvmpatternframe.data.database.provideDb
import com.mj.mvvmpatternframe.data.network.ProvidePokemonApiService.provideGsonConverterFactory
import com.mj.mvvmpatternframe.data.network.ProvidePokemonApiService.provideOkHttpClient
import com.mj.mvvmpatternframe.data.network.ProvidePokemonApiService.providePokemonApiService
import com.mj.mvvmpatternframe.data.network.ProvidePokemonApiService.providePokemonRetrofit
import com.mj.mvvmpatternframe.data.repository.DefaultPokemonRepository
import com.mj.mvvmpatternframe.data.repository.PagingRepository
import com.mj.mvvmpatternframe.data.repository.PokemonPagingSource
import com.mj.mvvmpatternframe.data.repository.PokemonRepository
import com.mj.mvvmpatternframe.domain.*
import com.mj.mvvmpatternframe.presentation.favorite.FavoriteViewModel
import com.mj.mvvmpatternframe.presentation.list.ListViewModel
import com.mj.mvvmpatternframe.presentation.main.MainViewModel
import kotlinx.coroutines.Dispatchers
import org.koin.android.ext.koin.androidContext
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val appModule = module {

    //di viewModel
    //viewModel { ListViewModel(get(), get(), get()) }
    viewModel { FavoriteViewModel(get(), get()) }

    //Coroutine Dispatcher
    single { Dispatchers.IO }
    single { Dispatchers.Main }

    //repositories
    //single<PokemonRepository> { DefaultPokemonRepository(get(), get(), get())}

    //retrofit
//    single { provideGsonConverterFactory() }
//    single { provideOkHttpClient() }
//    single { providePokemonApiService(get()) }
//    single { providePokemonRetrofit(get(), get())}

    //database
    single { provideDb(androidContext()) }
    single { provideDao(get()) }

    //useCases
    factory { GetPokemonListUseCase(get()) }
    factory { GetLocalPokemonListUseCase(get()) }
    factory { SetLocalPokemonListUseCase(get()) }
    //factory { SetLocalPokemonUseCase(get()) }
    factory { DeleteLocalPokemonUseCase(get()) }

    //paging
    //single { PagingRepository(get()) }
    //single { PokemonPagingSource(get()) }
}