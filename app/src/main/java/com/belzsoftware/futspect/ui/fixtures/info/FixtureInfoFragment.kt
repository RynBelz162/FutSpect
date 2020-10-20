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
import com.belzsoftware.futspect.model.shared.Result
import com.belzsoftware.futspect.util.extensions.createLongSnackbar
import com.belzsoftware.futspect.util.extensions.hideView
import com.belzsoftware.futspect.util.extensions.setUpToolbar
import com.belzsoftware.futspect.util.extensions.showView
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.fragment_fixture_info.*

@AndroidEntryPoint
class FixtureInfoFragment : Fragment(R.layout.fragment_fixture_info) {

    private lateinit var binding: FragmentFixtureInfoBinding
    private lateinit var eventAdapter: FixtureInfoEventAdapter

    private val fixtureInfoViewModel: FixtureInfoViewModel by viewModels()
    private val args: FixtureInfoFragmentArgs by navArgs()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        eventAdapter = FixtureInfoEventAdapter(
            homeTeamId = args.fixture.teams.home.id,
            awayTeamId = args.fixture.teams.away.id
        )

        fixtureInfoViewModel.setFixtureResponse(args.fixture)

        binding = FragmentFixtureInfoBinding.inflate(inflater, container, false).apply {
            viewModel = fixtureInfoViewModel
            lifecycleOwner = viewLifecycleOwner
        }

        fixtureInfoViewModel.fixtureEvents.observe(viewLifecycleOwner, { result ->
            when (result) {
                is Result.Loading -> progressbar_fixtureInfo.showView()
                is Result.Success -> {
                    progressbar_fixtureInfo.hideView()
                    eventAdapter.submitList(result.data.response)
                }
                is Result.Error -> {
                    progressbar_fixtureInfo.hideView()
                    activity?.createLongSnackbar(result.message)
                }
            }
        })

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        this.setUpToolbar(R.string.fixtureInfo_title)
        super.onViewCreated(view, savedInstanceState)

        recyclerView_fixtureInfo_events.apply {
            layoutManager = LinearLayoutManager(activity)
            adapter = eventAdapter
        }
    }
}
