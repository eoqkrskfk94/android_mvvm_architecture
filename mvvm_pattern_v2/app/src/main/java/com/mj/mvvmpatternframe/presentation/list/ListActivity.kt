package com.mj.mvvmpatternframe.presentation.list

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.activity.viewModels
import androidx.core.view.isVisible
import androidx.lifecycle.Observer
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.mj.mvvmpatternframe.databinding.ActivityListBinding
import com.mj.mvvmpatternframe.presentation.adapter.ListAdapter
import com.mj.mvvmpatternframe.presentation.adapter.PagingAdapter
import com.mj.mvvmpatternframe.presentation.favorite.FavoriteActivity
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import org.koin.androidx.viewmodel.ext.android.getViewModel

@AndroidEntryPoint
class ListActivity : AppCompatActivity() {

    private var _binding: ActivityListBinding? = null
    private val binding get() = _binding!!
    private val viewModel: ListViewModel by viewModels()
    private lateinit var adapter: PagingAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = ActivityListBinding.inflate(layoutInflater)
        setContentView(binding.root)


        //viewModel initiate
        //viewModel  = getViewModel()
        binding.vm = viewModel


        initView()
    }

    private fun initView() {
        binding.recyclerViewList.layoutManager = LinearLayoutManager(this)

        adapter = PagingAdapter() { pokemonEntity ->
            Toast.makeText(this, "${pokemonEntity.name} added to favorite", Toast.LENGTH_SHORT).show()
            //viewModel.addFavoritePokemon(pokemonEntity)
            pokemonEntity.favorite = true
        }

        binding.recyclerViewList.adapter = adapter

        lifecycleScope.launch {
            viewModel.getContent().collectLatest {
                (binding.recyclerViewList.adapter as PagingAdapter).submitData(it)
            }
        }


        //button to favorite activity
        binding.floatingBtnFavorite.setOnClickListener {
            startActivity(Intent(this, FavoriteActivity::class.java))
        }
    }
}