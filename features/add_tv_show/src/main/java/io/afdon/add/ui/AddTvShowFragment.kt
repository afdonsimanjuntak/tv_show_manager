package io.afdon.add.ui

import android.app.DatePickerDialog
import android.os.Bundle
import android.view.MenuItem
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import io.afdon.add.R
import io.afdon.add.databinding.FragmentAddBinding
import io.afdon.core.extension.toast
import io.afdon.core.viewmodel.SavedStateViewModelFactory
import java.text.SimpleDateFormat
import java.util.*
import javax.inject.Inject

class AddTvShowFragment @Inject constructor(
    private val factory: SavedStateViewModelFactory
) : Fragment(R.layout.fragment_add) {

    private val viewModel by viewModels<AddTvShowViewModel> {
        factory.create(this@AddTvShowFragment, arguments)
    }
    private var binding: FragmentAddBinding? = null
    private val calendar = Calendar.getInstance()
    private var datePickerDialog: DatePickerDialog? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initToolbar()
        setBinding(view)
        setDatePicker()
        observeViewModel()
    }

    private fun initToolbar() {
        (requireActivity() as AppCompatActivity).supportActionBar?.apply {
            title = "Add Tv Show"
            setDisplayHomeAsUpEnabled(true)
        }
    }

    private fun setBinding(v: View) {
        binding = FragmentAddBinding.bind(v).apply {
            lifecycleOwner = viewLifecycleOwner
            vm = viewModel
            bOpenDatePicker.setOnClickListener { datePickerDialog?.show() }
        }
    }

    private fun setDatePicker() {
        datePickerDialog = DatePickerDialog(
            requireContext(),
            { _, year, month, dayOfMonth ->
                calendar.set(Calendar.YEAR, year)
                calendar.set(Calendar.MONTH, month)
                calendar.set(Calendar.DAY_OF_MONTH, dayOfMonth)
                viewModel.setDate(formatDate())
            },
            calendar.get(Calendar.YEAR),
            calendar.get(Calendar.MONTH),
            calendar.get(Calendar.DAY_OF_MONTH)
        )
    }

    private fun observeViewModel() {
        viewModel.error.observe(viewLifecycleOwner) {
            it.getContentIfNotHandled()?.let { error ->
                toast(error)
            }
        }
        viewModel.successEvent.observe(viewLifecycleOwner) {
            it.getContentIfNotHandled()?.let {
                toast("TV Show has been saved successfully")
                requireActivity().onBackPressed()
            }
        }
    }

    private fun formatDate() : String {
        val sdf = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault())
        return sdf.format(calendar.time)
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
        datePickerDialog?.dismiss()
        datePickerDialog = null
    }
}