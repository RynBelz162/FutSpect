package com.belzsoftware.futspect.ui.leagues.table

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.belzsoftware.futspect.databinding.FragmentTableBinding
import com.belzsoftware.futspect.model.shared.Result
import com.belzsoftware.futspect.util.viewModelProvider
import com.google.android.material.snackbar.Snackbar
import dagger.android.support.DaggerFragment
import kotlinx.android.synthetic.main.fragment_table.*
import javax.inject.Inject

class TableFragment : DaggerFragment() {

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory
    private lateinit var tableViewModel: TableViewModel
    private lateinit var binding: FragmentTableBinding
    private val tableAdapter = TableAdapter()

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

        tableViewModel.standings.observe(viewLifecycleOwner, Observer { result ->
            when (result) {
                is Result.loading -> progress_bar.visibility = View.VISIBLE
                is Result.success -> {
                    progress_bar.visibility = View.GONE

                    val list = result.data.api.standings.flatten()
                    tableAdapter.submitList(list)
                }
                is Result.error -> {
                    progress_bar.visibility = View.GONE
                    Snackbar.make(binding.root, result.message, Snackbar.LENGTH_LONG)
                }
            }
        })

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        recycler_view.apply {
            layoutManager = LinearLayoutManager(activity)
            adapter = tableAdapter
            addItemDecoration(DividerItemDecoration(this.context, DividerItemDecoration.VERTICAL))
        }
    }
}