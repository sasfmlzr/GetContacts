package contact.fragment.contactsowner

import contact.api.model.contact.OwnerContacts
import contact.architecture.base.BaseModel
import contact.pipe.contactsowner.ContactsOwnerState
import contact.pipe.contactsowner.ObserveContactsOwnerEventModel
import io.reactivex.Observable
import io.reactivex.observers.TestObserver
import org.junit.Assert
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.InjectMocks
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class ContactsOwnerPresenterTest {

    @InjectMocks
    lateinit var presenter: ContactsOwnerPresenter

    @Test
    fun `Happy pass when event model equal null`() {
        val resultModel = BaseModel(eventModel = null)
        val state = Observable.just(ContactsOwnerState(eventModel = null))

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
        val state = Observable.just(ContactsOwnerState(eventModel))

        val observer = TestObserver<BaseModel>()

        presenter.apply(state).subscribe(observer)

        observer
                .assertNoErrors()
                .assertComplete()
                .assertValue(resultModel)

        Assert.assertEquals(resultModel.eventModel, eventModel)
    }
}
