package com.example.supercars.repository.room

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "cars")
data class Car(
//    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    @PrimaryKey(autoGenerate = true) @ColumnInfo(name = "_id") val id: Int = 0,
    val brand:String,
    val model:String,
    val year: Int,
//    val volume:Double,
    val price: Int
)