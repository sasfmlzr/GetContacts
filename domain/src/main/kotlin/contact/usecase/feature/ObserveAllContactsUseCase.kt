package contact.usecase.feature

import contact.api.model.contact.OwnerContacts
import contact.storage.ContactStorage
import contact.usecase.base.ObservableUseCase
import io.reactivex.Observable
import javax.inject.Inject

class ObserveAllContactsUseCase @Inject constructor(
        private val contactsStorage: ContactStorage
) :ObservableUseCase<Unit, List<OwnerContacts>>(){

    override fun buildUseCaseObservable(params: Unit): Observable<List<OwnerContacts>> =
                contactsStorage.getAll()

}
