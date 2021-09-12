package com.example.supercars.ui.main

import android.content.SharedPreferences
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.preference.PreferenceManager
import com.example.supercars.MainViewModel
import com.example.supercars.R
import com.example.supercars.databinding.FragmentSecondBinding
import com.example.supercars.repository.room.Car
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach

class SecondFragment : Fragment() {
    private val viewModel: MainViewModel by viewModels()
    private var _binding: FragmentSecondBinding? = null
    private val binding get() = _binding!!
    var methodDB = "Room"



    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentSecondBinding.inflate(inflater, container, false)
        binding.carYearPicker.minValue = 1890
        binding.carYearPicker.maxValue = 2021
        binding.carYearPicker.wrapSelectorWheel = true
        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.buttonNewSuperCar.setOnClickListener {
            if(binding.carModel.text.toString() != "" &&
                binding.carPrice.text.toString()!= ""&&
                binding.carPrice.text.toString().toInt() > 0) {
                saveCar()
                findNavController().navigate(R.id.action_SecondFragment_to_FirstFragment)
            } else{
                Toast.makeText(context, "Invalid input!", Toast.LENGTH_LONG).show()
            }
        }
    }


    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun saveCar() {
        val brand = binding.carBrandSpinner.selectedItem.toString().takeIf { it.isNotBlank() } ?: return
        val model = binding.carModel.text.toString().takeIf { it.isNotBlank() } ?: return
        var year =  binding.carYearPicker.value
//            val volume = binding.carVolumeSpinner.selectedItem.toString().takeIf { it.isNotBlank() } ?: return
        val price = binding.carPrice.text.toString().takeIf { it.isNotBlank() } ?: return
        val car = Car(
            brand = brand,
            model = model,
            year = year,
//                volume = volume.toDouble(),
            price = price.toInt()
        )
        viewModel.save(car)
    }

    override fun onResume() {
        var sharedPreferences: SharedPreferences = PreferenceManager.getDefaultSharedPreferences(context)
        var regularDB = sharedPreferences.getString(getString(R.string.list_preference_room_cursor), "")
        when(regularDB){
            "Cursor" -> methodDB = "Cursor"
            "Room" -> methodDB = "Room"
        }
        if (methodDB == "Cursor"){findNavController().navigate(R.id.action_SecondFragment_to_firstCursorFragment)}

        super.onResume()
    }



//    private fun <T> views(block: FragmentSecondBinding.() -> T): T? = binding.block()
}