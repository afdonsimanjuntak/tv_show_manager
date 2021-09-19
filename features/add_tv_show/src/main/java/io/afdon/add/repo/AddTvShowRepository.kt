package io.afdon.add.repo

import io.afdon.add.model.ReqResult
import io.afdon.add.model.TvShow
import kotlinx.coroutines.flow.Flow

interface AddTvShowRepository {

    fun save(tvShow: TvShow) : Flow<ReqResult<TvShow>>
}