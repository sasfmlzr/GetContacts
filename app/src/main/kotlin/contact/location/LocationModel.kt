package contact.location

import contact.architecture.base.ui.UiModel
import contact.architecture.EventModel

data class LocationModel(
        val eventModel: EventModel? = null
) : UiModel
