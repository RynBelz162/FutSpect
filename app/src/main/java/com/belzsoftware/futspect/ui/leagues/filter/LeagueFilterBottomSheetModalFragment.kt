package com.belzsoftware.futspect.ui.leagues.filter

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.belzsoftware.futspect.databinding.FragmentLeaguesFilterBinding
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class LeagueFilterBottomSheetModalFragment : BottomSheetDialogFragment() {

    private lateinit var binding: FragmentLeaguesFilterBinding

    private val leagueFilterViewModel: LeagueFilterViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = FragmentLeaguesFilterBinding.inflate(inflater, container, false).apply {
            viewModel = leagueFilterViewModel
            lifecycleOwner = viewLifecycleOwner
        }

        leagueFilterViewModel.navigateToLeagues.observe(viewLifecycleOwner, {
            it.getContentIfNotHandled()?.let {
                closeSheet()
            }
        })

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.buttonLeagueFiltersClose.setOnClickListener {
            closeSheet()
        }
    }

    private fun closeSheet() {
        val navController = findNavController()
        navController.popBackStack()
    }

}