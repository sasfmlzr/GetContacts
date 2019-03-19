package contact.architecture.base

import contact.architecture.EventModel
import contact.architecture.base.ui.UiModel

data class BaseModel(
        val eventModel: EventModel? = null
) : UiModel
