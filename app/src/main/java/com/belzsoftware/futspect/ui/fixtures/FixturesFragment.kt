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

@AndroidEntryPoint
class FixturesFragment : Fragment() {

    private var _binding: FragmentFixturesBinding? = null
    private val binding get() = _binding!!

    private val fixturesViewModel: FixturesViewModel by viewModels()
    private val fixturesAdapter = FixturesAdapter()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentFixturesBinding.inflate(inflater)

        fixturesViewModel.fixtures.observe(viewLifecycleOwner, { result ->
            when (result) {
                is Result.Loading -> binding.progressbarFixtures.showView()
                is Result.Success -> {
                    binding.progressbarFixtures.hideView()
                    fixturesAdapter.submitList(result.data.response)
                }
                is Result.Error -> {
                    binding.progressbarFixtures.hideView()
                    activity?.createLongSnackbar(result.message)
                }
            }
        })

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.recyclerViewFixtures.apply {
            layoutManager = LinearLayoutManager(activity)
            adapter = fixturesAdapter
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
