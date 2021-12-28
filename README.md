![image](https://user-images.githubusercontent.com/37679062/143455448-6a06f016-e0fd-4e82-9878-3209b479319f.png)
<p align="center">
  <a href="https://opensource.org/licenses/Apache-2.0"><img alt="License" src="https://img.shields.io/badge/License-MIT%20License-blue"/></a>
  <a href="https://android-arsenal.com/api?level=26"><img alt="API" src="https://img.shields.io/badge/API-26%2B-brightgreen.svg?style=flat"/></a>
</p>


# Android MVVM Architecture Template

Developing an android app is easy but developing an app with reusable components are the hardest thing,
Some tools are helpful to write a code with prebuild tools and configuration.

This guideline will help set for setting MVVM architecture with different plugins.

<br/><br/>

# [v1.0] MVVM Architecture template using DI KOIN

<br/>

## Main View (MainAcitivty)
Simple texView & Button sample using ViewModel, liveData and DataBinding

<img src="https://user-images.githubusercontent.com/37679062/134447215-bbf030ec-6ec3-4775-8e0b-81c428fe5f77.gif" align="left" width="25%"/>
<br/>


liveData countText in MainViewModel is incremented by 1 whenever `INCREASE NUMBER` is clicked. 

```kotlin
//MainViewModel.class
fun clickIncreaseButton() {
  _countText.value = "click count : ${++count}"
}
```

Then countText is observed and will be notifed of updates whenever the value is changed.
```kotlin
viewModel.countText.observe(this, Observer { number ->
  binding.textviewNumber.text = number
})
```



<br/><br/><br/><br/><br/>
## Pokemon List View (ListActivity)
Simple RecyclerView Sample using Retrofit, Koin, ViewModel. (PokeApi is used in this sample)


<img src="https://user-images.githubusercontent.com/37679062/134451811-8d7a345b-ffdb-4a09-a10c-4c83dfa2dad0.gif" align="left" width="25%"/>
<br/>

pokemon list is retreived using Retrofit and saved to liveData
```kotlin

//ListViewModel.class

private val _pokemonList = MutableLiveData<List<PokemonEntity>>()
val pokemonList: LiveData<List<PokemonEntity>> get() = _pokemonList

init {
  viewModelScope.launch {
  _pokemonList.postValue(getPokemonListUseCase.invoke())
  }
}


fun addFavoritePokemon(pokemonEntity: PokemonEntity) = viewModelScope.launch {
  setLocalPokemonUseCase(pokemonEntity)
  }
}

```
<br/><br/>
then the list is observed and will be notifed of updates whenever the the list is retreived.

```kotlin

//ListAcitivty.class

viewModel.pokemonList.observe(this, Observer {
  binding.progressBarLoading.isVisible = false
  adapter.setData(it)
  })

//button to favorite activity
binding.floatingBtnFavorite.setOnClickListener {
  startActivity(Intent(this, FavoriteActivity::class.java))
  }
}

```


<br/><br/>
## Pokemon Favorite List View (FavoriteActivity)
Simple RecyclerView Sample using Room, Koin, ViewModel.

<img src="https://user-images.githubusercontent.com/37679062/134452057-dc74622f-1e85-46ea-8190-eeddcff806ff.gif" align="left" width="25%"/>
<br/>


RecyclerView, ViewModel, liveData structure is similar to `Pokemon List View`. However, for Data, Room is used instead of Retrofit in order to save Favorites.

<br/>

when a pokemon is clicked in ListActivity, clicked pokemon is add to favorite using Room database. In favorite Activity (through floating button), added pokemon can be deleted from favorite. 

<br/><br/><br/><br/><br/><br/><br/><br/><br/><br/><br/><br/><br/>
# [v1.1] MVVM Architecture template + Jetpack Paging3


RecyclerView without paging | RecyclerView with paging | 
| :---------------: | :---------------: | 
| <img src="https://user-images.githubusercontent.com/37679062/134458553-84e0e03a-413f-4356-9cc3-f3ae58e440b8.gif" align="center" width="50%"/> | <img src="https://user-images.githubusercontent.com/37679062/134459229-58ba75d9-b6c9-40f5-b885-31d0ad9383ba.gif" align="center" width="50%"/> | 

Paging3 helps load and display pages of data from a larger dataset from local storage or over network. This will help configure RecyclerView adapters that automatically request data as the user scrolls toward the end of the loaded data.

paging3 needs `PagingRepository`, `PagingSource`, and `PagingAdapter`

<br/><br/><br/><br/><br/>
# [v2.0] MVVM Architecture template using DI Hilt

Used Hilt instead of Koin for Dependancy Injection


<br/><br/><br/>
# License
```xml
MIT License

Copyright (c) 2021 Daniel Kim

Permission is hereby granted, free of charge, to any person obtaining a copy
of this software and associated documentation files (the "Software"), to deal
in the Software without restriction, including without limitation the rights
to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
copies of the Software, and to permit persons to whom the Software is
furnished to do so, subject to the following conditions:

The above copyright notice and this permission notice shall be included in all
copies or substantial portions of the Software.

THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
SOFTWARE.
```

