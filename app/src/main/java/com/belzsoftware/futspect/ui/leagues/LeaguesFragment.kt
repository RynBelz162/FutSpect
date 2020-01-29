package com.belzsoftware.futspect.ui.leagues

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.belzsoftware.futspect.databinding.FragmentLeaguesBinding
import com.belzsoftware.futspect.model.shared.Result
import com.belzsoftware.futspect.util.viewModelProvider
import com.google.android.material.snackbar.Snackbar
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
                is Result.loading-> progress_bar.visibility = View.VISIBLE
                is Result.success -> {
                    progress_bar.visibility = View.GONE
                    leagueAdapter.submitList(result.data.api.leagues)
                }
                is Result.error -> {
                    progress_bar.visibility = View.GONE
                    Snackbar.make(binding.root, result.message, Snackbar.LENGTH_LONG).show()
                }
            }
        })

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        recycler_view.apply {
            layoutManager = LinearLayoutManager(activity)
            adapter = leagueAdapter
        }
    }
}