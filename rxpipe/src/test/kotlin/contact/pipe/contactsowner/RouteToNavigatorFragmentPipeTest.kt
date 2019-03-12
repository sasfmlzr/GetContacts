package contact.pipe.contactsowner

import contact.architecture.Router
import contact.base.TestEvent
import io.reactivex.Observable
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.InjectMocks
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
internal class RouteToNavigatorFragmentPipeTest {

    @InjectMocks
    lateinit var pipe: RouteToNavigatorFragmentPipe
    @Mock
    lateinit var router: Router

    @Test
    fun `Test event does not emit anything`() {
        Observable.just(TestEvent())
                .compose(pipe)
                .test()
                .assertNoValues()
                .assertNoErrors()
    }

    @Test
    fun `Router redirects to navigator on success`() {
        Observable.just(RouteToNavigatorFragmentEvent("test"))
                .compose(pipe)
                .test()
                .assertNoErrors()

        Mockito.verify(router).navigateContactOwnerToLocationFragment("test")
    }

}