package contact.usecase.feature

import contact.storage.ContactStorage
import contact.usecase.base.CompletableUseCase
import io.reactivex.Completable
import javax.inject.Inject

class InitUseCase @Inject constructor(
        private val contactsStorage: ContactStorage
) : CompletableUseCase<Unit>() {

    override fun buildUseCaseObservable(params: Unit) : Completable{
        return contactsStorage.fetch()
    }

}
