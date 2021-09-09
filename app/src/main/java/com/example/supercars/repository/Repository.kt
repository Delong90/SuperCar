package com.example.supercars.repository



import com.example.supercars.repository.room.Car
import com.example.supercars.repository.room.CarsDatabase
import kotlinx.coroutines.flow.Flow

class Repository(
    private val db: CarsDatabase,
) {

    private val dao get() = db.carsDao

    fun getAll(): Flow<List<Car>> = dao.getAll()

    fun getAllPrice(): Flow<List<Car>> = dao.getAllPrice()

    fun getAllBrand(): Flow<List<Car>> = dao.getAllBrand()

    fun getAllYear(): Flow<List<Car>> = dao.getAllYear()

    suspend fun save(car: Car) = dao.add(car)

    suspend fun delete(car: Car) = dao.delete(car)

}