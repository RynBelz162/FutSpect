package com.belzsoftware.futspect.ui.team

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.navArgs
import com.belzsoftware.futspect.R
import com.belzsoftware.futspect.databinding.FragmentTeamBinding
import com.belzsoftware.futspect.model.shared.Result
import com.belzsoftware.futspect.model.team.TeamStatisticResponse
import com.belzsoftware.futspect.ui.shared.setImage
import com.belzsoftware.futspect.util.extensions.createLongSnackbar
import com.belzsoftware.futspect.util.extensions.hideView
import com.belzsoftware.futspect.util.extensions.setUpToolbar
import com.belzsoftware.futspect.util.extensions.showView
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class TeamFragment : Fragment() {

    private var _binding: FragmentTeamBinding? = null
    private val binding get() = _binding!!

    private val teamViewModel: TeamViewModel by viewModels()
    private val args: TeamFragmentArgs by navArgs()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentTeamBinding.inflate(inflater)

        teamViewModel.setParameters(leagueId = args.leagueId, teamId = args.teamId)

        teamViewModel.teamStatsPromise.observe(viewLifecycleOwner, { result ->
            when (result) {
                is Result.Loading -> binding.progressbarTeam.showView()
                is Result.Error -> {
                    binding.progressbarTeam.hideView()
                    activity?.createLongSnackbar(result.message)
                }
                is Result.Success -> {
                    setViewItems(result.data.response)
                    binding.progressbarTeam.hideView()
                }
            }
        })

        return binding.root
    }

    private fun setViewItems(data: TeamStatisticResponse) {
        setImage(binding.imageViewTeamLogo, data.team.logo)
        binding.textViewTeamName.text = data.team.name
        binding.textViewTeamCountry.text = data.league.country
        setImage(binding.imageViewTeamFlag, data.league.flag)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        this.setUpToolbar(binding.toolbarLayoutFixtureInfo, R.string.team_title)
        super.onViewCreated(view, savedInstanceState)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}