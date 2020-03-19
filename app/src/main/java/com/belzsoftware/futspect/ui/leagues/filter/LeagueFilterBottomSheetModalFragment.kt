package com.belzsoftware.futspect.ui.leagues.filter

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.belzsoftware.futspect.R
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import kotlinx.android.synthetic.main.fragment_leagues_filter.*

class LeagueFilterBottomSheetModalFragment : BottomSheetDialogFragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
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