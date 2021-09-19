package io.afdon.list.di

import dagger.Binds
import dagger.Module
import io.afdon.list.repo.TvShowsRepository
import io.afdon.list.repo.impl.TvShowsRepositoryImpl
import io.afdon.list.usecase.GetTvShowsUseCase
import io.afdon.list.usecase.impl.GetTvShowsUseCaseImpl

@Module
interface TvShowsModule {

    @Binds
    fun bindGetTvShowsUseCase(useCase: GetTvShowsUseCaseImpl) : GetTvShowsUseCase

    @Binds
    fun bindTvShowsRepository(repository: TvShowsRepositoryImpl) : TvShowsRepository
}