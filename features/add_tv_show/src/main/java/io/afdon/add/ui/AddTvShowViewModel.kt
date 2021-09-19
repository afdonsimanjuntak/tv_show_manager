package io.afdon.add.ui

import android.view.View
import androidx.lifecycle.*
import dagger.assisted.Assisted
import dagger.assisted.AssistedFactory
import dagger.assisted.AssistedInject
import io.afdon.add.model.ReqResult
import io.afdon.add.model.TvShow
import io.afdon.add.usecase.AddTvShowUseCase
import io.afdon.core.event.Event
import io.afdon.core.extension.cancelIfActive
import io.afdon.core.viewmodel.AssistedViewModelFactory
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

class AddTvShowViewModel @AssistedInject constructor(
    @Assisted private val savedStateHandle: SavedStateHandle,
    private val addTvShowUseCase: AddTvShowUseCase
) : ViewModel() {

    @AssistedFactory
    interface Factory : AssistedViewModelFactory<AddTvShowViewModel>

    val title = savedStateHandle.getLiveData<String>("title")
    val releaseDate = savedStateHandle.getLiveData<String>("release_date")
    val season = savedStateHandle.getLiveData<String>("season")

    private val _progressVisibility = MutableLiveData<Int>(View.GONE)
    val progressVisibility: LiveData<Int> = _progressVisibility

    private val _error = MutableLiveData<Event<String>>()
    val error: LiveData<Event<String>> = _error

    private val _successEvent = MutableLiveData<Event<SuccessEvent>>()
    val successEvent: LiveData<Event<SuccessEvent>> = _successEvent

    private var job: Job? = null

    fun addTvShow() {
        if (!isValidInput()) return
        job.cancelIfActive()
        job = viewModelScope.launch {
            val tvShow = TvShow(
                title.value as String,
                releaseDate.value as String,
                season.value as String
            )
            addTvShowUseCase.save(tvShow).collect {
                when (it) {
                    is ReqResult.Loading -> {
                        _progressVisibility.value = if (it.isLoading) View.VISIBLE else View.GONE
                        if (!it.isLoading) job.cancelIfActive()
                    }
                    is ReqResult.Error -> {
                        _error.value = Event(it.error)
                    }
                    is ReqResult.Success -> {
                        _successEvent.value = Event(SuccessEvent)
                    }
                }
            }
        }
    }

    fun setDate(date: String) {
        releaseDate.value = date
    }

    private fun isValidInput() : Boolean {
        if (title.value.isNullOrBlank()) {
            _error.value = Event("Title is required")
            return false
        }
        if (releaseDate.value.isNullOrBlank()) {
            _error.value = Event("Release Date is required")
            return false
        }
        if (season.value.isNullOrBlank()) {
            _error.value = Event("Season is required")
            return false
        }
        return true
    }

    object SuccessEvent
}