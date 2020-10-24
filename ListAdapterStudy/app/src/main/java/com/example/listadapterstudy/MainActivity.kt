package com.example.listadapterstudy

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.GridLayoutManager
import com.example.listadapterstudy.databinding.MainActivityBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: MainActivityBinding
    private lateinit var adapter: MyListAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = MainActivityBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setupAdapter()
    }

    private fun setupAdapter() {
        adapter = MyListAdapter()
        binding.recyclerView.layoutManager = GridLayoutManager(this, 2)
        binding.recyclerView.addItemDecoration(GridSpaceDecoration.newInstance(10))
        binding.recyclerView.adapter = adapter
        adapter.submitList(getList())
    }


    private fun getList(): List<Item> {
        val colorList = getColorList()
        var result = mutableListOf<Item>()
        for (i in 1..50) {
            result.add(Item(i, colorList[i % colorList.size]))
        }
        return result
    }
}