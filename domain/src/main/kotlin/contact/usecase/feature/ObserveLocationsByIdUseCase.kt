package contact.usecase.feature

import contact.api.location.GetLocation
import contact.storage.ContactStorage
import contact.usecase.base.SingleUseCase
import org.joda.time.LocalDate
import javax.inject.Inject

class ObserveLocationsByIdUseCase @Inject constructor(
        private val contactsStorage: ContactStorage
) : SingleUseCase<ObserveLocationsByIdUseCase.Params, List<GetLocation>>() {

    override fun buildUseCaseObservable(params: Params) =
            contactsStorage.getLocationById(params.id,
                    params.fromDate,
                    params.toDate)

    data class Params(val id: String, val fromDate: LocalDate, val toDate: LocalDate)
}
