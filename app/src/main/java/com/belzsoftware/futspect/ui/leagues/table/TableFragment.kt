package com.belzsoftware.futspect.ui.leagues.table

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.navArgs
import com.belzsoftware.futspect.databinding.FragmentTableBinding
import com.belzsoftware.futspect.util.viewModelProvider
import dagger.android.support.DaggerFragment
import kotlinx.android.synthetic.main.fragment_table.*
import javax.inject.Inject

class TableFragment : DaggerFragment() {

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory
    private lateinit var tableViewModel: TableViewModel
    private lateinit var binding: FragmentTableBinding

    val args: TableFragmentArgs by navArgs()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        tableViewModel = viewModelProvider(viewModelFactory)

        binding = FragmentTableBinding.inflate(inflater, container, false).apply {
            viewModel = tableViewModel
            lifecycleOwner = viewLifecycleOwner
        }

        return binding.root
    }

    // nav args test
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        league_id_text.text = args.leagueId.toString()
    }
}