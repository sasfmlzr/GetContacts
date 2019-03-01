package contact.pipe.location

import contact.api.location.GetLocation
import contact.architecture.EventModel
import org.joda.time.LocalDateTime

data class LocationEventModel(
        val locations: List<GetLocation>
) : EventModel()

data class MinMaxDateEventModel(
        val minDate: LocalDateTime,
        val maxDate: LocalDateTime
) : EventModel()

data class ToolbarEventModel(
        val title: String) : EventModel()

data class BundleModel(
        val nameOwner: String) : EventModel()