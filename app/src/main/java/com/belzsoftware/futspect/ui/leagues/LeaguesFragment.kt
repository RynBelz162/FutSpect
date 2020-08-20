package com.belzsoftware.futspect.ui.leagues

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.belzsoftware.futspect.databinding.FragmentLeaguesBinding
import com.belzsoftware.futspect.model.shared.Result
import com.belzsoftware.futspect.util.createLongSnackbar
import com.belzsoftware.futspect.util.hideView
import com.belzsoftware.futspect.util.showView
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.fragment_leagues.*

@AndroidEntryPoint
class LeaguesFragment : Fragment() {

    private lateinit var binding: FragmentLeaguesBinding

    private val leaguesViewModel: LeaguesViewModel by viewModels()
    private val leagueAdapter = LeagueAdapter()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = FragmentLeaguesBinding.inflate(inflater, container, false).apply {
            viewModel = leaguesViewModel
            lifecycleOwner = viewLifecycleOwner
        }

        leaguesViewModel.leagues.observe(viewLifecycleOwner, { result ->
            when (result) {
                is Result.Loading -> {
                    progressbar_leagues.showView()
                    recyclerView_leagues.hideView()
                }
                is Result.Success -> {
                    progressbar_leagues.hideView()
                    leagueAdapter.submitList(null)
                    leagueAdapter.submitList(result.data.response) {
                        recyclerView_leagues.showView()
                    }
                }
                is Result.Error -> {
                    progressbar_leagues.hideView()
                    recyclerView_leagues.showView()
                    activity?.createLongSnackbar(result.message)
                }
            }
        })

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        recyclerView_leagues.apply {
            layoutManager = LinearLayoutManager(activity)
            adapter = leagueAdapter
        }

        fab_leagues_filter.setOnClickListener {
            val direction =
                LeaguesFragmentDirections.actionNavigationLeaguesToLeagueFilterBottomSheetModalFragment()
            it.findNavController().navigate(direction)
        }

    }
}