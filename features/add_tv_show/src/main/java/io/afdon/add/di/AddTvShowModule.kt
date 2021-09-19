package io.afdon.add.di

import dagger.Binds
import dagger.Module
import io.afdon.add.repo.AddTvShowRepository
import io.afdon.add.repo.impl.AddTvShowRepositoryImpl
import io.afdon.add.usecase.AddTvShowUseCase
import io.afdon.add.usecase.impl.AddTvShowUseCaseImpl

@Module
interface AddTvShowModule {

    @Binds
    fun bindAddTvShowUseCase(useCase: AddTvShowUseCaseImpl) : AddTvShowUseCase

    @Binds
    fun bindAddTvShowRepository(repository: AddTvShowRepositoryImpl) : AddTvShowRepository
}