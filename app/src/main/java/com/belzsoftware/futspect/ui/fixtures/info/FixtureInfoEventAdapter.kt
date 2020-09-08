package com.belzsoftware.futspect.ui.fixtures.info

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.belzsoftware.futspect.BR
import com.belzsoftware.futspect.R
import com.belzsoftware.futspect.model.fixture.Event

class FixtureInfoEventAdapter(
    val homeTeamId: Int,
    val awayTeamId: Int
) : ListAdapter<Event, EventViewHolder>(EventDiff()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): EventViewHolder {
        return EventViewHolder(
            DataBindingUtil.inflate(LayoutInflater.from(parent.context), viewType, parent, false),
            homeTeamId,
            awayTeamId
        )
    }

    override fun getItemViewType(position: Int): Int = R.layout.item_fixture_event

    override fun onBindViewHolder(holder: EventViewHolder, position: Int) =
        holder.bind(getItem(position))
}

class EventViewHolder(
    private val binding: ViewDataBinding,
    private val homeTeamId: Int,
    private val awayTeamId: Int
) :

    RecyclerView.ViewHolder(binding.root) {

    fun bind(item: Event) {
        binding.setVariable(BR.event, item)
        binding.setVariable(BR.homeTeamId, homeTeamId)
        binding.setVariable(BR.awayTeamId, awayTeamId)
        binding.executePendingBindings()
    }
}

class EventDiff : DiffUtil.ItemCallback<Event>() {
    override fun areContentsTheSame(oldItem: Event, newItem: Event): Boolean =
        oldItem.time.elapsed == newItem.time.elapsed && oldItem.type == newItem.type && oldItem.player.name == newItem.player.name

    override fun areItemsTheSame(oldItem: Event, newItem: Event): Boolean = oldItem == newItem
}