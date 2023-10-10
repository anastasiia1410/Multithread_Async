package com.example.multithread_async

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.multithread_async.databinding.ItemNumberBinding

class Adapter : ListAdapter<Int, Adapter.VH>(DiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VH {
        return VH(ItemNumberBinding.inflate(LayoutInflater.from(parent.context), parent, false))
    }

    override fun onBindViewHolder(holder: VH, position: Int) {
        val number = getItem(position)
        holder.bind(number)
    }

    class VH(private val binding: ItemNumberBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(number: Int) {
            binding.tvNum.text = number.toString()
        }
    }

    class DiffCallback : DiffUtil.ItemCallback<Int>() {
        override fun areItemsTheSame(oldNum: Int, newNum: Int): Boolean =
            oldNum == newNum

        override fun areContentsTheSame(oldNum: Int, newNum: Int) =
            oldNum == newNum
    }


}
