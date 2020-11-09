package com.belzsoftware.futspect.ui.fixtures.info

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.belzsoftware.futspect.R
import com.belzsoftware.futspect.databinding.ItemFixtureEventBinding
import com.belzsoftware.futspect.model.fixture.Event

class FixtureInfoEventAdapter(
    val homeTeamId: Int
) : ListAdapter<Event, EventViewHolder>(EventDiff()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): EventViewHolder {
        return EventViewHolder(
            ItemFixtureEventBinding.inflate(LayoutInflater.from(parent.context), parent, false),
            homeTeamId
        )
    }

    override fun getItemViewType(position: Int): Int = R.layout.item_fixture_event

    override fun onBindViewHolder(holder: EventViewHolder, position: Int) =
        holder.bind(getItem(position))
}

class EventViewHolder(
    private val binding: ItemFixtureEventBinding,
    private val homeTeamId: Int,
) : RecyclerView.ViewHolder(binding.root) {

    fun bind(item: Event) {
        setTeamGravity(binding.linearLayoutFixtureEvent, homeTeamId, item.team.id)
        binding.textViewFixtureEventTime.text = item.time.elapsed.toString()
        binding.textViewFixtureEventPlayer.text = item.player.name
        setEventIcon(binding.imageViewFixtureEventIcon, item.detail)
    }
}

class EventDiff : DiffUtil.ItemCallback<Event>() {
    override fun areContentsTheSame(oldItem: Event, newItem: Event): Boolean =
        oldItem.time.elapsed == newItem.time.elapsed && oldItem.type == newItem.type && oldItem.player.name == newItem.player.name

    override fun areItemsTheSame(oldItem: Event, newItem: Event): Boolean = oldItem == newItem
}