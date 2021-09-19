package io.afdon.list.ui

import android.view.View
import androidx.lifecycle.*
import dagger.assisted.Assisted
import dagger.assisted.AssistedFactory
import dagger.assisted.AssistedInject
import io.afdon.core.event.Event
import io.afdon.core.extension.cancelIfActive
import io.afdon.core.viewmodel.AssistedViewModelFactory
import io.afdon.list.model.ReqResult
import io.afdon.list.model.TvShow
import io.afdon.list.usecase.GetTvShowsUseCase
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

class TvShowsViewModel @AssistedInject constructor(
    @Assisted private val savedStateHandle: SavedStateHandle,
    private val getTvShowsUseCase: GetTvShowsUseCase
) : ViewModel() {

    @AssistedFactory
    interface Factory : AssistedViewModelFactory<TvShowsViewModel>

    private val _tvShows = MutableLiveData<List<TvShow>>()
    val tvShows: LiveData<List<TvShow>> = _tvShows

    private val _loadingViewVisibility = MutableLiveData(View.GONE)
    val loadingViewVisibility: LiveData<Int> = _loadingViewVisibility

    private val _error = MutableLiveData<Event<String>>()
    val error: LiveData<Event<String>> = _error

    private var job: Job? = null

    fun getTvShows() {
        job.cancelIfActive()
        job = viewModelScope.launch {
            getTvShowsUseCase.getTvShows().collect {
                when (it) {
                    is ReqResult.Loading -> {
                        _loadingViewVisibility.value = if (it.isLoading) View.VISIBLE else View.GONE
                        if (!it.isLoading) job.cancelIfActive()
                    }
                    is ReqResult.Error -> {
                        _error.value = Event(it.error)
                    }
                    is ReqResult.Success -> {
                        it.data.let { items ->_tvShows.value = items }
                    }
                }
            }
        }
    }
}