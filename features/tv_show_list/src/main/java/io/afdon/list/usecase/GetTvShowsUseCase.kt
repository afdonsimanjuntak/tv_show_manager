package io.afdon.list.usecase

import io.afdon.list.model.ReqResult
import io.afdon.list.model.TvShow
import kotlinx.coroutines.flow.Flow

interface GetTvShowsUseCase {

    fun getTvShows() : Flow<ReqResult<List<TvShow>>>
}