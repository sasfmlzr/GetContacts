package contact.usecase.feature

import contact.api.location.GetLocation
import contact.storage.ContactStorage
import contact.usecase.base.SingleUseCase
import javax.inject.Inject

class ObserveOwnerContactByIdUseCase @Inject constructor(
        private val contactsStorage: ContactStorage
) : SingleUseCase<String, List<GetLocation>>() {

    override fun buildUseCaseObservable(params: String) =
            contactsStorage.getLocationById(params)

}
