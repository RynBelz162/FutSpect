package com.belzsoftware.futspect.ui.leagues.preferences

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.belzsoftware.futspect.databinding.FragmentLeaguesPreferencesBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class LeaguePreferencesFragment : Fragment() {

    private var _binding: FragmentLeaguesPreferencesBinding? = null
    private val binding get() = _binding!!

    private val viewModel: LeaguePreferencesViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentLeaguesPreferencesBinding.inflate(inflater)
        return binding.root;
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}