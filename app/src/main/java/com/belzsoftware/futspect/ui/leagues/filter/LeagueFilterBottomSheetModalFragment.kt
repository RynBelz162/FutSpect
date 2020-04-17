package com.belzsoftware.futspect.ui.leagues.filter

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.belzsoftware.futspect.R
import com.belzsoftware.futspect.ui.leagues.LeaguesViewModel
import com.belzsoftware.futspect.util.viewModelProvider
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import dagger.android.support.AndroidSupportInjection
import kotlinx.android.synthetic.main.fragment_leagues_filter.*
import javax.inject.Inject

class LeagueFilterBottomSheetModalFragment : BottomSheetDialogFragment() {

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory
    private lateinit var leaguesViewModel: LeaguesViewModel

    override fun onAttach(context: Context) {
        AndroidSupportInjection.inject(this)
        super.onAttach(context)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        leaguesViewModel = viewModelProvider(viewModelFactory)

        return inflater.inflate(R.layout.fragment_leagues_filter, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        button_leagueFilters_apply.setOnClickListener {
            closeSheet()
        }

        button_leagueFulters_close.setOnClickListener {
            closeSheet()
        }
    }

    private fun closeSheet() {
        val navController = findNavController()
        navController.popBackStack()
    }

}