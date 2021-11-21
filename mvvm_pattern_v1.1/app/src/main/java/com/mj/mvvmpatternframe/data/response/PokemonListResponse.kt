package com.mj.mvvmpatternframe.data.response

import com.google.gson.annotations.SerializedName
import com.mj.mvvmpatternframe.data.entity.PokemonEntity

data class PokemonListResponse(
    @SerializedName("count") val count: Int?,
    @SerializedName("next") val next: String?,
    @SerializedName("previous") val previous: String?,
    @SerializedName("results") val results: ArrayList<PokemonEntity>
)
