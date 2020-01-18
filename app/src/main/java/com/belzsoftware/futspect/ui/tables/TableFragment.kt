package com.belzsoftware.futspect.ui.tables

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.belzsoftware.futspect.databinding.FragmentTableBinding
import com.belzsoftware.futspect.util.viewModelProvider
import dagger.android.support.DaggerFragment
import kotlinx.android.synthetic.main.fragment_table.*
import javax.inject.Inject

class TableFragment : DaggerFragment() {

    @Inject lateinit var viewModelFactory: ViewModelProvider.Factory
    private lateinit var tableViewModel: TableViewModel
    private lateinit var binding: FragmentTableBinding
    private val leagueAdapter: LeagueAdapter = LeagueAdapter()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        tableViewModel = viewModelProvider(viewModelFactory)

        binding = FragmentTableBinding.inflate(inflater, container, false).apply {
            viewModel = tableViewModel
            lifecycleOwner = viewLifecycleOwner
        }

        tableViewModel.leagues.observe(this, Observer {
            leagueAdapter.submitList(it)
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