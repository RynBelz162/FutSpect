package com.belzsoftware.futspect.ui.tables

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import com.belzsoftware.futspect.databinding.FragmentTableBinding

class TableFragment : Fragment() {

    private lateinit var tableViewModel: TableViewModel

    companion object {
        fun newInstance(): TableFragment {
            return TableFragment()
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        tableViewModel = ViewModelProviders.of(this).get(TableViewModel::class.java)
        val binding = FragmentTableBinding.inflate(inflater, container, false).apply {
            viewModel = tableViewModel
            lifecycleOwner = this@TableFragment
        }

        return binding.root
    }
}