package com.belzsoftware.futspect.ui.leagues

import android.graphics.drawable.Animatable
import com.belzsoftware.futspect.R
import com.belzsoftware.futspect.databinding.ItemLeagueGroupHeaderBinding
import com.belzsoftware.futspect.model.country.Country
import com.xwray.groupie.ExpandableGroup
import com.xwray.groupie.ExpandableItem

class ExpandableHeaderItem(
    country: Country
) : HeaderItem(country), ExpandableItem {

    private var expandableGroup: ExpandableGroup? = null

    override fun bind(viewBinding: ItemLeagueGroupHeaderBinding, position: Int) {
        super.bind(viewBinding, position)

        bindIcon(viewBinding)
        viewBinding.iconLeagueGroupHeader.setImageResource(if (expandableGroup!!.isExpanded) R.drawable.ic_up_arrow else R.drawable.ic_down_arrow)
        viewBinding.cardViewLeagueGroupHeader.setOnClickListener {
            expandableGroup!!.onToggleExpanded()
            bindIcon(viewBinding)
        }
    }

    private fun bindIcon(viewBinding: ItemLeagueGroupHeaderBinding) {
        viewBinding.iconLeagueGroupHeader.setImageResource(if (expandableGroup!!.isExpanded) R.drawable.up_arrow_animated else R.drawable.down_arrow_animated)
        val drawable = viewBinding.iconLeagueGroupHeader.drawable as Animatable
        drawable.start()
    }

    override fun setExpandableGroup(onToggleListener: ExpandableGroup) {
        expandableGroup = onToggleListener
    }
}