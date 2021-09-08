package com.example.supercars.ui.main

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.example.supercars.MainViewModel
import com.example.supercars.R
import com.example.supercars.databinding.FragmentSecondBinding
import com.example.supercars.repository.room.Car

/**
 * A simple [Fragment] subclass as the second destination in the navigation.
 */
class SecondFragment : Fragment() {
    private val viewModel: MainViewModel by viewModels()
    private var _binding: FragmentSecondBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        _binding = FragmentSecondBinding.inflate(inflater, container, false)
        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)



        binding.buttonNewSuperCar.setOnClickListener {
            saveCar()
            findNavController().navigate(R.id.action_SecondFragment_to_FirstFragment)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }


    private fun saveCar() {
            val brand = binding.carBrand.text.toString().takeIf { it.isNotBlank() } ?: return
            val model = binding.carModel.text.toString().takeIf { it.isNotBlank() } ?: return
            val year = binding.carYear.text.toString().takeIf { it.isNotBlank() } ?: return
            val volume = binding.carVolume.text.toString().takeIf { it.isNotBlank() } ?: return

            val car = Car(
                brand = brand,
                model = model,
                year = year.toInt(),
                volume = volume.toDouble()
            )

            viewModel.save(car)


    }

}