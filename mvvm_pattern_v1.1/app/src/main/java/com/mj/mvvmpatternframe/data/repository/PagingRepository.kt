package com.mj.mvvmpatternframe.data.repository

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.mj.mvvmpatternframe.data.entity.PokemonEntity
import com.mj.mvvmpatternframe.data.network.PokemonApiService
import kotlinx.coroutines.flow.Flow


/**
 *  Repository에서 Pager와 PagingSource를 사용하여 PagingData로 반환해줍니다
 *
 *  PagingConfig로 페이저의 기본 설정을 정의해준 뒤 Pager 객체를 생성합니다. 추가적으로 Pager 생성 시 초기 키 값을 지정해줄 수도 있습니다.
 *  Pager를 생성 후 Flow 형태로 반환해줍니다. 반환 형태는 Observable, LiveData로도 가능합니다.
 */
class PagingRepository(
    private val pokemonApiService: PokemonApiService
) {

    fun getPagingData(): Flow<PagingData<PokemonEntity>> {
        return Pager(PagingConfig(pageSize = 20)) {
            PokemonPagingSource(pokemonApiService)
        }.flow
    }

}