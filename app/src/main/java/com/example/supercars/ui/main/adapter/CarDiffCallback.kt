package com.example.supercars.ui.main.adapter

import androidx.recyclerview.widget.DiffUtil
import com.example.supercars.repository.room.Car

class NoteDiffCallback : DiffUtil.ItemCallback<Car>() {
    override fun areItemsTheSame(oldItem: Car, newItem: Car): Boolean = oldItem.id == newItem.id

    override fun areContentsTheSame(oldItem: Car, newItem: Car): Boolean = oldItem == newItem

}