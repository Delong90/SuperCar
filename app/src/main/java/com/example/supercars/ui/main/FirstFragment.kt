package com.example.supercars.ui.main

import android.content.SharedPreferences
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.preference.PreferenceManager
import com.example.supercars.MainViewModel
import com.example.supercars.R
import com.example.supercars.databinding.FragmentFirstBinding
import com.example.supercars.repository.room.Car
import com.example.supercars.ui.main.adapter.CarsAdapter
import com.example.supercars.ui.main.adapter.SwipeHelper
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach

class FirstFragment : Fragment() {

    private val viewModel: MainViewModel by viewModels()
    private var _binding: FragmentFirstBinding? = null
    private val binding get() = _binding!!
    private val adapter: CarsAdapter? get() =   binding.superCarList.adapter as? CarsAdapter
    var methodDB = "Room"


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentFirstBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        var sharedPreferences: SharedPreferences = PreferenceManager.getDefaultSharedPreferences(context)
        var regularDB = sharedPreferences.getString(getString(R.string.list_preference_room_cursor), "")
        when(regularDB){
            "Cursor" -> methodDB = "Cursor"
            "Room" -> methodDB = "Room"
        }
        if (methodDB == "Cursor"){
            findNavController().navigate(R.id.action_FirstFragment_to_firstCursorFragment)
        } else {
            binding.superCarList.adapter = CarsAdapter()
            SwipeHelper(viewModel::delete).attachToRecyclerView(binding.superCarList)
            binding.fab.setOnClickListener {
                findNavController().navigate(R.id.action_FirstFragment_to_SecondFragment)
            }
            viewModel.cars.onEach(::renderCars).launchIn(lifecycleScope)
        }
    }

//    override fun onDestroyView() {
//        super.onDestroyView()
//        _binding = null
//    }

    override fun onResume() {
        var sharedPreferences: SharedPreferences = PreferenceManager.getDefaultSharedPreferences(context)
        var regular = sharedPreferences.getString(getString(R.string.list_preference), "")
        when(regular){
            "Default" -> viewModel.cars.onEach(::renderCars).launchIn(lifecycleScope)
            "Brand"-> viewModel.carsBrand.onEach(::renderCars).launchIn(lifecycleScope)
            "Year"-> viewModel.carsYear.onEach(::renderCars).launchIn(lifecycleScope)
            else-> viewModel.carsPrice.onEach(::renderCars).launchIn(lifecycleScope)
        }
        var regularDB = sharedPreferences.getString(getString(R.string.list_preference_room_cursor), "")
        when(regularDB){
            "Cursor" -> methodDB = "Cursor"
            "Room" -> methodDB = "Room"
        }
        if (methodDB == "Cursor"){findNavController().navigate(R.id.action_FirstFragment_to_firstCursorFragment)}
        super.onResume()
    }
    private fun renderCars(cars: List<Car>) {
        adapter?.submitList(cars)
    }
//    private fun <T> views(block: FragmentFirstBinding.() -> T): T? = binding.block()
}