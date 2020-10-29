package com.belzsoftware.futspect.ui.leagues

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.belzsoftware.futspect.databinding.FragmentLeaguesBinding
import com.belzsoftware.futspect.model.league.LeagueResponse
import com.belzsoftware.futspect.model.shared.Result
import com.belzsoftware.futspect.util.extensions.createLongSnackbar
import com.belzsoftware.futspect.util.extensions.hideView
import com.belzsoftware.futspect.util.extensions.showView
import com.xwray.groupie.ExpandableGroup
import com.xwray.groupie.GroupAdapter
import com.xwray.groupie.GroupieViewHolder
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class LeaguesFragment : Fragment() {

    private var _binding: FragmentLeaguesBinding? = null

    private val binding get() = _binding!!
    private val leaguesViewModel: LeaguesViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = FragmentLeaguesBinding.inflate(inflater)

        leaguesViewModel.leagues.observe(viewLifecycleOwner, { result ->
            when (result) {
                is Result.Loading -> {
                    binding.progressbarLeagues.showView()
                }
                is Result.Success -> {
                    binding.progressbarLeagues.hideView()
                    bindGroupieAdapter(result.data)
                    binding.recyclerViewLeague.showView()
                }
                is Result.Error -> {
                    binding.progressbarLeagues.hideView()
                    binding.recyclerViewLeague.showView()

                    activity?.createLongSnackbar(result.message)
                }
            }
        })

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.fabLeaguesFilter.setOnClickListener {
            val direction =
                LeaguesFragmentDirections.actionNavigationLeaguesToLeagueFilterBottomSheetModalFragment()
            it.findNavController().navigate(direction)
        }
    }

    private fun bindGroupieAdapter(groups: HashMap<String, List<LeagueResponse>>) {
        val adapter = GroupAdapter<GroupieViewHolder>()

        groups.forEach { group ->
            val headerItem = ExpandableHeaderItem(group.key)
            adapter.add(ExpandableGroup(headerItem).apply {
                group.value.forEach {
                    add(LeagueItem(it))
                }
            })
        }

        binding.recyclerViewLeague.apply {
            this.layoutManager = LinearLayoutManager(activity)
            this.adapter = adapter
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}