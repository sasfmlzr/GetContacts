package contact.usecase.feature

import contact.api.model.contact.OwnerContacts
import contact.storage.ContactStorage
import contact.usecase.base.ObservableUseCase
import javax.inject.Inject

class ObserveAllContactsUseCase @Inject constructor(
        private val contactsStorage: ContactStorage
) : ObservableUseCase<Unit, List<OwnerContacts>>() {

    override fun buildUseCaseObservable(params: Unit) =
            contactsStorage.getAll()

}
