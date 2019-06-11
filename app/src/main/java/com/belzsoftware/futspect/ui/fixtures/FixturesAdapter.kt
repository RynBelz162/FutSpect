package com.belzsoftware.futspect.ui.fixtures

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.belzsoftware.futspect.BR
import com.belzsoftware.futspect.R
import com.belzsoftware.futspect.model.fixture.Fixture

class FixturesAdapter : ListAdapter<Fixture, FixtureViewHolder>(MatchDiff()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FixtureViewHolder {
        return FixtureViewHolder(
            DataBindingUtil.inflate(LayoutInflater.from(parent.context), viewType, parent, false)
        )
    }

    override fun getItemViewType(position: Int): Int = R.layout.item_fixture

    override fun onBindViewHolder(holder: FixtureViewHolder, position: Int)
        = holder.bind(getItem(position))
}

class FixtureViewHolder(private val binding: ViewDataBinding) : RecyclerView.ViewHolder(binding.root) {
    fun bind(item: Fixture) {
        binding.setVariable(BR.fixture, item)
        binding.executePendingBindings()
    }
}

class MatchDiff : DiffUtil.ItemCallback<Fixture>() {
    override fun areContentsTheSame(oldItem: Fixture, newItem: Fixture): Boolean
        = oldItem == newItem

    override fun areItemsTheSame(oldItem: Fixture, newItem: Fixture): Boolean
        = oldItem.id == newItem.id
}