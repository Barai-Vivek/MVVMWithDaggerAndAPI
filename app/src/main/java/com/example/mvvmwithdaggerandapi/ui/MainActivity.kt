package com.example.mvvmwithdaggerandapi.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.activity.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.mvvmwithdaggerandapi.adapter.PeopleAdapter
import com.example.mvvmwithdaggerandapi.databinding.ActivityMainBinding
import com.example.mvvmwithdaggerandapi.model.People
import com.example.mvvmwithdaggerandapi.util.DataStatus
import com.example.mvvmwithdaggerandapi.util.initRecycler
import com.example.mvvmwithdaggerandapi.util.isVisible
import com.example.mvvmwithdaggerandapi.viewmodel.MainViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private var _binding : ActivityMainBinding? = null
    private val binding get() = _binding!!

    @Inject
    lateinit var peopleAdapter: PeopleAdapter
    private val viewModel: MainViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setUpRecyclerView()

        lifecycleScope.launch {
            binding.apply {
                viewModel.getPeopleData()
                viewModel.peopleData.observe(this@MainActivity){
                    when(it.status){
                        DataStatus.Status.LOADING -> {
                            progressBar.isVisible(true, peopleRecyclerView)
                        }
                        DataStatus.Status.SUCCESS -> {
                            progressBar.isVisible(false, peopleRecyclerView)
                            peopleAdapter.setData(it.data?: emptyList())
                            peopleAdapter.setItemClickListener {
                                Toast.makeText(this@MainActivity, "Handle click -> ${it.name}", Toast.LENGTH_SHORT).show()
                            }
                        }
                        DataStatus.Status.ERROR -> {
                            progressBar.isVisible(false, peopleRecyclerView)
                            Toast.makeText(this@MainActivity, "Something is wrong", Toast.LENGTH_SHORT).show()
                        }
                    }
                }
            }
        }
    }

    private fun setUpRecyclerView(){
        binding.peopleRecyclerView.initRecycler(peopleAdapter, LinearLayoutManager(this@MainActivity))
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}