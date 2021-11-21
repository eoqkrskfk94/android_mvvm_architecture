package com.mj.mvvmpatternframe.presentation.list

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.core.view.isVisible
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.mj.mvvmpatternframe.databinding.ActivityListBinding
import com.mj.mvvmpatternframe.presentation.adapter.ListAdapter
import com.mj.mvvmpatternframe.presentation.favorite.FavoriteActivity
import org.koin.androidx.viewmodel.ext.android.getViewModel

class ListActivity : AppCompatActivity() {

    private var _binding: ActivityListBinding? = null
    private val binding get() = _binding!!
    private lateinit var viewModel: ListViewModel
    private lateinit var adapter: ListAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = ActivityListBinding.inflate(layoutInflater)
        setContentView(binding.root)


        //viewModel initiate
        viewModel  = getViewModel()
        binding.vm = viewModel


        initView()
    }

    private fun initView() {
        binding.recyclerViewList.layoutManager = LinearLayoutManager(this)
        binding.progressBarLoading.isVisible = true

        adapter = ListAdapter(mutableListOf()) { pokemonEntity ->
            Toast.makeText(this, "${pokemonEntity.name} added to favorite", Toast.LENGTH_SHORT).show()
            viewModel.addFavoritePokemon(pokemonEntity)
            pokemonEntity.favorite = true
        }

        binding.recyclerViewList.adapter = adapter

        viewModel.pokemonList.observe(this, Observer {
            binding.progressBarLoading.isVisible = false
            adapter.setData(it)
        })

        //button to favorite activity
        binding.floatingBtnFavorite.setOnClickListener {
            startActivity(Intent(this, FavoriteActivity::class.java))
        }
    }
}