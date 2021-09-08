package com.example.supercars.ui.main.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.supercars.databinding.CarsListItemBinding
import com.example.supercars.repository.room.Car

class CarViewHolder(
    private val binding: CarsListItemBinding,
) : RecyclerView.ViewHolder(binding.root) {

    var item: Car? = null
        private set

    fun onBind(item: Car) {
//        this.item = Car(brand = "",
//        model = "",
//        year = 0,
//        volume = 0.0)
//
        this.item = item

        views {
            carBrand.text = item.brand
            carModel.text = item.model
            carYearOfIssue.text = item.year.toString()
            carEngineVolume.text = item.volume.toString()
        }
    }

    private fun <T> views(block: CarsListItemBinding.() -> T): T? = binding.block()

    companion object {
        fun create(parent: ViewGroup) = CarsListItemBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        ).let(::CarViewHolder)
    }
}