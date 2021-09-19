package io.afdon.add.usecase.impl

import io.afdon.add.model.ReqResult
import io.afdon.add.model.TvShow
import io.afdon.add.repo.AddTvShowRepository
import io.afdon.add.usecase.AddTvShowUseCase
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class AddTvShowUseCaseImpl @Inject constructor(
    private val addTvShowRepository: AddTvShowRepository
) : AddTvShowUseCase {

    override fun save(tvShow: TvShow): Flow<ReqResult<TvShow>> = addTvShowRepository.save(tvShow)
}