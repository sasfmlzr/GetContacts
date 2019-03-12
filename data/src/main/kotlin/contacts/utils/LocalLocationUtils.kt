package contacts.utils

import contact.api.location.GetLocation
import contact.utils.LocationUtils
import io.reactivex.Single
import org.joda.time.LocalDateTime
import javax.inject.Inject

class LocalLocationUtils @Inject constructor() : LocationUtils {

    override fun getMinMaxDate(locations: List<GetLocation>):
            Single<Pair<LocalDateTime, LocalDateTime>> = Single.fromCallable {
            try {
                executeParseMinMaxDateFromLocation(locations)
            } catch (e: Exception) {
                throw RuntimeException("Location could not be empty")
            }
        }

    private fun executeParseMinMaxDateFromLocation(locations: List<GetLocation>):
            Pair<LocalDateTime, LocalDateTime> {
        var minDate: LocalDateTime = locations.first().date
        var maxDate: LocalDateTime = locations.first().date
        locations.forEach {
            if (it.date < minDate) {
                minDate = it.date
            }
            if (it.date > maxDate) {
                maxDate = it.date
            }
        }
        return Pair(minDate, maxDate)
    }
}
