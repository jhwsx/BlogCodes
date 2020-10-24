package com.example.listadapterstudy

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.listadapterstudy.databinding.RecycleItemBinding

/**
 * Adapter
 *
 * @author wangzhichao
 * @date 20-10-24
 */
class MyListAdapter : ListAdapter<Item, MyListAdapter.MyViewHolder>(diffCallback) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
       return MyViewHolder.from(parent)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.bind(currentList[position], position)
    }

    class MyViewHolder(private val binding: RecycleItemBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(item: Item, position: Int) {
            item.apply {
                binding.tv.text = number.toString()
                binding.cv.setCardBackgroundColor(ContextCompat.getColor(itemView.context, colorRes))
            }
        }

        companion object {
            fun from(parent: ViewGroup): MyViewHolder {
                val inflater = LayoutInflater.from(parent.context)
                val binding = RecycleItemBinding.inflate(inflater, parent, false)
                return MyViewHolder(binding)
            }
        }
    }

    companion object {
        val diffCallback = object: DiffUtil.ItemCallback<Item>() {
            override fun areItemsTheSame(oldItem: Item, newItem: Item): Boolean {
                return oldItem == newItem
            }

            override fun areContentsTheSame(oldItem: Item, newItem: Item): Boolean {
                return oldItem == newItem
            }

        }
    }
}