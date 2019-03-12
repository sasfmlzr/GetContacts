package contact.usecase.base

import contact.base.UseCaseTest
import io.reactivex.Observer
import org.mockito.ArgumentMatchers.any
import org.mockito.Mock
import org.mockito.Mockito.verify
import org.mockito.Mockito.verifyNoMoreInteractions

internal abstract class ObservableUseCaseTest<T> : UseCaseTest() {

    @Mock
    lateinit var observer: Observer<T>

    protected fun verifySuccessfulEmission(successValue: T) {
        verify(observer).onSubscribe(any())
        verify(observer).onNext(successValue)
        verify(observer).onComplete()
        verifyNoMoreInteractions(observer)
    }

    protected fun verifyNoEmission() {
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
