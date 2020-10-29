package com.belzsoftware.futspect.ui.leagues

import android.view.View
import androidx.navigation.findNavController
import com.belzsoftware.futspect.R
import com.belzsoftware.futspect.databinding.ItemLeagueBinding
import com.belzsoftware.futspect.model.league.LeagueResponse
import com.xwray.groupie.viewbinding.BindableItem

class LeagueItem constructor(
    private val leagueResponse: LeagueResponse
) : BindableItem<ItemLeagueBinding>() {

    override fun getLayout() = R.layout.item_league

    override fun initializeViewBinding(view: View): ItemLeagueBinding = ItemLeagueBinding.bind(view)

    override fun bind(viewBinding: ItemLeagueBinding, position: Int) {
        viewBinding.apply {
            setLeagueLogo(this.imageViewLeagueLogo, leagueResponse)
            this.textViewLeagueName.text = leagueResponse.league.name
            this.textViewLeagueCountry.text = leagueResponse.country.name

            this.cardViewLeagueItem.setOnClickListener {
                val navigation =
                    LeaguesFragmentDirections.actionNavigationTablesToTableStandingFragment(
                        leagueResponse
                    )
                it.findNavController().navigate(navigation)
            }
        }
    }
}