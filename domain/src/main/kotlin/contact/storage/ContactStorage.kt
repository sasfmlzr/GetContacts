package contact.storage

import contact.api.model.contact.OwnerContacts
import io.reactivex.Completable
import io.reactivex.Observable

interface ContactStorage {
    fun getAll(): Observable<List<OwnerContacts>>
}