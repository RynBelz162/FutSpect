package com.belzsoftware.futspect.ui.leagues

import com.belzsoftware.futspect.R
import com.belzsoftware.futspect.databinding.ItemLeagueGroupHeaderBinding
import com.xwray.groupie.ExpandableGroup
import com.xwray.groupie.ExpandableItem

class ExpandableHeaderItem(
    title: String
) : HeaderItem(title), ExpandableItem {

    private var expandableGroup: ExpandableGroup? = null

    override fun bind(viewBinding: ItemLeagueGroupHeaderBinding, position: Int) {
        super.bind(viewBinding, position)

        bindIcon(viewBinding)
        viewBinding.cardViewLeagueGroupHeader.setOnClickListener {
            expandableGroup!!.onToggleExpanded()
            bindIcon(viewBinding)
        }
    }

    private fun bindIcon(viewBinding: ItemLeagueGroupHeaderBinding) {
        viewBinding.iconLeagueGroupHeader.setImageResource(if (expandableGroup!!.isExpanded) R.drawable.ic_up_arrow else R.drawable.ic_down_arrow)
    }

    override fun setExpandableGroup(onToggleListener: ExpandableGroup) {
        expandableGroup = onToggleListener
    }
}