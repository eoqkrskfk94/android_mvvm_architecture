package com.mj.mvvmpatternframe.presentation.list

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.mj.mvvmpatternframe.data.entity.PokemonEntity
import com.mj.mvvmpatternframe.data.repository.PagingRepository
import com.mj.mvvmpatternframe.domain.GetPokemonListUseCase
import com.mj.mvvmpatternframe.domain.SetLocalPokemonListUseCase
import com.mj.mvvmpatternframe.domain.SetLocalPokemonUseCase
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch

class ListViewModel(
    private val setLocalPokemonListUseCase: SetLocalPokemonListUseCase,
    private val setLocalPokemonUseCase: SetLocalPokemonUseCase,
    private val pagingRepository: PagingRepository
): ViewModel() {


    fun getContent(): Flow<PagingData<PokemonEntity>> {
        return pagingRepository.getPagingData().cachedIn(viewModelScope)
    }

    fun setLocalPokemonList(pokemonList: List<PokemonEntity>) = viewModelScope.launch {
        setLocalPokemonListUseCase(pokemonList)
    }

    fun addFavoritePokemon(pokemonEntity: PokemonEntity) = viewModelScope.launch {
        setLocalPokemonUseCase(pokemonEntity)
    }
}