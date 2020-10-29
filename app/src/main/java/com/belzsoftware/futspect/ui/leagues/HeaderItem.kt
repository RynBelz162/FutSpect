package com.belzsoftware.futspect.ui.leagues

import android.view.View
import com.belzsoftware.futspect.R
import com.belzsoftware.futspect.databinding.ItemLeagueGroupHeaderBinding
import com.xwray.groupie.viewbinding.BindableItem

open class HeaderItem constructor(
    private val title: String
) : BindableItem<ItemLeagueGroupHeaderBinding>() {

    override fun getLayout(): Int = R.layout.item_league_group_header

    override fun initializeViewBinding(view: View): ItemLeagueGroupHeaderBinding =
        ItemLeagueGroupHeaderBinding.bind(view)

    override fun bind(viewBinding: ItemLeagueGroupHeaderBinding, position: Int) {
        viewBinding.apply {
            this.textViewLeagueHeaderTitle.text = title
        }
    }
}