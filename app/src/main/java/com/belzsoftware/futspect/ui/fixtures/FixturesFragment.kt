package com.belzsoftware.futspect.ui.fixtures

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.belzsoftware.futspect.databinding.FragmentFixturesBinding
import com.belzsoftware.futspect.model.shared.Result
import com.belzsoftware.futspect.util.extensions.createLongSnackbar
import com.belzsoftware.futspect.util.extensions.hideView
import com.belzsoftware.futspect.util.extensions.showView
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.fragment_fixtures.*

@AndroidEntryPoint
class FixturesFragment : Fragment() {

    private lateinit var binding: FragmentFixturesBinding

    private val fixturesViewModel: FixturesViewModel by viewModels()
    private val fixturesAdapter = FixturesAdapter()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentFixturesBinding.inflate(inflater, container, false).apply {
            viewModel = fixturesViewModel
            lifecycleOwner = viewLifecycleOwner
        }

        fixturesViewModel.fixtures.observe(viewLifecycleOwner, { result ->
            when (result) {
                is Result.Loading -> progressbar_fixtures.showView()
                is Result.Success -> {
                    progressbar_fixtures.hideView()
                    fixturesAdapter.submitList(result.data.response)
                }
                is Result.Error -> {
                    progressbar_fixtures.hideView()
                    activity?.createLongSnackbar(result.message)
                }
            }
        })

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        recyclerView_fixtures.apply {
            layoutManager = LinearLayoutManager(activity)
            adapter = fixturesAdapter
        }
    }
}
