package contact.architecture

import io.reactivex.Observable
import io.reactivex.ObservableSource
import io.reactivex.ObservableTransformer

abstract class Funnel<S : ViewState>(private val initialState: S) : ObservableTransformer<EventModel, S> {

    protected abstract fun reduce(state: S, eventModel: EventModel): S

    final override fun apply(upstream: Observable<EventModel>): ObservableSource<S> =
            upstream.scan(initialState) { state, result ->
                reduce(state, result)
            }
}
