package com.belzsoftware.futspect.ui.leagues

import android.view.View
import com.belzsoftware.futspect.R
import com.belzsoftware.futspect.databinding.ItemLeagueGroupHeaderBinding
import com.belzsoftware.futspect.model.country.Country
import com.belzsoftware.futspect.ui.shared.setCircleImage
import com.xwray.groupie.viewbinding.BindableItem

open class HeaderItem constructor(
    private val country: Country
) : BindableItem<ItemLeagueGroupHeaderBinding>() {

    override fun getLayout(): Int = R.layout.item_league_group_header

    override fun initializeViewBinding(view: View): ItemLeagueGroupHeaderBinding =
        ItemLeagueGroupHeaderBinding.bind(view)

    override fun bind(viewBinding: ItemLeagueGroupHeaderBinding, position: Int) {
        viewBinding.apply {
            this.textViewLeagueHeaderTitle.text = country.name
            setCircleImage(this.imageViewLeagueHeaderFlag, country.flag)
        }
    }
}