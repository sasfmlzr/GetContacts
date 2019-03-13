package contact.architecture

import contact.pipe.location.ToolbarEventModel
import io.reactivex.Observable
import io.reactivex.ObservableTransformer

internal typealias Pipe = ObservableTransformer<ViewEvent, EventModel>

internal fun Observable<EventModel>.appendErrorResults() =
        onErrorResumeNext { e: Throwable ->
            Observable.just(ErrorEventModel(e))
        }

internal fun Observable<EventModel>.appendLoadingResults() =
        onErrorResumeNext { _: Throwable ->
            Observable.empty<EventModel>()
        }
                .startWith(LoadingStartedEventModel())
                .concatWith(Observable.just(LoadingEndedEventModel()))

internal fun Observable<EventModel>.appendLoadingAndErrorResults() =
        appendErrorResults()
                .appendLoadingResults()

internal fun Observable<EventModel>.appendToolbarResults(title : String) =
        concatWith(Observable.just(ToolbarEventModel(title)))
