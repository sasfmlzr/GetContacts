package contact.storage

import contact.api.location.Location
import contact.api.model.contact.OwnerContacts
import io.reactivex.Completable
import io.reactivex.Observable
import io.reactivex.Single
import org.joda.time.LocalDate

interface ContactStorage {
    fun fetch(): Completable
    fun getAll(): Observable<List<OwnerContacts>>
    fun getLocationById(id: String,
                        fromDate: LocalDate,
                        toDate: LocalDate): Single<List<Location>>
}
