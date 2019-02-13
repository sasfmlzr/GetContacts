package contact.fragment.location

import contact.architecture.Presenter
import contact.pipe.location.LocationState
import javax.inject.Inject

class LocationPresenter @Inject constructor() : Presenter<LocationState, LocationModel>() {

    override fun map(state: LocationState): LocationModel = LocationModel(
            eventModel = state.eventModel
    )

}
