package contact.pipe.contacts

import contact.architecture.Result

data class ContactsInitEventModel(
        val isError: Boolean = false,
        val error: Throwable? = null,
        val contactsResult: String? = null
) : Result()

data class ContactsPushEventModel(
        val contactsPushResult: String? = null
) : Result()
