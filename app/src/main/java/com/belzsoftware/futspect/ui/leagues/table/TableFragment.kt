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

        recycler_view.apply {
            layoutManager = LinearLayoutManager(activity)
            adapter = tableAdapter
            addItemDecoration(DividerItemDecoration(this.context, DividerItemDecoration.VERTICAL))
        }
    }

    private fun setUpToolbar() {
        val navHostFragment = NavHostFragment.findNavController(this)
        NavigationUI.setupWithNavController(toolbar, navHostFragment)
    }

    private fun setUpViewModel() {
        tableViewModel.setLeagueId(args.league)

        tableViewModel.standings.observe(viewLifecycleOwner, Observer { result ->
            when (result) {
                is Result.loading -> progress_bar.showView()
                is Result.success -> {
                    progress_bar.hideView()

                    val list = result.data.api.standings.flatten()
                    tableAdapter.addHeaderAndSubmitList(list)
                }
                is Result.error -> {
                    progress_bar.hideView()
                    activity?.createLongSnackbar(result.message)
                }
            }
        })
    }
}