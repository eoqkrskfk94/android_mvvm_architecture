package com.mj.mvvmpatternframe.presentation.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.mj.mvvmpatternframe.data.entity.PokemonEntity
import com.mj.mvvmpatternframe.databinding.ItemPokemonBinding

class PagingAdapter(private val itemClick: (PokemonEntity) -> Unit): PagingDataAdapter<PokemonEntity, PagingAdapter.PagingViewHolder>(diffCallback) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PagingViewHolder {
        val binding = ItemPokemonBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return PagingViewHolder(binding)
    }

    override fun onBindViewHolder(holder: PagingAdapter.PagingViewHolder, position: Int) {
        val item = getItem(position)
        if(item != null) {
            holder.bind(item)
        }
    }


    inner class PagingViewHolder(private val binding: ItemPokemonBinding): RecyclerView.ViewHolder(binding.root) {

        fun bind(pokemonEntity: PokemonEntity) = with(binding) {
            textviewPokemonName.text = pokemonEntity.name
            imageviewFavorite.isVisible = pokemonEntity.favorite
            pokemonEntity.index = pokemonEntity.url.split("/".toRegex()).dropLast(1).last().toInt()
            textViewPokemonNumber.text = pokemonEntity.index.toString()
            cardviewItem.setOnClickListener { itemClick(pokemonEntity) }

            Glide.with(root)
                .load(pokemonEntity.getImageUrl())
                .into(imageviewPokemon)
        }

    }

    companion object {
        private val diffCallback = object : DiffUtil.ItemCallback<PokemonEntity>() {

            override fun areItemsTheSame(oldItem: PokemonEntity, newItem: PokemonEntity): Boolean {
                return oldItem == newItem
            }

            override fun areContentsTheSame(oldItem: PokemonEntity, newItem: PokemonEntity): Boolean {
                return oldItem == newItem
            }
        }
    }

}
