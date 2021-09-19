package io.afdon.list.mapper

import com.combyne.tvshowmanager.GetTvShowsQuery
import io.afdon.core.utils.DateConverter.convertReleaseDate
import io.afdon.data.mapper.Mapper
import io.afdon.list.BuildConfig
import io.afdon.list.model.TvShow
import java.lang.Exception
import javax.inject.Inject

class EdgeToTvShowMapper @Inject constructor() : Mapper<GetTvShowsQuery.Edge, TvShow> {

    override fun map(input: GetTvShowsQuery.Edge?): TvShow? {
        return try {
            TvShow(
                input?.node?.title as String,
                convertReleaseDate(input.node?.releaseDate) as String,
                input.node?.seasons?.toInt().toString()
            )
        } catch (e: Exception) {
            if (BuildConfig.DEBUG) e.printStackTrace()
            null
        }
    }
}