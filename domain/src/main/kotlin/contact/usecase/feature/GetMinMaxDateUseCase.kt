package contact.usecase.feature

import contact.api.location.Location
import contact.usecase.base.SingleUseCase
import contact.utils.LocationUtils
import org.joda.time.LocalDateTime
import javax.inject.Inject

open class GetMinMaxDateUseCase @Inject constructor(
        private val locationUtils: LocationUtils
) : SingleUseCase<List<Location>, Pair<LocalDateTime, LocalDateTime>>() {

    override fun buildUseCaseObservable(params: List<Location>) =
            locationUtils.getMinMaxDate(params)

}
