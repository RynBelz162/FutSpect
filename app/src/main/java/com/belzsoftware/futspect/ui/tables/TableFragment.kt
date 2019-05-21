package com.belzsoftware.futspect.ui.tables

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.belzsoftware.futspect.databinding.FragmentTableBinding
import kotlinx.android.synthetic.main.fragment_table.*

class TableFragment : Fragment() {

    private val tableViewModel: TableViewModel by lazy {
         ViewModelProviders.of(this).get(TableViewModel::class.java)
    }

    private val leagueAdapter: LeagueAdapter = LeagueAdapter()
    private lateinit var binding: FragmentTableBinding

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = FragmentTableBinding.inflate(inflater, container, false).apply {
            viewModel = tableViewModel
            lifecycleOwner = this@TableFragment
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