package com.example.supercars.ui.main

import android.content.SharedPreferences
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.preference.PreferenceManager
import com.example.supercars.R
import com.example.supercars.databinding.FragmentFirstBinding
import com.example.supercars.ui.main.adapter.CarsAdapter
import com.example.supercars.ui.main.adapter.SwipeHelper
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import com.example.supercars.databinding.FragmentFirstCursorBinding


class FirstCursorFragment : Fragment() {

    private var _binding: FragmentFirstCursorBinding? = null
    private val binding get() = _binding!!
    var methodDB = "Cursor"


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentFirstCursorBinding.inflate(inflater, container, false)
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
        if (methodDB == "Room"){
            findNavController().navigate(R.id.action_firstCursorFragment_to_FirstFragment)
        }else {
            binding.fab.setOnClickListener {
                findNavController().navigate(R.id.action_firstCursorFragment_to_secondCursorFragment)
            }
        }
    }

    override fun onResume() {
        var sharedPreferences: SharedPreferences = PreferenceManager.getDefaultSharedPreferences(context)
        var regularDB = sharedPreferences.getString(getString(R.string.list_preference_room_cursor), "")
        when(regularDB){
            "Cursor" -> methodDB = "Cursor"
            "Room" -> methodDB = "Room"
        }
        if (methodDB == "Room"){findNavController().navigate(R.id.action_firstCursorFragment_to_FirstFragment)}
        super.onResume()
    }


}