package com.belzsoftware.futspect.ui.leagues.table

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.findNavController
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.belzsoftware.futspect.BR
import com.belzsoftware.futspect.R
import com.belzsoftware.futspect.databinding.ItemTableBinding
import com.belzsoftware.futspect.model.standings.DataItem
import com.belzsoftware.futspect.model.standings.Standing
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

private const val ITEM_VIEW_TYPE_HEADER = 0
private const val ITEM_VIEW_TYPE_ITEM = 1

class TableAdapter(private val leagueId: Int) :
    ListAdapter<DataItem, RecyclerView.ViewHolder>(StandingDiff()) {

    private val adapterScope = CoroutineScope(Dispatchers.Default)

    fun addHeaderAndSubmitList(list: List<Standing>?) {
        adapterScope.launch {
            val items = when (list) {
                null -> listOf(DataItem.Header)
                else -> listOf(DataItem.Header) + list.map { DataItem.StandingItem(it) }
            }
            withContext(Dispatchers.Main) {
                submitList(items)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return when (viewType) {
            ITEM_VIEW_TYPE_HEADER -> HeaderViewHolder.from(parent)
            ITEM_VIEW_TYPE_ITEM -> StandingViewHolder.from(parent, leagueId)
            else -> throw ClassCastException("Unknown viewType $viewType")
        }
    }

    override fun getItemViewType(position: Int): Int {
        return when (getItem(position)) {
            is DataItem.Header -> ITEM_VIEW_TYPE_HEADER
            is DataItem.StandingItem -> ITEM_VIEW_TYPE_ITEM
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when (holder) {
            is StandingViewHolder -> {
                val standingItem = getItem(position) as DataItem.StandingItem
                holder.bind(standingItem.standing)
            }
        }
    }
}

class StandingViewHolder(
    private val binding: ItemTableBinding,
    private val leagueId: Int
) :
    RecyclerView.ViewHolder(binding.root) {
    fun bind(item: Standing) {
        binding.setVariable(BR.standing, item)
        binding.setVariable(BR.clickListener, createOnClickListener(item))
        binding.executePendingBindings()
    }

    companion object {
        fun from(parent: ViewGroup, leagueId: Int): StandingViewHolder {
            val layoutInflater = LayoutInflater.from(parent.context)
            val binding = ItemTableBinding.inflate(layoutInflater, parent, false)
            return StandingViewHolder(binding, leagueId)
        }
    }

    private fun createOnClickListener(standing: Standing): View.OnClickListener {
        return View.OnClickListener {
            val direction = TableFragmentDirections.actionTableStandingFragmentToTeamFragment(
                standing.team.id,
                leagueId
            )
            it.findNavController().navigate(direction)
        }
    }
}

class HeaderViewHolder(view: View) :
    RecyclerView.ViewHolder(view) {

    companion object {
        fun from(parent: ViewGroup): HeaderViewHolder {
            val layoutInflater = LayoutInflater.from(parent.context)
            val view = layoutInflater.inflate(R.layout.header_table, parent, false)
            return HeaderViewHolder(view)
        }
    }
}

class StandingDiff : DiffUtil.ItemCallback<DataItem>() {
    override fun areItemsTheSame(oldItem: DataItem, newItem: DataItem): Boolean =
        oldItem.id == newItem.id

    @SuppressLint("DiffUtilEquals")
    override fun areContentsTheSame(oldItem: DataItem, newItem: DataItem): Boolean =
        oldItem == newItem
}