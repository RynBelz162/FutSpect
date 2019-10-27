package com.belzsoftware.futspect.ui.more


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatDelegate
import androidx.fragment.app.Fragment
import com.belzsoftware.futspect.R
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
        val currentMode = AppCompatDelegate.getDefaultNightMode()

        if (currentMode == AppCompatDelegate.MODE_NIGHT_YES) {
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
            return
        }

        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)
    }
}
