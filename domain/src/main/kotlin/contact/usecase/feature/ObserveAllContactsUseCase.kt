package contact.usecase.feature

import contact.api.model.contact.OwnerContacts
import contact.architecture.logging.Logger
import contact.storage.ContactStorage
import contact.usecase.base.ObservableUseCase
import io.reactivex.Observable
import javax.inject.Inject

class ObserveAllContactsUseCase @Inject constructor(
        private val logger: Logger,
        private val contactsStorage: ContactStorage
) :ObservableUseCase<Unit, List<OwnerContacts>>(){

    override fun buildUseCaseObservable(params: Unit): Observable<List<OwnerContacts>> {

        contactsStorage.fetch().doOnError{
            logger.e(it.message!!)
        }.subscribe()
        return contactsStorage.getAll()
    }

}
