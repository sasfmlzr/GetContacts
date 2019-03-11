package contact.fragment.location

import contact.architecture.Presenter
import contact.architecture.base.ui.BaseModel
import contact.pipe.location.LocationState
import javax.inject.Inject

class LocationPresenter @Inject constructor() : Presenter<LocationState>() {

    override fun map(state: LocationState): BaseModel = BaseModel(
            eventModel = state.eventModel
    )

}
