package contact.storage

import contact.api.location.GetLocation
import contact.api.model.contact.OwnerContacts
import io.reactivex.Completable
import io.reactivex.Observable
import io.reactivex.Single

interface ContactStorage {
    fun fetch(): Completable
    fun getAll(): Observable<List<OwnerContacts>>
    fun getLocationById(id: String): Single<List<GetLocation>>
}
