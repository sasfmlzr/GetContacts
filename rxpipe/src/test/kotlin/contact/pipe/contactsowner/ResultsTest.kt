package contact.pipe.contactsowner

import contact.api.model.contact.OwnerContacts
import org.junit.Assert
import org.junit.Test

class ResultsTest {

    @Test
    fun `ObserveContactsOwnerEventModel verify`() {
        val eventModelOne = ObserveContactsOwnerEventModel(listOf(OwnerContacts("test")))
        val eventModelTwo = ObserveContactsOwnerEventModel(listOf(OwnerContacts("test")))
        Assert.assertEquals(eventModelOne.contacts, eventModelTwo.contacts)
    }
}
