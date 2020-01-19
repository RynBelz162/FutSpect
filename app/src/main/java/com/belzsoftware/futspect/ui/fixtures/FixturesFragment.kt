package com.belzsoftware.futspect.ui.fixtures

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.belzsoftware.futspect.databinding.FragmentFixturesBinding
import com.belzsoftware.futspect.util.viewModelProvider
import dagger.android.support.DaggerFragment
import kotlinx.android.synthetic.main.fragment_table.*
import javax.inject.Inject

class FixturesFragment : DaggerFragment() {

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory
    private lateinit var fixturesViewModel: FixturesViewModel
    private lateinit var binding: FragmentFixturesBinding
    private val fixturesAdapter = FixturesAdapter()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        fixturesViewModel = viewModelProvider(viewModelFactory)

        binding = FragmentFixturesBinding.inflate(inflater, container, false).apply {
            viewModel = fixturesViewModel
            lifecycleOwner = viewLifecycleOwner
        }

        fixturesViewModel.fixtures.observe(this, Observer {
            fixturesAdapter.submitList(it)
        })

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        recycler_view.apply {
            layoutManager = LinearLayoutManager(activity)
            adapter = fixturesAdapter
        }
    }
}
