package contact.architecture

import io.reactivex.Observable
import io.reactivex.ObservableSource
import io.reactivex.ObservableTransformer

abstract class Funnel<S : ViewState>(private val initialState: S) : ObservableTransformer<Result, S> {

    protected abstract fun reduce(state: S, result: Result): S

    final override fun apply(upstream: Observable<Result>): ObservableSource<S> =
            upstream.scan(initialState) { state, result ->
                reduce(state, result)
            }
}
