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
        setupSmartRefreshLayout()
    }

    private fun setupAdapter() {
        adapter = MyListAdapter()
        binding.recyclerView.layoutManager = GridLayoutManager(this, 2)
        binding.recyclerView.addItemDecoration(GridSpaceDecoration.newInstance(10))
        binding.recyclerView.adapter = adapter
        adapter.submitList(getPageList())
    }

    private fun setupSmartRefreshLayout() {
        binding.refreshLayout.setEnableRefresh(false)
        binding.refreshLayout.setOnLoadMoreListener {
            lastIndex = lastIndex + pageNumber
            if (lastIndex >= 50) {
                binding.refreshLayout.finishRefreshWithNoMoreData()
                return@setOnLoadMoreListener
            }
            val pageList = getPageList()
            val newList = mutableListOf<Item>()
            newList.addAll(adapter.currentList)
            newList.addAll(pageList)
            adapter.submitList(newList)
            binding.refreshLayout.finishLoadMore(300)
        }
    }

    private var lastIndex: Int = 1
    private var pageNumber = 10
    private fun getPageList(): List<Item> {
        val colorList = getColorList()
        var result = mutableListOf<Item>()
        for (i in lastIndex until lastIndex + pageNumber) {
            result.add(Item(i, colorList[i % colorList.size]))
        }
        return result
    }
}