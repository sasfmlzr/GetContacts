package contact.pipe.contactsowner

import contact.api.model.contact.OwnerContacts
import contact.architecture.EventModel
import contact.base.TestEventModel
import io.reactivex.Observable
import io.reactivex.observers.TestObserver
import org.junit.Test

class ContactsOwnerFunnelTest {

    lateinit var currentState: ContactsOwnerState
    private val initState = ContactsOwnerState.idle()

    @Test
    fun `LocationEventModel test`() {
        val eventModel = ObserveContactsOwnerEventModel(
                listOf(OwnerContacts("test")))

        getStatesObserver(eventModel)
                .assertValues(initState, currentState)
                .assertNoErrors()
                .assertComplete()
    }

    @Test
    fun `OtherModel test`() {
        val eventModel = TestEventModel()

        getStatesObserver(eventModel)
                .assertValues(initState, ContactsOwnerState.idle())
                .assertNoErrors()
                .assertComplete()
    }

    private fun getStatesObserver(eventModel: EventModel): TestObserver<ContactsOwnerState> {
        val locationFunnel = ContactsOwnerFunnel(initState)
        currentState = ContactsOwnerState(eventModel)
        val testObserver = TestObserver.create<ContactsOwnerState>()
        locationFunnel.apply(Observable.just(eventModel))
                .subscribe(testObserver)
        return testObserver
    }

}
