package com.example.supercars.ui.main.adapter


import androidx.recyclerview.widget.ItemTouchHelper
import com.example.supercars.repository.room.Car

class SwipeHelper(onSwiped: (Car) -> Unit,): ItemTouchHelper(SwipeCallback(onSwiped))