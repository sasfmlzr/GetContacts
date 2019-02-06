package contact.architecture

import io.reactivex.Observable
import io.reactivex.ObservableSource
import io.reactivex.ObservableTransformer

abstract class Pipeline : ObservableTransformer<ViewEvent, EventModel> {

    protected abstract fun create(): List<Pipe>

    override fun apply(upstream: Observable<ViewEvent>): ObservableSource<EventModel> =
            upstream.publish { events ->
                Observable.merge(
                        create().map { events.compose(it) }
                )
            }

}
