package com.belzsoftware.futspect.ui.more


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatDelegate
import androidx.fragment.app.Fragment
import androidx.preference.PreferenceManager
import com.belzsoftware.futspect.R
import com.belzsoftware.futspect.util.NIGHT_MODE_PREF
import com.google.android.material.button.MaterialButton

class MoreFragment : Fragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_more, container, false)

        val dayNightButton: MaterialButton = view!!.findViewById(R.id.button_dark_theme)
        dayNightButton.setOnClickListener { toggleDarkTheme() }

        return view
    }

    private fun toggleDarkTheme() {
        val pref = PreferenceManager
            .getDefaultSharedPreferences(context)
            .getInt(NIGHT_MODE_PREF, AppCompatDelegate.MODE_NIGHT_NO)

        when (pref) {
            AppCompatDelegate.MODE_NIGHT_NO -> setNightMode(AppCompatDelegate.MODE_NIGHT_YES)
            AppCompatDelegate.MODE_NIGHT_YES -> setNightMode(AppCompatDelegate.MODE_NIGHT_NO)
        }
    }

    private fun setNightMode(mode: Int) {
        AppCompatDelegate.setDefaultNightMode(mode)

        PreferenceManager
            .getDefaultSharedPreferences(context)
            .edit()
            .putInt(NIGHT_MODE_PREF, mode)
            .apply()
    }
}
