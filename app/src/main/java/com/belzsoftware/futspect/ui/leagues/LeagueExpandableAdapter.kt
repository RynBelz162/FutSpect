package com.belzsoftware.futspect.ui.leagues

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseExpandableListAdapter
import androidx.navigation.findNavController
import com.belzsoftware.futspect.R
import com.belzsoftware.futspect.databinding.ItemLeagueBinding
import com.belzsoftware.futspect.model.league.LeagueResponse
import kotlinx.android.synthetic.main.item_league_group_header.view.*

class LeagueExpandableAdapter(
    private val context: Context,
    private val titles: List<String>,
    private val details: HashMap<String, List<LeagueResponse>>
) : BaseExpandableListAdapter() {

    override fun getChild(listPosition: Int, expandedListPosition: Int): LeagueResponse {
        val titlePosition = titles[listPosition]
        return details[titlePosition]!![expandedListPosition]
    }

    override fun getChildId(listPosition: Int, expandedListPosition: Int): Long {
        return expandedListPosition.toLong()
    }

    override fun getChildView(
        listPosition: Int,
        expandedListPosition: Int,
        isLastChild: Boolean,
        convertView: View?,
        parent: ViewGroup?
    ): View {
        val child = getChild(listPosition, expandedListPosition)

        if (convertView == null) {
            val layoutInflater =
                context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater

            return ItemLeagueBinding.inflate(layoutInflater, parent, false).apply {
                leagueResponse = child
                clickListener = createOnClickListener(child)
            }.root
        }

        return convertView
    }

    override fun getChildrenCount(listPosition: Int): Int {
        return details[titles[listPosition]]!!.count()
    }

    override fun getGroup(listPosition: Int): String {
        return titles[listPosition]
    }

    override fun getGroupCount(): Int {
        return titles.count()
    }

    override fun getGroupId(listPosition: Int): Long {
        return listPosition.toLong()
    }

    override fun getGroupView(
        listPosition: Int,
        isExpanded: Boolean,
        convertView: View?,
        parent: ViewGroup?
    ): View? {
        val group = getGroup(listPosition)

        if (convertView == null) {
            val layoutInflater =
                context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater

            val view = layoutInflater.inflate(R.layout.item_league_group_header, parent, false)
            view.textView_league_header_title.text = group
            return view
        }

        convertView.textView_league_header_title.text = group
        return convertView
    }

    override fun hasStableIds(): Boolean {
        return false
    }

    override fun isChildSelectable(p0: Int, p1: Int): Boolean {
        return true
    }

    private fun createOnClickListener(leagueResponse: LeagueResponse): View.OnClickListener {
        return View.OnClickListener {
            val direction =
                LeaguesFragmentDirections.actionNavigationTablesToTableStandingFragment(
                    leagueResponse
                )
            it.findNavController().navigate(direction)

        }
    }
}