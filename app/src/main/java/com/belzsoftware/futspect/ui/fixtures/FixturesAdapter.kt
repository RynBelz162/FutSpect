package com.belzsoftware.futspect.ui.fixtures

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.findNavController
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.belzsoftware.futspect.R
import com.belzsoftware.futspect.databinding.ItemFixtureBinding
import com.belzsoftware.futspect.model.fixture.FixtureResponse
import com.belzsoftware.futspect.ui.shared.setImage

class FixturesAdapter : ListAdapter<FixtureResponse, FixtureViewHolder>(MatchDiff()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FixtureViewHolder {
        return FixtureViewHolder(
            ItemFixtureBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        )
    }

    override fun getItemViewType(position: Int): Int = R.layout.item_fixture

    override fun onBindViewHolder(holder: FixtureViewHolder, position: Int)
        = holder.bind(getItem(position))
}

class FixtureViewHolder(private val binding: ItemFixtureBinding) : RecyclerView.ViewHolder(binding.root) {
    fun bind(item: FixtureResponse) {

        // home team
        binding.textViewFixtureHomeTeam.text = item.teams.home.name
        setImage(binding.imageViewFixtureHomeTeamLogo, item.teams.home.logo)
        scoreVisibility(binding.textViewFixtureHomeScore, item.fixture.status.shortStatus)
        binding.textViewFixtureHomeScore.text = item.goals.home?.toString() ?: "0"

        // away team
        binding.textViewFixtureAwayTeam.text = item.teams.away.name
        setImage(binding.imageViewFixtureAwayTeamLogo, item.teams.away.logo)
        scoreVisibility(binding.textViewFixtureAwayScore, item.fixture.status.shortStatus)
        binding.textViewFixtureAwayScore.text = item.goals.away?.toString() ?: "0"

        scoreVisibility(binding.textViewFixtureDash, item.fixture.status.shortStatus)
        infoVisibility(binding.textViewFixtureVs, item.fixture.status.shortStatus)

        binding.cardViewFixture.setOnClickListener(createOnClickListener(item))
    }

    private fun createOnClickListener(fixtureResponse: FixtureResponse): View.OnClickListener {
        return View.OnClickListener {
            val direction = FixturesFragmentDirections.actionNavigationFixturesToFixtureInfoFragment(fixtureResponse)
            it.findNavController().navigate(direction)
        }
    }
}

class MatchDiff : DiffUtil.ItemCallback<FixtureResponse>() {
    override fun areContentsTheSame(oldItem: FixtureResponse, newItem: FixtureResponse): Boolean
        = oldItem.fixture.id == newItem.fixture.id

    override fun areItemsTheSame(oldItem: FixtureResponse, newItem: FixtureResponse): Boolean
        = oldItem == newItem
}