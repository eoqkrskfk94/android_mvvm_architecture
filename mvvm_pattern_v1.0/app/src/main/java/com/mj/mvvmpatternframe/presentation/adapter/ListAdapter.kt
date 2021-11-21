package com.mj.mvvmpatternframe.presentation.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.mj.mvvmpatternframe.data.entity.PokemonEntity
import com.mj.mvvmpatternframe.databinding.ItemPokemonBinding

class ListAdapter(private val pokemonList: MutableList<PokemonEntity>, private val itemClick: (PokemonEntity) -> Unit): RecyclerView.Adapter<ListAdapter.ListViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListViewHolder {
        val binding = ItemPokemonBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ListViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ListAdapter.ListViewHolder, position: Int) {
        holder.bind(pokemonList[position])
    }

    override fun getItemCount(): Int = pokemonList.size

    fun setData(newPokemonList: List<PokemonEntity>) {
        val diffCallback = DiffCallback(pokemonList, newPokemonList)
        val diffResult = DiffUtil.calculateDiff(diffCallback)
        pokemonList.clear()
        pokemonList.addAll(newPokemonList)
        diffResult.dispatchUpdatesTo(this)
    }


    inner class ListViewHolder(private val binding: ItemPokemonBinding): RecyclerView.ViewHolder(binding.root) {

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

    inner class DiffCallback(
        private var oldList: List<PokemonEntity>,
        private var newList: List<PokemonEntity>
    ): DiffUtil.Callback() {

        override fun getOldListSize(): Int = oldList.size

        override fun getNewListSize(): Int = newList.size

        override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
            return oldList[oldItemPosition] == newList[newItemPosition]
        }

        override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
            return oldList[oldItemPosition].name == newList[newItemPosition].name
        }

    }
}