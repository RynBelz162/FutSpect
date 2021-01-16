package com.belzsoftware.futspect.ui.more


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatDelegate
import androidx.fragment.app.Fragment
import androidx.preference.PreferenceManager
import com.belzsoftware.futspect.R
import com.belzsoftware.futspect.databinding.FragmentMoreBinding
import com.belzsoftware.futspect.util.NIGHT_MODE_PREF
import com.belzsoftware.futspect.util.extensions.createLongSnackbar

class MoreFragment : Fragment() {

    private var _binding: FragmentMoreBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentMoreBinding.inflate(layoutInflater)
        return binding.root;
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        setButtonNameIcon()
        binding.buttonMoreDarkTheme.setOnClickListener { toggleDarkTheme() }
        binding.buttonMoreAbout.setOnClickListener { activity?.createLongSnackbar(R.string.more_about) }
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
        binding.buttonMoreDarkTheme.setIconResource(resourceId)
        binding.buttonMoreDarkTheme.setText(stringId)
    }

    private fun setNightMode(mode: Int) {
        AppCompatDelegate.setDefaultNightMode(mode)

        PreferenceManager
            .getDefaultSharedPreferences(context)
            .edit()
            .putInt(NIGHT_MODE_PREF, mode)
            .apply()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
