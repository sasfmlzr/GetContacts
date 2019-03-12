package contact.base

import io.reactivex.Completable
import io.reactivex.Observable
import io.reactivex.Single
import org.mockito.BDDMockito

internal abstract class BaseTest {

    protected fun BDDMockito.BDDMyOngoingStubbing<Completable>.willComplete():
            BDDMockito.BDDMyOngoingStubbing<Completable> = willReturn(Completable.complete())

    protected fun BDDMockito.BDDMyOngoingStubbing<Completable>.willReturnCompletableError():
            BDDMockito.BDDMyOngoingStubbing<Completable> =
            willReturn(Completable.error(TestException()))

    protected fun BDDMockito.BDDMyOngoingStubbing<Completable>.willReturnCompletableError(error: Throwable):
            BDDMockito.BDDMyOngoingStubbing<Completable> =
            willReturn(Completable.error(error))

    protected fun <T> BDDMockito.BDDMyOngoingStubbing<Single<T>>.willReturnSingleError():
            BDDMockito.BDDMyOngoingStubbing<Single<T>> =
            willReturn(Single.error(TestException()))

    protected fun <T> BDDMockito.BDDMyOngoingStubbing<Single<T>>.willReturnSingleError(error: Throwable):
            BDDMockito.BDDMyOngoingStubbing<Single<T>> =
            willReturn(Single.error(error))

    protected fun <T> BDDMockito.BDDMyOngoingStubbing<Single<T>>.willReturnSingle(value: T):
            BDDMockito.BDDMyOngoingStubbing<Single<T>> =
            willReturn(Single.just(value))

    protected fun <T> BDDMockito.BDDMyOngoingStubbing<Observable<T>>.willReturn(value: T):
            BDDMockito.BDDMyOngoingStubbing<Observable<T>> =
            willReturn(Observable.just(value))

    protected fun <T> BDDMockito.BDDMyOngoingStubbing<Observable<T>>.willReturnObservableError():
            BDDMockito.BDDMyOngoingStubbing<Observable<T>> =
            willReturn(Observable.error(TestException()))

    protected fun <T> BDDMockito.BDDMyOngoingStubbing<Observable<T>>.willNotEmitItems():
            BDDMockito.BDDMyOngoingStubbing<Observable<T>> =
            willReturn(Observable.empty())

}
