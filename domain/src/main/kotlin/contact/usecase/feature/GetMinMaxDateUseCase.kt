package contact.usecase.feature

import contact.api.location.GetLocation
import contact.usecase.base.SingleUseCase
import contact.utils.LocationUtils
import org.joda.time.LocalDateTime
import javax.inject.Inject

class GetMinMaxDateUseCase @Inject constructor(
        private val locationUtils: LocationUtils
) : SingleUseCase<List<GetLocation>, Pair<LocalDateTime, LocalDateTime>>() {

    override fun buildUseCaseObservable(params: List<GetLocation>) =
            locationUtils.getMinMaxDate(params)

}
