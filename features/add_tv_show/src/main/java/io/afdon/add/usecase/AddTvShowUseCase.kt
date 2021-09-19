package io.afdon.add.usecase

import io.afdon.add.model.ReqResult
import io.afdon.add.model.TvShow
import kotlinx.coroutines.flow.Flow

interface AddTvShowUseCase {

    fun save(tvShow: TvShow) : Flow<ReqResult<TvShow>>
}