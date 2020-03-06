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
import com.belzsoftware.futspect.model.fixture.FixtureResponse

class FixturesAdapter : ListAdapter<FixtureResponse, FixtureViewHolder>(MatchDiff()) {

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
    fun bind(item: FixtureResponse) {
        binding.setVariable(BR.fixtureResponse, item)
        binding.executePendingBindings()
    }
}

class MatchDiff : DiffUtil.ItemCallback<FixtureResponse>() {
    override fun areContentsTheSame(oldItem: FixtureResponse, newItem: FixtureResponse): Boolean
        = oldItem.fixture.id == newItem.fixture.id

    override fun areItemsTheSame(oldItem: FixtureResponse, newItem: FixtureResponse): Boolean
        = oldItem == newItem
}