package com.example.supercars

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.supercars.locator.locateLazy
import com.example.supercars.repository.Repository
import com.example.supercars.repository.room.Car
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.shareIn
import kotlinx.coroutines.launch



class MainViewModel : ViewModel() {

    private val repository: Repository by locateLazy()

    val cars = repository.getAll().asLiveDataFlow()

    fun save(car: Car) {
        viewModelScope.launch { repository.save(createCar(car)) }
    }

    fun delete(car: Car) {
        viewModelScope.launch { repository.delete(car) }
    }

    private fun createCar(car: Car) = Car(
        brand = car.brand,
        model = car.model,
        year = car.year,
        volume = car.volume

    )

    private fun <T> Flow<T>.asLiveDataFlow() =
        shareIn(viewModelScope, SharingStarted.Eagerly, replay = 1)

}

