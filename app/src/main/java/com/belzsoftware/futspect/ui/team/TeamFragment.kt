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
import com.belzsoftware.futspect.util.extensions.createLongSnackbar
import com.belzsoftware.futspect.util.extensions.hideView
import com.belzsoftware.futspect.util.extensions.setUpToolbar
import com.belzsoftware.futspect.util.extensions.showView
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.fragment_team.*

@AndroidEntryPoint
class TeamFragment : Fragment() {

    private lateinit var binding: FragmentTeamBinding

    private val teamViewModel: TeamViewModel by viewModels()
    private val args: TeamFragmentArgs by navArgs()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        teamViewModel.setParameters(leagueId = args.leagueId, teamId = args.teamId)

        binding = FragmentTeamBinding.inflate(inflater, container, false).apply {
            viewModel = teamViewModel
            lifecycleOwner = viewLifecycleOwner
        }

        teamViewModel.teamStatsPromise.observe(viewLifecycleOwner, { result ->
            when(result) {
                is Result.Loading -> progressbar_team.showView()
                is Result.Error -> {
                    progressbar_team.hideView()
                    activity?.createLongSnackbar(result.message)
                }
                is Result.Success -> progressbar_team.hideView()
            }
        })

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        this.setUpToolbar(R.string.team_title)
        super.onViewCreated(view, savedInstanceState)
    }
}