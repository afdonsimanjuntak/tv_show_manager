package io.afdon.list.repo

import io.afdon.list.model.ReqResult
import io.afdon.list.model.TvShow
import kotlinx.coroutines.flow.Flow

interface TvShowsRepository {

    fun getTvShows() : Flow<ReqResult<List<TvShow>>>
}