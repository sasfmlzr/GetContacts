package contacts.utils

import contact.api.location.GetLocation
import contact.utils.LocationUtils
import io.reactivex.Single
import org.joda.time.LocalDateTime
import javax.inject.Inject

class LocalLocationUtils @Inject constructor() : LocationUtils {

    private lateinit var minDate: LocalDateTime
    private lateinit var maxDate: LocalDateTime

    override fun getMinMaxDate(locations: List<GetLocation>):
            Single<Pair<LocalDateTime, LocalDateTime>> {

        locations.forEach {
            if (!::minDate.isInitialized) {
                minDate = it.date
            }
            if (it.date < minDate) {
                minDate = it.date
            }

            if (!::maxDate.isInitialized) {
                maxDate = it.date
            }
            if (it.date > maxDate) {
                maxDate = it.date
            }
        }
        return Single.fromCallable {
            Pair(minDate, maxDate)
        }
    }
}
