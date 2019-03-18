package contact.utils

import contact.api.location.Location
import io.reactivex.Single
import org.joda.time.LocalDateTime

interface LocationUtils {
    fun getMinMaxDate(locations: List<Location>) : Single<Pair<LocalDateTime, LocalDateTime>>
}
