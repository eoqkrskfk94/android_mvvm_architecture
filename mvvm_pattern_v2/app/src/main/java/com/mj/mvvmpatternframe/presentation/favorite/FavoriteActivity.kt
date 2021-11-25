package com.mj.mvvmpatternframe.presentation.favorite

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import androidx.core.view.isVisible
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import com.mj.mvvmpatternframe.databinding.ActivityFavoriteBinding
import com.mj.mvvmpatternframe.presentation.adapter.ListAdapter
import dagger.hilt.android.AndroidEntryPoint
import org.koin.androidx.viewmodel.ext.android.getViewModel

@AndroidEntryPoint
class FavoriteActivity : AppCompatActivity() {

    private var _binding: ActivityFavoriteBinding? = null
    private val binding get() = _binding!!
    private val viewModel: FavoriteViewModel by viewModels()
    private lateinit var adapter: ListAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = ActivityFavoriteBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.vm = viewModel
        initView()
    }

    private fun initView() {
        binding.recyclerViewList.layoutManager = LinearLayoutManager(this)
        binding.progressBarLoading.isVisible = true

        adapter = ListAdapter(mutableListOf()) { pokemonEntity ->

            MaterialAlertDialogBuilder(this)
                .setMessage("delete ${pokemonEntity.name} from favorite?")
                .setNegativeButton("No") {_, _ -> }
                .setPositiveButton("Yes") {_, _ ->
                    viewModel.deletePokemonFromFavorite(pokemonEntity)
                }
                .show()

        }


        binding.recyclerViewList.adapter = adapter

        viewModel.localPokemonList.observe(this, Observer {
            binding.progressBarLoading.isVisible = false
            adapter.setData(it)
        })

    }
}