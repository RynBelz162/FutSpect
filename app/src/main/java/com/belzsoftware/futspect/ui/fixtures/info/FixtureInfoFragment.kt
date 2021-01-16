package com.belzsoftware.futspect.ui.fixtures.info

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import com.belzsoftware.futspect.R
import com.belzsoftware.futspect.databinding.FragmentFixtureInfoBinding
import com.belzsoftware.futspect.model.fixture.FixtureResponse
import com.belzsoftware.futspect.model.shared.Result
import com.belzsoftware.futspect.ui.shared.setImage
import com.belzsoftware.futspect.util.extensions.createLongSnackbar
import com.belzsoftware.futspect.util.extensions.hideView
import com.belzsoftware.futspect.util.extensions.setUpToolbar
import com.belzsoftware.futspect.util.extensions.showView
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class FixtureInfoFragment : Fragment(R.layout.fragment_fixture_info) {

    private var _binding: FragmentFixtureInfoBinding? = null
    private val binding get() = _binding!!

    private lateinit var eventAdapter: FixtureInfoEventAdapter

    private val fixtureInfoViewModel: FixtureInfoViewModel by viewModels()
    private val args: FixtureInfoFragmentArgs by navArgs()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        eventAdapter = FixtureInfoEventAdapter(
            homeTeamId = args.fixture.teams.home.id
        )

        _binding = FragmentFixtureInfoBinding.inflate(inflater)

        fixtureInfoViewModel.setFixtureResponse(args.fixture)
        setViewItems(args.fixture)

        fixtureInfoViewModel.fixtureEvents.observe(viewLifecycleOwner, { result ->
            when (result) {
                is Result.Loading -> binding.progressbarFixtureInfo.showView()
                is Result.Success -> {
                    binding.progressbarFixtureInfo.hideView()
                    eventAdapter.submitList(result.data.response)
                }
                is Result.Error -> {
                    binding.progressbarFixtureInfo.hideView()
                    activity?.createLongSnackbar(result.message)
                }
            }
        })

        return binding.root
    }

    private fun setViewItems(response: FixtureResponse) {
        setFixtureDatetime(binding.textViewFixtureInfoDate, response.fixture.date)
        setVenue(binding.textViewFixtureInfoVenue, response.fixture.venue)

        // home team
        setImage(binding.imageViewFixtureInfoHomeLogo, response.teams.home.logo)
        binding.textViewFixtureInfoHomeName.text = response.teams.home.name
        binding.textViewFixtureInfoHomeScore.text = response.goals.home?.toString() ?: "0"

        // away team
        setImage(binding.imageViewFixtureInfoAwayLogo, response.teams.away.logo)
        binding.textViewFixtureInfoAwayName.text = response.teams.away.name
        binding.textViewFixtureInfoAwayScore.text = response.goals.away?.toString() ?: "0"

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        this.setUpToolbar(binding.toolbarLayoutFixtureInfo, R.string.fixtureInfo_title)
        super.onViewCreated(view, savedInstanceState)

        binding.recyclerViewFixtureInfoEvents.apply {
            layoutManager = LinearLayoutManager(activity)
            adapter = eventAdapter
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
