package contact.fragment.location

import contact.R
import contact.architecture.base.BaseFragment
import contact.di.core.FragmentComponent
import contact.pipe.location.LocationFunnel
import contact.pipe.location.LocationPipeline
import contact.pipe.location.LocationState
import javax.inject.Inject

class LocationFragment : BaseFragment<LocationState, LocationModel, LocationUi>() {

    override val layoutId: Int = R.layout.fragment_location

    @Inject
    override lateinit var presenter: LocationPresenter
    @Inject
    override lateinit var pipeline: LocationPipeline
    @Inject
    override lateinit var ui: LocationUi

    override fun funnel(): LocationFunnel = LocationFunnel(LocationState.idle())

    override fun inject(component: FragmentComponent) = component.inject(this)
}
