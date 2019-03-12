package contact.usecase.base

import contact.base.UseCaseTest
import io.reactivex.CompletableObserver
import org.mockito.ArgumentMatchers.any
import org.mockito.Mock
import org.mockito.Mockito.verify
import org.mockito.Mockito.verifyNoMoreInteractions

internal abstract class CompletableUseCaseTest : UseCaseTest() {

    @Mock
    lateinit var observer: CompletableObserver

    protected fun verifySuccess() {
        verify(observer).onSubscribe(any())
        verify(observer).onComplete()
        verifyNoMoreInteractions(observer)
    }

    protected fun verifyError() {
        verify(observer).onSubscribe(any())
        verify(observer).onError(any())
        verifyNoMoreInteractions(observer)
    }

}
