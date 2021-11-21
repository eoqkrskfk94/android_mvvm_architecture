package com.mj.mvvmpatternframe.presentation.favorite

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mj.mvvmpatternframe.data.entity.PokemonEntity
import com.mj.mvvmpatternframe.domain.DeleteLocalPokemonUseCase
import com.mj.mvvmpatternframe.domain.GetLocalPokemonListUseCase
import kotlinx.coroutines.launch

class FavoriteViewModel(
    private val getLocalPokemonListUseCase: GetLocalPokemonListUseCase,
    private val deleteLocalPokemonUseCase: DeleteLocalPokemonUseCase
): ViewModel() {

    private val _localPokemonList = MutableLiveData<List<PokemonEntity>>()
    val localPokemonList: LiveData<List<PokemonEntity>> get() = _localPokemonList

    init {
        viewModelScope.launch {
            _localPokemonList.postValue(getLocalPokemonListUseCase.invoke())
        }
    }

    fun deleteAllFavorite() = viewModelScope.launch {
        deleteLocalPokemonUseCase.deleteAll()
        _localPokemonList.postValue(getLocalPokemonListUseCase.invoke())
    }

    fun deletePokemonFromFavorite(pokemonEntity: PokemonEntity) = viewModelScope.launch {
        deleteLocalPokemonUseCase.delete(pokemonEntity)
        _localPokemonList.postValue(getLocalPokemonListUseCase.invoke())
    }

}