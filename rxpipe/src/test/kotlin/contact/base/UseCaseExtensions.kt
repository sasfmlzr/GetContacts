package contact.base

import contact.usecase.base.CompletableUseCase
import contact.usecase.base.ObservableUseCase
import contact.usecase.base.SingleUseCase
import io.reactivex.Completable
import io.reactivex.Observable
import io.reactivex.Single
import org.mockito.BDDMockito.given

internal fun <P, R> SingleUseCase<P, R>.willReturn(params: P, result: R) {
    given(buildUseCaseObservable(params)).willReturn(Single.just(result))
}

internal fun <R> SingleUseCase<Unit, R>.willReturn(result: R) {
    given(buildUseCaseObservable(Unit)).willReturn(Single.just(result))
}

internal fun <P> SingleUseCase<P, *>.willReturnError(params: P, error: Throwable) {
    given(buildUseCaseObservable(params)).willReturn(Single.error(error))
}

internal fun SingleUseCase<Unit, *>.willReturnError(error: Throwable) {
    given(buildUseCaseObservable(Unit)).willReturn(Single.error(error))
}

internal fun <P, R> ObservableUseCase<P, R>.willReturn(params: P, result: R) {
    given(buildUseCaseObservable(params)).willReturn(Observable.just(result))
}

internal fun <P, R> ObservableUseCase<P, R>.willReturnItems(params: P, result1: R, result2: R) {
    given(buildUseCaseObservable(params)).willReturn(Observable.just(result1, result2))
}

internal fun <R> ObservableUseCase<Unit, R>.willReturn(result: R) {
    given(buildUseCaseObservable(Unit)).willReturn(Observable.just(result))
}

internal fun <R> ObservableUseCase<Unit, R>.willReturnItems(result1: R, result2: R) {
    given(buildUseCaseObservable(Unit)).willReturn(Observable.just(result1, result2))
}

internal fun <P> ObservableUseCase<P, *>.willReturnError(params: P, error: Throwable) {
    given(buildUseCaseObservable(params)).willReturn(Observable.error(error))
}

internal fun ObservableUseCase<Unit, *>.willReturnError(error: Throwable) {
    given(buildUseCaseObservable(Unit)).willReturn(Observable.error(error))
}

internal fun <P> CompletableUseCase<P>.willComplete(params: P) {
    given(buildUseCaseObservable(params)).willReturn(Completable.complete())
}

internal fun CompletableUseCase<Unit>.willComplete() {
    given(buildUseCaseObservable(Unit)).willReturn(Completable.complete())
}

internal fun <P> CompletableUseCase<P>.willReturnError(params: P, error: Throwable) {
    given(buildUseCaseObservable(params)).willReturn(Completable.error(error))
}

internal fun CompletableUseCase<Unit>.willReturnError(error: Throwable) {
    given(buildUseCaseObservable(Unit)).willReturn(Completable.error(error))
}