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
import com.belzsoftware.futspect.model.league.LeagueResponse

class LeagueAdapter : ListAdapter<LeagueResponse, LeagueViewHolder>(LeagueDiff()) {

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
    fun bind(item: LeagueResponse) {
        binding.setVariable(BR.leagueResponse, item)
        binding.setVariable(BR.clickListener, createOnClickListener(item))
        binding.executePendingBindings()
    }

    private fun createOnClickListener(leagueResponse: LeagueResponse): View.OnClickListener {
        return View.OnClickListener {
            val direction =
                LeaguesFragmentDirections.actionNavigationTablesToTableStandingFragment(leagueResponse)
            it.findNavController().navigate(direction)

        }
    }
}

class LeagueDiff : DiffUtil.ItemCallback<LeagueResponse>() {
    override fun areItemsTheSame(oldItem: LeagueResponse, newItem: LeagueResponse): Boolean =
        oldItem.league.id == newItem.league.id

    override fun areContentsTheSame(oldItem: LeagueResponse, newItem: LeagueResponse): Boolean = oldItem == newItem
}