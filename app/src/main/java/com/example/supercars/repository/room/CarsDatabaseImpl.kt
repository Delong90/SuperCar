package com.example.supercars.repository.room

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [Car::class], version = 1)
abstract class CarsDatabaseImpl : RoomDatabase(), CarsDatabase {

    companion object {
        fun create(context: Context) = Room
            .databaseBuilder(
                context,
                CarsDatabaseImpl::class.java,
                "cars-database"
            )
            .build()
    }
}