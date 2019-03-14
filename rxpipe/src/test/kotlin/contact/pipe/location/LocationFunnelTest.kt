package contact.pipe.location

import contact.api.location.GetLocation
import contact.architecture.ErrorEventModel
import contact.architecture.EventModel
import contact.base.TestEventModel
import io.reactivex.Observable
import io.reactivex.observers.TestObserver
import org.joda.time.LocalDateTime
import org.junit.Test

class LocationFunnelTest {

    lateinit var locationState: LocationState
    private val initModel = BundleModel("test")
    private val initState = LocationState.idle(initModel)

    @Test
    fun `LocationEventModel test`() {
        val eventModel = LocationEventModel(
                listOf(GetLocation(0.0, 0.0, LocalDateTime())))

        getStatesObserver(eventModel)
                .assertValues(initState, locationState)
                .assertNoErrors()
                .assertComplete()
    }

    @Test
    fun `MinMaxDateEventModel test`() {
        val eventModel = MinMaxDateEventModel(LocalDateTime(), LocalDateTime())

        getStatesObserver(eventModel)
                .assertValues(initState, locationState)
                .assertNoErrors()
                .assertComplete()
    }

    @Test
    fun `ToolbarEventModel test`() {
        val eventModel = ToolbarEventModel("Test")

        getStatesObserver(eventModel)
                .assertValues(initState, locationState)
                .assertNoErrors()
                .assertComplete()
    }

    @Test
    fun `ErrorEventModel test`() {
        val eventModel = ErrorEventModel(RuntimeException("Test"))

        getStatesObserver(eventModel)
                .assertValues(initState, locationState)
                .assertNoErrors()
                .assertComplete()
    }


    @Test
    fun `OtherModel test`() {
        val eventModel = TestEventModel()

        getStatesObserver(eventModel)
                .assertValues(initState, LocationState())
                .assertNoErrors()
                .assertComplete()
    }

    private fun getStatesObserver(eventModel: EventModel): TestObserver<LocationState> {
        val locationFunnel = LocationFunnel(initState)
        locationState = LocationState(eventModel)
        val testObserver = TestObserver.create<LocationState>()
        locationFunnel.apply(Observable.just(eventModel))
                .subscribe(testObserver)
        return testObserver
    }

}
