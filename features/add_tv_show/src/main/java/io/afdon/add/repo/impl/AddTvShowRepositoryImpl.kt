package io.afdon.add.repo.impl

import com.apollographql.apollo.ApolloClient
import com.apollographql.apollo.api.Input
import com.apollographql.apollo.coroutines.await
import com.combyne.tvshowmanager.SaveTvShowMutation
import io.afdon.add.BuildConfig
import io.afdon.add.mapper.MovieDtoToTvShowMapper
import io.afdon.add.model.ReqResult
import io.afdon.add.model.TvShow
import io.afdon.add.repo.AddTvShowRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class AddTvShowRepositoryImpl @Inject constructor(
    private val apolloClient: ApolloClient,
    private val movieDtoToTvShowMapper: MovieDtoToTvShowMapper
) : AddTvShowRepository {

    override fun save(tvShow: TvShow): Flow<ReqResult<TvShow>> = flow {
        emit(ReqResult.Loading(true))
        try {
            val res = apolloClient.mutate(SaveTvShowMutation(
                tvShow.title,
                Input.optional(tvShow.releaseDate),
                Input.optional(tvShow.season.toDouble())
            )).await()
            movieDtoToTvShowMapper.map(res.data?.createMovie?.movie)?.let {
                emit(ReqResult.Success(it))
            } ?: emit(ReqResult.Error("Invalid result"))
        } catch (e: Exception) {
            if (BuildConfig.DEBUG) e.printStackTrace()
            emit(ReqResult.Error("Error fetching movies", e))
        } finally {
            emit(ReqResult.Loading(false))
        }
    }
}