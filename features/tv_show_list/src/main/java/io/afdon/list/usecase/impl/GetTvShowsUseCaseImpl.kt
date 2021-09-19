package io.afdon.list.usecase.impl

import io.afdon.list.model.ReqResult
import io.afdon.list.model.TvShow
import io.afdon.list.repo.TvShowsRepository
import io.afdon.list.usecase.GetTvShowsUseCase
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetTvShowsUseCaseImpl @Inject constructor(
    private val repository: TvShowsRepository
) : GetTvShowsUseCase {

    override fun getTvShows(): Flow<ReqResult<List<TvShow>>> = repository.getTvShows()
}