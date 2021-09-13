package com.example.supercars.repository.room


import androidx.room.*
import kotlinx.coroutines.flow.Flow

@Dao
interface CarsDao {

    @Query("SELECT * FROM cars order by _id")
    fun getAll(): Flow<List<Car>>

    @Query("SELECT * FROM cars order by price")
    fun getAllPrice():Flow<List<Car>>

    @Query("SELECT * FROM cars order by brand")
    fun getAllBrand():Flow<List<Car>>

    @Query("SELECT * FROM cars order by year")
    fun getAllYear():Flow<List<Car>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun add(car: Car)

    @Delete
    suspend fun delete(car: Car)

}