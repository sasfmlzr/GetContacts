package contact.utils

import contact.api.location.GetLocation
import io.reactivex.Single
import org.joda.time.LocalDateTime

interface LocationUtils {
    fun getMinMaxDate(locations: List<GetLocation>) : Single<Pair<LocalDateTime, LocalDateTime>>
}
