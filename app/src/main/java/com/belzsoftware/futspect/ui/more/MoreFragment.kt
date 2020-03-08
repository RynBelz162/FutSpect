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
import com.belzsoftware.futspect.util.createLongSnackbar
import kotlinx.android.synthetic.main.fragment_more.*

class MoreFragment : Fragment() {


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_more, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        setButtonNameIcon()
        button_more_darkTheme.setOnClickListener { toggleDarkTheme() }
        button_more_about.setOnClickListener { activity?.createLongSnackbar(R.string.more_about) }
    }

    private fun setButtonNameIcon() {
        val pref = PreferenceManager
            .getDefaultSharedPreferences(context)
            .getInt(NIGHT_MODE_PREF, AppCompatDelegate.MODE_NIGHT_NO)

        when (pref) {
            AppCompatDelegate.MODE_NIGHT_NO -> toggleButtonNameIcon(
                R.drawable.ic_outline_brightness_2,
                R.string.more_dark_theme
            )
            AppCompatDelegate.MODE_NIGHT_YES -> toggleButtonNameIcon(
                R.drawable.ic_outline_brightness_5,
                R.string.more_light_theme
            )
        }
    }

    private fun toggleDarkTheme() {
        val pref = PreferenceManager
            .getDefaultSharedPreferences(context)
            .getInt(NIGHT_MODE_PREF, AppCompatDelegate.MODE_NIGHT_NO)

        when (pref) {
            AppCompatDelegate.MODE_NIGHT_NO -> {
                setNightMode(AppCompatDelegate.MODE_NIGHT_YES)
                toggleButtonNameIcon(
                    R.drawable.ic_outline_brightness_2,
                    R.string.more_dark_theme
                )
            }
            AppCompatDelegate.MODE_NIGHT_YES -> {
                setNightMode(AppCompatDelegate.MODE_NIGHT_NO)
                toggleButtonNameIcon(
                    R.drawable.ic_outline_brightness_5,
                    R.string.more_light_theme
                )
            }
        }
    }

    private fun toggleButtonNameIcon(resourceId: Int, stringId: Int) {
        button_more_darkTheme.setIconResource(resourceId)
        button_more_darkTheme.setText(stringId)
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
