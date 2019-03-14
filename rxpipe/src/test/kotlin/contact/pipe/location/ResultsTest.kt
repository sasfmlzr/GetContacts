package contact.pipe.location

import contact.api.location.GetLocation
import org.joda.time.LocalDateTime
import org.junit.Assert
import org.junit.Test

class ResultsTest {

    @Test
    fun `LocationEventModel verify`() {
        val date = LocalDateTime()
        val eventModelOne = LocationEventModel(listOf(GetLocation(0.0, 0.0, date)))
        val eventModelTwo = LocationEventModel(listOf(GetLocation(0.0, 0.0, date)))
        Assert.assertEquals(eventModelOne.locations, eventModelTwo.locations)
    }

    @Test
    fun `MinMaxDateEventModel verify`() {
        val date = LocalDateTime()
        val eventModelOne = MinMaxDateEventModel(date, date.minusMonths(1))
        val eventModelTwo = MinMaxDateEventModel(date, date.minusMonths(1))
        Assert.assertEquals(eventModelOne.minDate, eventModelTwo.minDate)
        Assert.assertEquals(eventModelOne.maxDate, eventModelTwo.maxDate)
    }

    @Test
    fun `ToolbarEventModel verify`() {
        val eventModelOne = ToolbarEventModel("test")
        val eventModelTwo = ToolbarEventModel("test")
        Assert.assertEquals(eventModelOne.title, eventModelTwo.title)
    }

    @Test
    fun `BundleModel verify`() {
        val eventModelOne = BundleModel("test")
        val eventModelTwo = BundleModel("test")
        Assert.assertEquals(eventModelOne.nameOwner, eventModelTwo.nameOwner)
    }
}
