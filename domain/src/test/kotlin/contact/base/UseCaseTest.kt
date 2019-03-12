package contact.base

import contact.usecase.base.CompletableUseCase
import contact.usecase.base.ObservableUseCase
import contact.usecase.base.SingleUseCase
import io.reactivex.CompletableObserver
import io.reactivex.Observer
import io.reactivex.SingleObserver
import org.junit.runner.RunWith
import org.mockito.junit.MockitoJUnitRunner


@RunWith(MockitoJUnitRunner::class)
internal abstract class UseCaseTest : BaseTest() {

    protected fun <P> whenSubscribed(useCase: CompletableUseCase<P>, params: P, observer: CompletableObserver) {
        useCase.buildUseCaseObservable(params).subscribe(observer)
    }

    protected fun whenSubscribed(useCase: CompletableUseCase<Unit>, observer: CompletableObserver) {
        useCase.buildUseCaseObservable(Unit).subscribe(observer)
    }

    protected fun <P, R> whenSubscribed(useCase: SingleUseCase<P, R>, params: P, observer: SingleObserver<R>) {
        useCase.buildUseCaseObservable(params).subscribe(observer)
    }

    protected fun <R> whenSubscribed(useCase: SingleUseCase<Unit, R>, observer: SingleObserver<R>) {
        useCase.buildUseCaseObservable(Unit).subscribe(observer)
    }

    protected fun <P, R> whenSubscribed(useCase: ObservableUseCase<P, R>, params: P, observer: Observer<R>) {
        useCase.buildUseCaseObservable(params).subscribe(observer)
    }

    protected fun <R> whenSubscribed(useCase: ObservableUseCase<Unit, R>, observer: Observer<R>) {
        useCase.buildUseCaseObservable(Unit).subscribe(observer)
    }

}
