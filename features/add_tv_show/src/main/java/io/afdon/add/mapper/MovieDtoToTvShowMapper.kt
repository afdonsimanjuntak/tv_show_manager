package io.afdon.add.mapper

import com.combyne.tvshowmanager.SaveTvShowMutation
import io.afdon.add.BuildConfig
import io.afdon.add.model.TvShow
import io.afdon.core.utils.DateConverter.convertReleaseDate
import io.afdon.data.mapper.Mapper
import java.lang.Exception
import javax.inject.Inject

class MovieDtoToTvShowMapper @Inject constructor() : Mapper<SaveTvShowMutation.Movie, TvShow> {

    override fun map(input: SaveTvShowMutation.Movie?): TvShow? {
        return try {
            TvShow(
                input?.title as String,
                convertReleaseDate(input.releaseDate) as String,
                input.seasons.toString()
            )
        } catch (e: Exception) {
            if (BuildConfig.DEBUG) e.printStackTrace()
            null
        }
    }
}