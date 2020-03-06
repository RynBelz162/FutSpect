package com.belzsoftware.futspect.ui.leagues.table

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.fragment.navArgs
import androidx.navigation.ui.NavigationUI
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.belzsoftware.futspect.databinding.FragmentTableBinding
import com.belzsoftware.futspect.model.shared.Result
import com.belzsoftware.futspect.model.standings.DataItem
import com.belzsoftware.futspect.ui.shared.HeaderItemDecoration
import com.belzsoftware.futspect.util.createLongSnackbar
import com.belzsoftware.futspect.util.hideView
import com.belzsoftware.futspect.util.showView
import com.belzsoftware.futspect.util.viewModelProvider
import dagger.android.support.DaggerFragment
import kotlinx.android.synthetic.main.fragment_table.*
import javax.inject.Inject

class TableFragment : DaggerFragment() {

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory
    private lateinit var tableViewModel: TableViewModel
    private lateinit var binding: FragmentTableBinding
    private val tableAdapter = TableAdapter()
    private val args: TableFragmentArgs by navArgs()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        tableViewModel = viewModelProvider(viewModelFactory)

        binding = FragmentTableBinding.inflate(inflater, container, false).apply {
            viewModel = tableViewModel
            lifecycleOwner = viewLifecycleOwner
        }

        setUpViewModel()

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setUpToolbar()

        recyclerView_table.apply {
            layoutManager = LinearLayoutManager(activity)
            adapter = tableAdapter
            addItemDecoration(HeaderItemDecoration(this, false, isHeader()))
            addItemDecoration(DividerItemDecoration(this.context, DividerItemDecoration.VERTICAL))
        }
    }

    private fun setUpToolbar() {
        val navHostFragment = NavHostFragment.findNavController(this)
        NavigationUI.setupWithNavController(toolbar_table, navHostFragment)
    }

    private fun setUpViewModel() {
        tableViewModel.setLeagueId(args.leagueResponse.league)

        tableViewModel.standings.observe(viewLifecycleOwner, Observer { result ->
            when (result) {
                is Result.loading -> progressbar_table.showView()
                is Result.success -> {
                    progressbar_table.hideView()

                    val list = result.data.response.firstOrNull()?.league?.standings?.flatten()
                    tableAdapter.addHeaderAndSubmitList(list)
                }
                is Result.error -> {
                    progressbar_table.hideView()
                    activity?.createLongSnackbar(result.message)
                }
            }
        })
    }

    private fun isHeader(): (itemPosition: Int) -> Boolean {
        return {
            tableAdapter.currentList[it].id == DataItem.Header.id
        }
    }
}