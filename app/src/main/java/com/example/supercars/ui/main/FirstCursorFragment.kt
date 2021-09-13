package com.example.supercars.ui.main

import android.annotation.SuppressLint
import android.content.SharedPreferences
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.SimpleCursorAdapter
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.preference.PreferenceManager
import androidx.room.Query
import com.example.supercars.R
import com.example.supercars.databinding.FragmentFirstCursorBinding
import com.example.supercars.repository.DatabaseHelper
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach


class FirstCursorFragment : Fragment() {

    private var _binding: FragmentFirstCursorBinding? = null
    private val binding get() = _binding!!
    var methodDB = "Cursor"
    var databaseHelper: DatabaseHelper? = null
    var db: SQLiteDatabase? = null
    var userCursor: Cursor? = null
    var userAdapter: SimpleCursorAdapter? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentFirstCursorBinding.inflate(inflater, container, false)
        return binding.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        databaseHelper = DatabaseHelper(context)
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
    @SuppressLint("Recycle")
    override fun onResume() {
        var sharedPreferences: SharedPreferences = PreferenceManager.getDefaultSharedPreferences(context)
        var regular = sharedPreferences.getString(getString(R.string.list_preference), "")
        db = databaseHelper!!.readableDatabase
        when(regular){
            "Price" ->userCursor = db!!.rawQuery("select * from " + DatabaseHelper.TABLE + " order by " + DatabaseHelper.COLUMN_PRICE, null)
            "Brand"-> userCursor = db!!.rawQuery("select * from " + DatabaseHelper.TABLE + " order by " + DatabaseHelper.COLUMN_BRAND, null)
            "Year"-> userCursor = db!!.rawQuery("select * from " + DatabaseHelper.TABLE + " order by " + DatabaseHelper.COLUMN_YEAR, null)
            else-> userCursor = db!!.rawQuery("select * from " + DatabaseHelper.TABLE + " order by " + DatabaseHelper.COLUMN_ID, null)
        }
//        userCursor = db!!.rawQuery("select * from " + DatabaseHelper.TABLE, null)
        val headers = arrayOf(
            DatabaseHelper.COLUMN_BRAND,
            DatabaseHelper.COLUMN_MODEL,
            DatabaseHelper.COLUMN_YEAR,
            DatabaseHelper.COLUMN_PRICE
        )
        userAdapter = SimpleCursorAdapter(
            view?.context, R.layout.line_item,
            userCursor, headers, intArrayOf(R.id.text1, R.id.text2, R.id.text3, R.id.text4), 0
        )

        binding.list.adapter = userAdapter

        var regularDB = sharedPreferences.getString(getString(R.string.list_preference_room_cursor), "")
        when(regularDB){
            "Cursor" -> methodDB = "Cursor"
            "Room" -> methodDB = "Room"
        }
        if (methodDB == "Room"){findNavController().navigate(R.id.action_firstCursorFragment_to_FirstFragment)}
        super.onResume()
    }

    override fun onDestroy() {
        super.onDestroy()
        db!!.close()
        userCursor!!.close()
    }

}