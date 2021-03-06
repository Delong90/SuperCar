package com.example.supercars.ui.main.adapter

import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import com.example.supercars.repository.room.Car


class CarsAdapter : ListAdapter<Car, CarViewHolder>(CarDiffCallback()) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CarViewHolder =
        CarViewHolder.create(parent)

    override fun onBindViewHolder(holder: CarViewHolder, position: Int) =
        holder.onBind(getItem(position))

}