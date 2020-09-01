package com.belzsoftware.futspect.ui.fixtures.info

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.fragment.navArgs
import androidx.navigation.ui.NavigationUI
import com.belzsoftware.futspect.R
import com.belzsoftware.futspect.databinding.FragmentFixtureInfoBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.fragment_fixture_info.*

@AndroidEntryPoint
class FixtureInfoFragment : Fragment(R.layout.fragment_fixture_info) {

    private lateinit var binding: FragmentFixtureInfoBinding

    private val fixtureInfoViewModel: FixtureInfoViewModel by viewModels()
    private val args: FixtureInfoFragmentArgs by navArgs()


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        fixtureInfoViewModel.setFixtureResponse(args.fixture)

        binding = FragmentFixtureInfoBinding.inflate(inflater, container, false).apply {
            viewModel = fixtureInfoViewModel
            lifecycleOwner = viewLifecycleOwner
        }

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        setUpToolbar()
        super.onViewCreated(view, savedInstanceState)
    }

    private fun setUpToolbar() {
        val navHostFragment = NavHostFragment.findNavController(this)
        NavigationUI.setupWithNavController(toolbarLayout_fixtureInfo, navHostFragment)
        toolbarLayout_fixtureInfo.title = resources.getString(R.string.fixtureInfo_title)
    }
}