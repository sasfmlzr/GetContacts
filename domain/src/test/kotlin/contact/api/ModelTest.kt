package contact.api

import contact.api.location.GetLocation
import contact.api.model.contact.Contact
import contact.api.model.contact.OwnerContacts
import org.joda.time.LocalDateTime
import org.junit.Assert
import org.junit.Test

class ModelTest {
    private val location = GetLocation(0.0, 0.0, LocalDateTime())
    private val contact = Contact("test", "test")

    @Test
    fun `Verify location model`() {
        Assert.assertEquals(location.latitude, 0.0, 0.0)
        Assert.assertEquals(location.longitude, 0.0, 0.0)
    }

    @Test
    fun `Verify contact model`() {
        Assert.assertEquals(contact.name, "test")
        Assert.assertEquals(contact.phone, "test")
    }

    @Test
    fun `Verify contact owner model`() {
        val ownerContacts = OwnerContacts("test",
                mutableListOf(contact),
                mutableListOf(location))
        Assert.assertEquals(ownerContacts.id, "test")
        Assert.assertEquals(ownerContacts.contacts.first(), contact)
        Assert.assertEquals(ownerContacts.locations.first(), location)
    }
}
