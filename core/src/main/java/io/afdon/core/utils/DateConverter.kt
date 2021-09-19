package io.afdon.core.utils

import java.lang.Exception
import java.text.SimpleDateFormat
import java.util.*

object DateConverter {

    fun convertReleaseDate(releaseDate: Any?) : String? {
        return try {
            val relDate = releaseDate as String
            val serverFormat = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'", Locale.getDefault())
            val localFormat = SimpleDateFormat("d MMMM yyyy", Locale.getDefault())
            val date = serverFormat.parse(relDate)
            return localFormat.format(date?.time)
        } catch (e: Exception) {
            null
        }
    }
}