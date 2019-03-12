package contact.pipe.contactsowner

import contact.api.model.contact.OwnerContacts
import contact.base.TestEvent
import contact.base.willReturn
import contact.usecase.feature.ObserveAllContactsUseCase
import io.reactivex.Observable
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.InjectMocks
import org.mockito.Mock
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
internal class ObserveContactsOwnerPipeTest {

    @InjectMocks
    lateinit var pipe: ObserveContactsOwnerPipe
    @Mock
    lateinit var observeAllContacts: ObserveAllContactsUseCase

    @Test
    fun `Test event does not emit anything`() {
        Observable.just(TestEvent())
                .compose(pipe)
                .test()
                .assertNoValues()
                .assertNoErrors()
    }

    @Test
    fun `Loading contacts owner are emitted on success`() {
        val result = listOf(OwnerContacts())
        observeAllContacts.willReturn(result)

        Observable.just(RequestObserveContactsOwnerEvent())
                .compose(pipe)
                .test()
                .assertValues(
                        ObserveContactsOwnerEventModel(result)
                )
                .assertNoErrors()
    }
}
