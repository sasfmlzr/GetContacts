package contact.architecture

import io.reactivex.Observable
import io.reactivex.ObservableTransformer

internal typealias Pipe = ObservableTransformer<ViewEvent, Result>

internal fun Observable<Result>.appendErrorResults() =
        onErrorResumeNext { e: Throwable ->
            Observable.just(ErrorResult(e), HideErrorResult())
        }

internal fun Observable<Result>.appendLoadingResults() =
        onErrorResumeNext { _: Throwable ->
            Observable.empty<Result>()
        }
                .startWith(LoadingStartedResult())
                .concatWith(Observable.just(LoadingEndedResult()))

internal fun Observable<Result>.appendLoadingAndErrorResults() =
        appendErrorResults()
                .appendLoadingResults()
