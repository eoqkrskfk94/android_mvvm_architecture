package com.mj.mvvmpatternframe.presentation.main

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import androidx.lifecycle.Observer
import com.mj.mvvmpatternframe.databinding.ActivityMainBinding
import com.mj.mvvmpatternframe.presentation.list.ListActivity
import dagger.hilt.android.AndroidEntryPoint
import org.koin.androidx.viewmodel.ext.android.getViewModel
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private val viewModel: MainViewModel by viewModels()

    private var _binding: ActivityMainBinding? = null
    private val binding get() = _binding!!
    //private lateinit var viewModel: MainViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)


        //viewModel initiate
        //viewModel  = getViewModel()
        binding.vm = viewModel

        //observe ui
        viewModel.countText.observe(this, {
            binding.textviewNumber.text = it
        })

        //to ListActivity
        binding.buttonToList.setOnClickListener {
            startActivity(Intent(this, ListActivity::class.java))
        }

    }
}