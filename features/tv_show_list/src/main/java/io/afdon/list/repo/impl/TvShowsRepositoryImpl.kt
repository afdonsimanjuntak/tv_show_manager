package io.afdon.list.repo.impl

import com.apollographql.apollo.ApolloClient
import com.apollographql.apollo.coroutines.await
import com.apollographql.apollo.coroutines.toDeferred
import com.combyne.tvshowmanager.GetTvShowsQuery
import io.afdon.list.BuildConfig
import io.afdon.list.mapper.EdgeToTvShowMapper
import io.afdon.list.model.ReqResult
import io.afdon.list.model.TvShow
import io.afdon.list.repo.TvShowsRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class TvShowsRepositoryImpl @Inject constructor(
    private val apolloClient: ApolloClient,
    private val edgeToTvShowMapper: EdgeToTvShowMapper
) : TvShowsRepository {

    override fun getTvShows(): Flow<ReqResult<List<TvShow>>> = flow {
        emit(ReqResult.Loading(true))
        try {
            val res = apolloClient.query(GetTvShowsQuery()).await()
            val result = res.data?.movies?.edges?.mapNotNull {
                edgeToTvShowMapper.map(it)
            }.orEmpty()
            emit(ReqResult.Success(result))
        } catch (e: Exception) {
            if (BuildConfig.DEBUG) e.printStackTrace()
            emit(ReqResult.Error("Error fetching movies", e))
        } finally {
            emit(ReqResult.Loading(false))
        }
    }
}