package io.afdon.list.ui

import android.os.Bundle
import android.view.MenuItem
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import io.afdon.core.extension.toast
import io.afdon.core.viewmodel.SavedStateViewModelFactory
import io.afdon.list.R
import io.afdon.list.databinding.FragmentListBinding
import javax.inject.Inject

class TvShowsFragment @Inject constructor(
    private val factory: SavedStateViewModelFactory
) : Fragment(R.layout.fragment_list) {

    private val viewModel by viewModels<TvShowsViewModel> {
        factory.create(this@TvShowsFragment, arguments)
    }
    private var binding: FragmentListBinding? = null
    private lateinit var adapter: TvShowsAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initToolbar()
        setAdapter()
        setBinding(view)
        observeViewModel()
        viewModel.getTvShows()
    }

    private fun initToolbar() {
        (requireActivity() as AppCompatActivity).supportActionBar?.apply {
            title = "Saved Tv Shows"
            setDisplayHomeAsUpEnabled(true)
        }
    }

    private fun setAdapter() {
        adapter = TvShowsAdapter()
    }

    private fun setBinding(v: View) {
        binding = FragmentListBinding.bind(v).apply {
            lifecycleOwner = viewLifecycleOwner
            vm = viewModel
            rvTvShows.adapter = adapter
        }
    }

    private fun observeViewModel() {
        viewModel.tvShows.observe(viewLifecycleOwner) {
            adapter.submitList(it)
        }
        viewModel.error.observe(viewLifecycleOwner) {
            it.getContentIfNotHandled()?.let { error ->
                toast(error)
            }
        }
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            android.R.id.home -> {
                requireActivity().onBackPressed()
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        binding = null
    }
}