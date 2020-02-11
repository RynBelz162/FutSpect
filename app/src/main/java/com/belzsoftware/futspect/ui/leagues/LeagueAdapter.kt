package com.belzsoftware.futspect.ui.leagues

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.databinding.library.baseAdapters.BR
import androidx.navigation.findNavController
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.belzsoftware.futspect.R
import com.belzsoftware.futspect.model.league.League

class LeagueAdapter : ListAdapter<League, LeagueViewHolder>(LeagueDiff()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): LeagueViewHolder {
        return LeagueViewHolder(
            DataBindingUtil.inflate(LayoutInflater.from(parent.context), viewType, parent, false)
        )
    }

    override fun getItemViewType(position: Int): Int = R.layout.item_league

    override fun onBindViewHolder(holder: LeagueViewHolder, position: Int) =
        holder.bind(getItem(position))
}

class LeagueViewHolder(private val binding: ViewDataBinding) :
    RecyclerView.ViewHolder(binding.root) {
    fun bind(item: League) {
        binding.setVariable(BR.league, item)
        binding.setVariable(BR.clickListener, createOnClickListener(item))
        binding.executePendingBindings()
    }

    private fun createOnClickListener(league: League): View.OnClickListener {
        return View.OnClickListener {
            val direction =
                LeaguesFragmentDirections.actionNavigationTablesToTableStandingFragment(league)
            it.findNavController().navigate(direction)

        }
    }
}

class LeagueDiff : DiffUtil.ItemCallback<League>() {
    override fun areItemsTheSame(oldItem: League, newItem: League): Boolean =
        oldItem.id == newItem.id

    override fun areContentsTheSame(oldItem: League, newItem: League): Boolean = oldItem == newItem
}