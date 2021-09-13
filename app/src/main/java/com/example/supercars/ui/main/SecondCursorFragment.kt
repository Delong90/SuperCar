package com.example.supercars.ui.main

import android.content.SharedPreferences
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.SimpleCursorAdapter
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.preference.PreferenceManager
import com.example.supercars.R
import com.example.supercars.databinding.FragmentSecondBinding
import com.example.supercars.databinding.FragmentSecondCursorBinding
import com.example.supercars.repository.DatabaseHelper

class SecondCursorFragment : Fragment() {

    private var _binding: FragmentSecondCursorBinding? = null
    private val binding get() = _binding!!
    var methodDB = "Cursor"
    var databaseHelper: DatabaseHelper? = null
    var db: SQLiteDatabase? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        println("onCreateView Second Fragment")
        _binding = FragmentSecondCursorBinding.inflate(inflater, container, false)
        binding.carYearPicker.minValue = 1890
        binding.carYearPicker.maxValue = 2021
        binding.carYearPicker.wrapSelectorWheel = true
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        databaseHelper = DatabaseHelper(view.context)
        binding.buttonNewSuperCar.setOnClickListener {
            if(binding.carModel.text.toString() != "" &&
                binding.carPrice.text.toString()!= ""&&
                binding.carPrice.text.toString().toInt() > 0) {
//                saveCar()
                val brand = binding.carBrandSpinner.selectedItem.toString()
                val model = binding.carModel.text.toString()
                var year =  binding.carYearPicker.value.toString().toInt()
                val price = binding.carPrice.text.toString().toInt()

                db = databaseHelper!!.readableDatabase
                db!!.execSQL("INSERT INTO  ${DatabaseHelper.TABLE} (${DatabaseHelper.COLUMN_BRAND}, ${DatabaseHelper.COLUMN_MODEL}, ${DatabaseHelper.COLUMN_YEAR}, ${DatabaseHelper.COLUMN_PRICE}) VALUES ('$brand','$model', $year, $price);")

                findNavController().navigate(R.id.action_secondCursorFragment_to_firstCursorFragment)
            } else{
                Toast.makeText(context, "Invalid input!", Toast.LENGTH_LONG).show()
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        db!!.close()
        _binding = null
    }

    override fun onResume() {
        var sharedPreferences: SharedPreferences = PreferenceManager.getDefaultSharedPreferences(context)
        var regularDB = sharedPreferences.getString(getString(R.string.list_preference_room_cursor), "")
        when(regularDB){
            "Cursor" -> methodDB = "Cursor"
            "Room" -> methodDB = "Room"
        }
        if (methodDB == "Room"){findNavController().navigate(R.id.action_secondCursorFragment_to_FirstFragment)}

        super.onResume()
    }
}