package com.example.multithread_async

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.multithread_async.databinding.ActivityMainBinding
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {
    private lateinit var viewModel: MainViewModel
    private lateinit var binding: ActivityMainBinding
    private val adapter by lazy {
        Adapter()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        viewModel = ViewModelProvider(this)[MainViewModel::class.java]
        binding.rvRecycler.layoutManager = LinearLayoutManager(this)
        binding.rvRecycler.adapter = adapter

        this.lifecycleScope.launch {
            this@MainActivity.lifecycle.repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.randomNumFlow.collect {
                    adapter.submitList(it)
                }
            }
        }
    }
}