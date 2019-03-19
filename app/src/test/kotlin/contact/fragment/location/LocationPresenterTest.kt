package contact.fragment.location

import contact.api.model.contact.OwnerContacts
import contact.architecture.base.BaseModel
import contact.pipe.contactsowner.ObserveContactsOwnerEventModel
import contact.pipe.location.LocationState
import io.reactivex.Observable
import io.reactivex.observers.TestObserver
import org.junit.Assert
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.InjectMocks
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class LocationPresenterTest {

    @InjectMocks
    lateinit var presenter: LocationPresenter

    @Test
    fun `Happy pass when event model equal null`() {
        val resultModel = BaseModel(eventModel = null)
        val state = Observable.just(LocationState(eventModel = null))

        val observer = TestObserver<BaseModel>()

        presenter.apply(state).subscribe(observer)

        observer
                .assertNoErrors()
                .assertComplete()
                .assertValue(resultModel)

        Assert.assertEquals(resultModel.eventModel, null)
    }

    @Test
    fun `Happy pass when event model equal anything model`() {
        val eventModel = ObserveContactsOwnerEventModel(listOf(OwnerContacts("test")))
        val resultModel = BaseModel(eventModel)
        val state = Observable.just(LocationState(eventModel))

        val observer = TestObserver<BaseModel>()

        presenter.apply(state).subscribe(observer)

        observer
                .assertNoErrors()
                .assertComplete()
                .assertValue(resultModel)

        Assert.assertEquals(resultModel.eventModel, eventModel)
    }
}
