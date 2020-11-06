package com.belzsoftware.futspect.ui.shared

import android.graphics.Rect
import android.view.View
import androidx.annotation.LayoutRes
import androidx.recyclerview.widget.RecyclerView

class ExpandableItemDecorator(@LayoutRes val childLayout: Int) : RecyclerView.ItemDecoration() {

    companion object {
        private const val DIVIDER_WIDTH_CONST: Int = 3
    }

    override fun getItemOffsets(
        outRect: Rect,
        view: View,
        parent: RecyclerView,
        state: RecyclerView.State
    ) {
        if (!view.isChild(parent)) {
            return
        }

        val viewHolder = parent.getChildViewHolder(view)
        when (viewHolder.adapterPosition) {
            2 -> {
                outRect.bottom = DIVIDER_WIDTH_CONST
                outRect.top = DIVIDER_WIDTH_CONST
            }
            else -> {
                outRect.bottom = DIVIDER_WIDTH_CONST
            }
        }
    }

    private fun View.isChild(parent: RecyclerView): Boolean {
        val viewType: Int = parent.layoutManager!!.getItemViewType(this)
        return viewType == childLayout
    }
}