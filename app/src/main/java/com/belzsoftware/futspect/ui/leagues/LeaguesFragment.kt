package com.belzsoftware.futspect.ui.leagues

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.belzsoftware.futspect.databinding.FragmentLeaguesBinding
import com.belzsoftware.futspect.model.shared.Result
import com.belzsoftware.futspect.util.createLongSnackbar
import com.belzsoftware.futspect.util.hideView
import com.belzsoftware.futspect.util.showView
import com.belzsoftware.futspect.util.viewModelProvider
import dagger.android.support.DaggerFragment
import kotlinx.android.synthetic.main.fragment_leagues.*
import javax.inject.Inject

class LeaguesFragment : DaggerFragment() {

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory
    private lateinit var leaguesViewModel: LeaguesViewModel
    private lateinit var binding: FragmentLeaguesBinding
    private val leagueAdapter = LeagueAdapter()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        leaguesViewModel = viewModelProvider(viewModelFactory)

        binding = FragmentLeaguesBinding.inflate(inflater, container, false).apply {
            viewModel = leaguesViewModel
            lifecycleOwner = viewLifecycleOwner
        }

        leaguesViewModel.leagues.observe(viewLifecycleOwner, Observer { result ->
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