package com.example.supercars.ui.main

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.supercars.MainViewModel
import com.example.supercars.R
import com.example.supercars.databinding.FragmentFirstBinding
import com.example.supercars.repository.room.Car
import com.example.supercars.ui.main.adapter.CarsAdapter
import com.example.supercars.ui.main.adapter.SwipeHelper
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach

/**
 * A simple [Fragment] subclass as the default destination in the navigation.
 */
class FirstFragment : Fragment() {
//    companion object {
//        fun newInstance() = MainFragment()
//    }

    private val viewModel: MainViewModel by viewModels()
    private val adapter: CarsAdapter? get() = views {  superCarList.adapter as? CarsAdapter }
    private var _binding: FragmentFirstBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        _binding = FragmentFirstBinding.inflate(inflater, container, false)
        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        views {
            superCarList.adapter = CarsAdapter()
            SwipeHelper(viewModel::delete).attachToRecyclerView(superCarList)
            fab.setOnClickListener {
                findNavController().navigate(R.id.action_FirstFragment_to_SecondFragment)
            }

        viewModel.cars.onEach(::renderCars).launchIn(lifecycleScope)

        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun renderCars(cars: List<Car>) {
        adapter?.submitList(cars)
    }

    private fun <T> views(block: FragmentFirstBinding.() -> T): T? = binding.block()

}