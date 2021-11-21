package com.mj.mvvmpatternframe.presentation.list

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mj.mvvmpatternframe.data.entity.PokemonEntity
import com.mj.mvvmpatternframe.domain.GetPokemonListUseCase
import com.mj.mvvmpatternframe.domain.SetLocalPokemonListUseCase
import com.mj.mvvmpatternframe.domain.SetLocalPokemonUseCase
import kotlinx.coroutines.launch

class ListViewModel(
    private val getPokemonListUseCase: GetPokemonListUseCase,
    private val setLocalPokemonListUseCase: SetLocalPokemonListUseCase,
    private val setLocalPokemonUseCase: SetLocalPokemonUseCase
): ViewModel() {

    private val _pokemonList = MutableLiveData<List<PokemonEntity>>()
    val pokemonList: LiveData<List<PokemonEntity>> get() = _pokemonList

    init {
        viewModelScope.launch {
            _pokemonList.postValue(getPokemonListUseCase.invoke())
        }
    }

    fun setLocalPokemonList(pokemonList: List<PokemonEntity>) = viewModelScope.launch {
        setLocalPokemonListUseCase(pokemonList)
    }

    fun addFavoritePokemon(pokemonEntity: PokemonEntity) = viewModelScope.launch {
        setLocalPokemonUseCase(pokemonEntity)
    }
}