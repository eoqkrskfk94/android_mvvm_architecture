package com.mj.mvvmpatternframe.data.repository

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.mj.mvvmpatternframe.data.entity.PokemonEntity
import com.mj.mvvmpatternframe.data.network.PokemonApiService
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

class PokemonPagingSource @Inject constructor(
    private val pokemonApiService: PokemonApiService
) : PagingSource<Int, PokemonEntity>() {


    /**
     *  params을 바탕으로 페이지의 데이터를 반환하게 됩니다
     *  load()의 인자로 넘어오는 params의 key 값이 페이지 정보입니다. 추가적으로 loadSize도 가지고 있습니다.
     *
     *  return인 LoadResult는 Page, Error 두 가지가 있습니다. LoadResult.Page는 정상적인 경우일 때 사용하면 됩니다.
     *  만약 다음 데이터를 더 이상 호출하지 않으려면 nextKey 값을 null로 주면 됩니다.
     *  LoadResult.Error는 Exception 발생이나 데이터가 문제가 있을 경우 사용하면 됩니다.
     */
    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, PokemonEntity> {
        //for first case it will be null, then we can pass some default value, in our case it's 0
        return try {
            val page = params.key ?: 0
            val response = pokemonApiService.getPokemons(page)

            LoadResult.Page(
                response.body()?.results!!,
                prevKey = if (page == 0) null else page - 20,
                nextKey = if (response.body()?.results!!.isEmpty()) null else page + 20
            )

        } catch (exception: IOException) {
            return LoadResult.Error(exception)
        } catch (exception: HttpException) {
            return LoadResult.Error(exception)
        }
    }

    /**
     * refresh시 다시 시작할 key를 반환해주면 됩니다.
     * PagingState를 인자로 받습니다. PagingState는 로드된 페이지 및 마지막으로 액세스 한 위치 등의 페이징 시스템의 스냅샷 상태를 가지고 있습니다.
     */
    override fun getRefreshKey(state: PagingState<Int, PokemonEntity>): Int? {
        return state.anchorPosition?.let { anchorPosition ->
            state.closestPageToPosition(anchorPosition)?.prevKey?.plus(1)
                ?: state.closestPageToPosition(anchorPosition)?.nextKey?.minus(1)
        }
    }
}
