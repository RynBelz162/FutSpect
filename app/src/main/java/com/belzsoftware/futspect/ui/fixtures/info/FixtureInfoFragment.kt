package com.belzsoftware.futspect.ui.fixtures.info

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.navArgs
import com.belzsoftware.futspect.R
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class FixtureInfoFragment : Fragment(R.layout.fragment_fixture_info) {

    private val fixtureInfoViewModel : FixtureInfoViewModel by viewModels()
    private val args: FixtureInfoFragmentArgs by navArgs()


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        fixtureInfoViewModel.setFixtureId(args.fixtureId)

        return super.onCreateView(inflater, container, savedInstanceState)
    }
}