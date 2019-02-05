package contact.architecture

import contact.architecture.base.ui.UiModel
import io.reactivex.Observable
import io.reactivex.ObservableSource
import io.reactivex.ObservableTransformer

abstract class Presenter<S : ViewState, M : UiModel> : ObservableTransformer<S, M> {

    protected abstract fun map(state: S): M

    override fun apply(upstream: Observable<S>): ObservableSource<M> =
            upstream.map(::map)

}
