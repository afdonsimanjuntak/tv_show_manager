package io.afdon.home.ui

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import io.afdon.core.viewmodel.SavedStateViewModelFactory
import io.afdon.home.R
import io.afdon.home.databinding.FragmentHomeBinding
import javax.inject.Inject

class HomeFragment @Inject constructor(
    private val factory: SavedStateViewModelFactory
) : Fragment(R.layout.fragment_home) {

    private val viewModel by viewModels<HomeViewModel> {
        factory.create(this@HomeFragment)
    }
    private var binding: FragmentHomeBinding? = null

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initToolbar()
        setBinding(view)
        observeViewModel()
    }

    private fun initToolbar() {
        (requireActivity() as AppCompatActivity).supportActionBar?.apply {
            title = "TV SHOW MANAGER"
            setDisplayHomeAsUpEnabled(false)
        }
    }

    private fun setBinding(v: View) {
        binding = FragmentHomeBinding.bind(v).apply {
            lifecycleOwner = viewLifecycleOwner
            vm = viewModel
        }
    }

    private fun observeViewModel() {

    }
}