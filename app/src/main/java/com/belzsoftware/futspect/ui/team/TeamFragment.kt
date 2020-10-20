package com.belzsoftware.futspect.ui.team

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import com.belzsoftware.futspect.R
import com.belzsoftware.futspect.util.extensions.setUpToolbar

class TeamFragment : Fragment(R.layout.fragment_team) {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        this.setUpToolbar(R.string.team_title)
        super.onViewCreated(view, savedInstanceState)
    }
}