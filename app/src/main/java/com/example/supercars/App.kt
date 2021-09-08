package com.example.supercars

import android.app.Application
import android.content.Context
import com.example.supercars.locator.ServiceLocator
import com.example.supercars.locator.locate
import com.example.supercars.repository.Repository
import com.example.supercars.repository.room.CarsDatabase
import com.example.supercars.repository.room.CarsDatabaseImpl

class App: Application() {

    override fun onCreate() {
        super.onCreate()

        ServiceLocator.register<Context>(this)
        ServiceLocator.register<CarsDatabase>(CarsDatabaseImpl.create(locate()))
        ServiceLocator.register(Repository(locate()))
    }
}