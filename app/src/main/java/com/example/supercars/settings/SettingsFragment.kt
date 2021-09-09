package com.example.supercars.settings

import android.os.Bundle
import androidx.preference.PreferenceFragmentCompat
import com.example.supercars.R
import com.example.supercars.databinding.FragmentFirstBinding

class SettingsFragment: PreferenceFragmentCompat() {
    override fun onCreatePreferences(savedInstanceState: Bundle?, rootKey: String?) {
        return
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        addPreferencesFromResource(R.xml.settings)
    }


}