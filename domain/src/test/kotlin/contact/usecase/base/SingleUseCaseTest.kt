package contact.usecase.base

import contact.base.UseCaseTest
import io.reactivex.SingleObserver
import org.mockito.ArgumentMatchers.any
import org.mockito.Mock
import org.mockito.Mockito.verify
import org.mockito.Mockito.verifyNoMoreInteractions

internal abstract class SingleUseCaseTest<T> : UseCaseTest() {

    @Mock
    lateinit var observer: SingleObserver<T>

    protected fun verifySuccess(successValue: T) {
        verify(observer).onSubscribe(any())
        verify(observer).onSuccess(successValue)
        verifyNoMoreInteractions(observer)
    }

    protected fun verifyError() {
        verify(observer).onSubscribe(any())
        verify(observer).onError(any())
        verifyNoMoreInteractions(observer)
    }

}
