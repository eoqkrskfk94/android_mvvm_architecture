package com.mj.mvvmpatternframe.data.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

@Entity(tableName = "pokemon")
data class PokemonEntity(
    @PrimaryKey @SerializedName("name") val name: String,
    @SerializedName("url") val url: String,
    var index: Int = 0,
    var favorite: Boolean = false
) {

    fun getImageUrl(): String {
        val index = url.split("/".toRegex()).dropLast(1).last()
        return "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/other/official-artwork/$index.png"
    }
}