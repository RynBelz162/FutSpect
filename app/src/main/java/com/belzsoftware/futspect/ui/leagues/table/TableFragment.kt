package com.belzsoftware.futspect.ui.leagues.table

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.fragment.navArgs
import androidx.navigation.ui.NavigationUI
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.belzsoftware.futspect.databinding.FragmentTableBinding
import com.belzsoftware.futspect.model.shared.Result
import com.belzsoftware.futspect.model.standings.DataItem
import com.belzsoftware.futspect.ui.shared.HeaderItemDecoration
import com.belzsoftware.futspect.util.extensions.createLongSnackbar
import com.belzsoftware.futspect.util.extensions.hideView
import com.belzsoftware.futspect.util.extensions.showView
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class TableFragment : Fragment() {

    private var _binding: FragmentTableBinding? = null
    private val binding get() = _binding!!

    private val tableViewModel: TableViewModel by viewModels()
    private val args: TableFragmentArgs by navArgs()
    private val tableAdapter: TableAdapter by lazy { TableAdapter(args.leagueResponse.league.id) }


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = FragmentTableBinding.inflate(inflater)

        setupView()

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setUpToolbar()

        binding.recyclerViewTable.apply {
            layoutManager = LinearLayoutManager(activity)
            adapter = tableAdapter
            addItemDecoration(HeaderItemDecoration(this, false, isHeader()))
            addItemDecoration(DividerItemDecoration(this.context, DividerItemDecoration.VERTICAL))
        }
    }

    private fun setUpToolbar() {
        val navHostFragment = NavHostFragment.findNavController(this)
        NavigationUI.setupWithNavController(binding.toolbarTable, navHostFragment)
    }

    private fun setupView() {
        tableViewModel.setLeague(args.leagueResponse.league)

        binding.toolbarLayoutTable.title = args.leagueResponse.league.name
        tableViewModel.standings.observe(viewLifecycleOwner, { result ->
            when (result) {
                is Result.Loading -> binding.progressbarTable.showView()
                is Result.Success -> {
                    binding.progressbarTable.hideView()

                    val list = result.data.response.firstOrNull()?.league?.standings?.flatten()
                    tableAdapter.addHeaderAndSubmitList(list)
                }
                is Result.Error -> {
                    binding.progressbarTable.hideView()
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

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}