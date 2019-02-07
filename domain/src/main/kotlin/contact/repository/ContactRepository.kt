package contact.repository

import contact.api.model.contact.OwnerContacts
import io.reactivex.Single

interface ContactRepository {
        fun getAllOwnerContacts(): Single<List<OwnerContacts>>
}
