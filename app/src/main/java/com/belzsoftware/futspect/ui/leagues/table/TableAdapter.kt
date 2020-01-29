package com.belzsoftware.futspect.ui.leagues.table

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.belzsoftware.futspect.BR
import com.belzsoftware.futspect.R
import com.belzsoftware.futspect.model.standings.Standing

class TableAdapter : ListAdapter<Standing, StandingViewHolder>(StandingDiff()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): StandingViewHolder {
        return StandingViewHolder(
            DataBindingUtil.inflate(LayoutInflater.from(parent.context), viewType, parent, false)
        )
    }

    override fun getItemViewType(position: Int): Int = R.layout.item_table

    override fun onBindViewHolder(holder: StandingViewHolder, position: Int) =
        holder.bind(getItem(position))
}

class StandingViewHolder(private val binding: ViewDataBinding) :
    RecyclerView.ViewHolder(binding.root) {
    fun bind(item: Standing) {
        binding.setVariable(BR.standing, item)
        binding.executePendingBindings()
    }
}

class StandingDiff : DiffUtil.ItemCallback<Standing>() {
    override fun areItemsTheSame(oldItem: Standing, newItem: Standing): Boolean =
        oldItem.teamId == newItem.teamId

    override fun areContentsTheSame(oldItem: Standing, newItem: Standing): Boolean =
        oldItem == newItem
}