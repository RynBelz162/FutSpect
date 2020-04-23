package com.belzsoftware.futspect.ui.leagues.filter

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.belzsoftware.futspect.databinding.FragmentLeaguesFilterBinding
import com.belzsoftware.futspect.util.viewModelProvider
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import dagger.android.support.AndroidSupportInjection
import kotlinx.android.synthetic.main.fragment_leagues_filter.*
import javax.inject.Inject

class LeagueFilterBottomSheetModalFragment : BottomSheetDialogFragment() {

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory
    private lateinit var leagueFilterViewModel: LeagueFilterViewModel
    private lateinit var binding: FragmentLeaguesFilterBinding;

    override fun onAttach(context: Context) {
        AndroidSupportInjection.inject(this)
        super.onAttach(context)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        leagueFilterViewModel = viewModelProvider(viewModelFactory)

        binding = FragmentLeaguesFilterBinding.inflate(inflater, container, false).apply {
            viewModel = leagueFilterViewModel
            lifecycleOwner = viewLifecycleOwner
        }

        leagueFilterViewModel.navigateToLeagues.observe(viewLifecycleOwner, Observer {
            it.getContentIfNotHandled()?.let {
                closeSheet()
            }
        })

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        button_leagueFulters_close.setOnClickListener {
            closeSheet()
        }
    }

    private fun closeSheet() {
        val navController = findNavController()
        navController.popBackStack()
    }

}